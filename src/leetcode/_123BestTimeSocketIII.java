package leetcode;

/**
 * @author mizhu
 * @date 2020/3/21 20:29
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class _123BestTimeSocketIII {
    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        _123BestTimeSocketIII t = new _123BestTimeSocketIII();
        System.out.println(t.maxProfit(prices));
    }

    /**
     * 动态规划，有三个变量，天数day，交易次数num，手中是否有股票（0：无/1：有）
     * 因此采用三维数组表示：dp[i][j][0/1]，其值为第i天，已经交易了j次，手中有/无股票时的最大获利
     * 其中 0<=i<day   1<=j<=num
     * 状态转移方程：
     * dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])
     * 第i天无股票，可以从i-1的两种状态转移：
     * 1. i-1天无股票，i天休息，即不交易
     * 2. i-1天有股票，i天卖出
     * dp[i][j][1] = max(dp[i-1][j-1][0] - prices[i], dp[i-1][j][1])
     * 同上，i天有股票也可以从两种状态转移
     * 1. i-1天无股票，i天买入，此时买卖次数+1
     * 2. i-1天有股票，i天休息
     * <p>
     * 接下来是初始条件
     * dp[-1][j][0] = 0     由于i是从0开始的，-1代表还没开始，因此利润为0
     * dp[-1][j][1] = -inf  由于还未开始，不可能持有股票，因此是负无穷
     * dp[i][0][0] = 0      由于j是从1开始，0代表还未交易，利润为0
     * dp[i][0][1] = -inf   由于未交易，因此不可能有股票，因此是负无穷
     * <p>
     * 由此可以推出，当i==0时，dp[0][j][0] = 0;  dp[0][j][1] = -prices[0]
     * <p>
     * 对本题来说，num = 2
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int days = prices.length;
        /*  dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + price[i])
            dp[0][j][0] = max(dp[-1][j][0], dp[-1][j][1] + price[0])

            dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - price[i])
            dp[0][j][1] = max(dp[-1][j][1], dp[-1][j-1][0] - price[0])
         */
        int[][][] dp = new int[days][3][2];
        for (int i = 0; i < days; i++) {
            for (int j = 2; j >= 1; j--) {
                // 第0天不持有股票收益为0
                // 持有股票必为第0天买入的，因此收益为-prices[0]
                if (i == 0) {
                    // 对于dp[0][2][0] 和 dp[0][2][1]这两种情况
                    // 可以理解为在同一天，已经买卖过一次股票了，此时收益为0，第二次买入dp[0][2][1] = -prices[0]，第二次卖出dp[0][2][0] = 0
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[0];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return Math.max(dp[days - 1][2][0], dp[days - 1][1][0]);
    }

    /**
     * dp[i][j][0] = max{dp[i-1][j][0], dp[i-1][j][1] + prices[i]}
     * dp[i][j][1] = max{dp[i-1][j-1][0] - prices[i], dp[i-1][j][1]}
     * 空间优化解法，注意先求dp[j][0]，理由是dp[j][0]要用到上一行到dp[j][1]
     */
    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int[][] dp = new int[3][2];
        dp[1][1] = -prices[0];
        dp[2][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 2; j >= 1; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j-1][0] - prices[i], dp[j][1]);
            }
        }

        return Math.max(dp[1][0], dp[2][0]);
    }
}
