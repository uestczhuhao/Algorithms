package leetcode.editor.cn;

//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件： 
//
// 
// 第 i 位的数字能被 i 整除 
// i 能被第 i 位上的数字整除 
// 
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？ 
//
// 示例1: 
//
// 
//输入: 2
//输出: 2
//解释: 
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
// 
//
// 说明: 
//
// 
// N 是一个正整数，并且不会超过15。 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 178 👎 0


import java.util.ArrayList;
import java.util.List;

public class _526BeautifulArrangement {
    public static void main(String[] args) {
        Solution t = new _526BeautifulArrangement().new Solution();
        System.out.println(t.countArrangement(4));
        System.out.println(t.countArrangement1(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 存放满足条件i对应的j（i和j都不会超过15）
        List<List<Integer>> match = new ArrayList<>();
        boolean[] visit;
        int ans;

        /**
         * 具体地，我们定义函数 dfs(index,n)，表示尝试向位置 index 放入数。其中 n 表示排列的长度。
         * 在当前函数中，我们首先找到一个符合条件的未被使用过的数，
         * 然后递归地执行 backtrack(index+1,n)，当该函数执行完毕，回溯到当前层，我们再尝试下一个符合条件的未被使用过的数即可。
         * 注意：核心在于构建match，否则对每一个index，要判断1～n中的每个数，看其是否满足条件
         */
        public int countArrangement1(int n) {
            visit = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                match.add(new ArrayList<>());
            }
            // 提前构建match
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i % j == 0 || j % i == 0) {
                        match.get(i).add(j);
                    }
                }
            }
            dfs(1, n);
            return ans;
        }

        private void dfs(int index, int n) {
            if (index == n + 1) {
                ans++;
                return;
            }

            for (int m : match.get(index)) {
                if (!visit[m]) {
                    visit[m] = true;
                    dfs(index + 1, n);
                    visit[m] = false;
                }
            }
        }

        /**
         * 状态压缩的动态规划，dp[i][j]表示考虑前i个数，且当前选择方案为j时的所有方案数
         * 其中j为二进制数，共15位，每一位为1 代表被选中，为0表示没有被选中，把状态压缩为一个二进制数
         */
        public int countArrangement(int n) {
            int mask = 1 << n;
            int[][] f = new int[n + 1][mask];
            f[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                // 枚举所有的状态
                for (int state = 0; state < mask; state++) {
                    // 枚举位置 i（最后一位）选的数值是 k
                    for (int k = 1; k <= n; k++) {
                        // 首先 k 在 state 中必须是 1，其次数值 k 和位置 i 之间满足任一整除关系
                        if ((((state >> (k - 1)) & 1) != 0) && (k % i == 0 || i % k == 0)) {
                            f[i][state] += f[i - 1][state & (~(1 << (k - 1)))];
                        }
                    }
                }
            }
            return f[n][mask - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}