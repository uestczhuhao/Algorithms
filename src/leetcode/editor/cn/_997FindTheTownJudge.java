package leetcode.editor.cn;

//在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
//
// 如果小镇的法官真的存在，那么：
//
//
// 小镇的法官不相信任何人。
// 每个人（除了小镇法官外）都信任小镇的法官。
// 只有一个人同时满足条件 1 和条件 2 。
//
//
// 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
//
// 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
//
//
//
// 示例 1：
//
//
//输入：n = 2, trust = [[1,2]]
//输出：2
//
//
// 示例 2：
//
//
//输入：n = 3, trust = [[1,3],[2,3]]
//输出：3
//
//
// 示例 3：
//
//
//输入：n = 3, trust = [[1,3],[2,3],[3,1]]
//输出：-1
//
//
// 示例 4：
//
//
//输入：n = 3, trust = [[1,2],[2,3]]
//输出：-1
//
//
// 示例 5：
//
//
//输入：n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
//输出：3
//
//
//
// 提示：
//
//
// 1 <= n <= 1000
// 0 <= trust.length <= 10⁴
// trust[i].length == 2
// trust[i] 互不相同
// trust[i][0] != trust[i][1]
// 1 <= trust[i][0], trust[i][1] <= n
//
// Related Topics 图 数组 哈希表 👍 222 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _997FindTheTownJudge {
    public static void main(String[] args) {
        Solution t = new _997FindTheTownJudge().new Solution();
        int[][] s = {{1, 3}, {1, 4}, {2, 3}};
        System.out.println(t.findJudge(4, s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 用入度出度的思路解，假设a信任b，则a出度加1，b入度加1
         * 则法官是入度为n-1，出度为0
         * 更进一步，若有人 入度 - 出度 == n-1，则定是法官，可以用一个数组解决问题
         */
        public int findJudge(int n, int[][] trust) {
            int[] inOutDegree = new int[n + 1];
            for (int[] t : trust) {
                inOutDegree[t[0]]--;
                inOutDegree[t[1]]++;
            }
            for (int i = 1; i <= n; i++) {
                if (inOutDegree[i] == n - 1) {
                    return i;
                }
            }
            return -1;
        }

        public int findJudge1(int n, int[][] trust) {
            if (trust.length == 0) {
                return n == 1 ? n : -1;
            }
            Set<Integer> trustSet = new HashSet<>();
            int[] trustCount = new int[n + 1];
            int maxTrusted = 0;
            int judge = -1;
            for (int[] t : trust) {
                trustSet.add(t[0]);
                int candidate = t[1];
                trustCount[candidate]++;
                if (trustCount[candidate] > maxTrusted) {
                    maxTrusted = trustCount[candidate];
                    judge = candidate;
                }
            }

            if (!trustSet.contains(judge) && trustSet.size() == n - 1) {
                return judge;
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
