package leetcode.editor.cn;

//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 491 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _77Combinations {
    public static void main(String[] args) {
        Solution t = new _77Combinations().new Solution();
        System.out.println(t.combine(4, 2));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：回溯算法，每次取一个数，当长度等于k时退出
         */
        int tgtLen, maxNum;

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            if (n <= 0 || k <= 0) {
                return result;
            }
            maxNum = n;
            tgtLen = k;

            dfs(1, new LinkedList<>(), result);
            return result;
        }

        private void dfs(int index, Deque<Integer> currentPath, List<List<Integer>> result) {
            // 剪枝，当剩余的数字不够时，这个分支不可能会成为结果，直接剪枝
            // 即，currentPath.size() + （n - i + 1） < k，
            // 第一项代表当前取的长度，第二项是剩下的数字长度，两项之和小于长度k时，直接剪枝
            if ( currentPath.size() + (maxNum - index + 1) < tgtLen) {
                return;
            }

            if (currentPath.size() == tgtLen) {
                result.add(new ArrayList<>(currentPath));
                return;
            }
            for (int i = index; i <= maxNum; i++) {
                currentPath.addLast(i);
                dfs(i + 1, currentPath, result);
                currentPath.removeLast();
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}