package leetcode.editor.cn;

//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：3
//解释：12 = 4 + 4 + 4
//
// 示例 2：
//
//
//输入：n = 13
//输出：2
//解释：13 = 4 + 9
//
//
// 提示：
//
//
// 1 <= n <= 104
//
// Related Topics 广度优先搜索 数学 动态规划
// 👍 749 👎 0


public class _279PerfectSquares {
    public static void main(String[] args) {
        Solution t = new _279PerfectSquares().new Solution();
//        System.out.println(t.numSquares(3));
//        System.out.println(t.numSquares(4));
        System.out.println(t.numSquares(3));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：动态规划，dp[i] 表示i能展开的完全平方数的最少数量
         * dp[i] = min{ dp[i - k] + 1} 其中k为某个数的平方
         */
        public int numSquares(int n) {
            if (n <= 1) {
                return n;
            }

            // 先准备好要用的k

            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int minSquares = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    minSquares = Math.min(minSquares, dp[i - j * j]);
                }
                dp[i] = minSquares + 1;
            }

            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
