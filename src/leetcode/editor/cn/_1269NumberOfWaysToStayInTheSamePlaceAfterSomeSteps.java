package leetcode.editor.cn;

//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。 
//
// 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。 
//
// 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。 
//
// 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。 
//
// 
//
// 示例 1： 
//
// 输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
// 
//
// 示例 2： 
//
// 输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
// 
//
// 示例 3： 
//
// 输入：steps = 4, arrLen = 2
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= steps <= 500 
// 1 <= arrLen <= 10^6 
// 
// Related Topics 动态规划 
// 👍 123 👎 0


import java.util.Arrays;

public class _1269NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        Solution t = new _1269NumberOfWaysToStayInTheSamePlaceAfterSomeSteps().new Solution();
//        System.out.println(t.numWays(4, 3));
        System.out.println(t.numWays(27, 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][j]表示在i步后，指针在j处的方法数量
         * 其中 j <= min(step / 2, arrLen)，不然在steps步后就不再能回来了
         */
        public int numWays(int steps, int arrLen) {
            int column = Math.min(arrLen, steps / 2 + 1);
            int[][] dp = new int[steps + 1][column];
            dp[0][0] = 1;
            for (int i = 1; i <= steps; i++) {
                for (int j = 0; j < column; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j != 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % 1_000_000_007;
                    }
                    if (j != column - 1) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % 1_000_000_007;
                    }
                }
            }

//            System.out.println(Arrays.deepToString(dp));
            return dp[steps][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}