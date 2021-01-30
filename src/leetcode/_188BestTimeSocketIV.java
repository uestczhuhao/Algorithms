package leetcode;

/**
 * @author mizhu
 * @date 2020/3/27 22:43
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * <p>
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class _188BestTimeSocketIV {
    public static void main(String[] args) {
        _188BestTimeSocketIV t = new _188BestTimeSocketIV();
        int[] a = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(t.maxProfit(2, a));


    }

    /**
     * 三维数组解法，大数据测试用例会oom
     * 详解见123题
     */
    public int maxProfit(int k, int[] prices) {
        if (null == prices || prices.length == 0 || k <= 0) {
            return 0;
        }

        if (k > prices.length / 2) {
            return maxProfit1(prices);
        }

        int days = prices.length;
        /*  dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + price[i])
            dp[0][j][0] = max(dp[-1][j][0], dp[-1][j][1] + price[0])

            dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - price[i])
            dp[0][j][1] = max(dp[-1][j][1], dp[-1][j-1][0] - price[0])
         */
        int[][][] dp = new int[days][k + 1][2];
        for (int i = 0; i < days; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[0];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[days - 1][k][0];
    }

    private int maxProfit1(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }


}
