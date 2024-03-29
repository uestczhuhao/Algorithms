package leetcode;

/**
 * @author mizhu
 * @date 2020/3/21 20:05
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class _122BestTimeStockII {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        _122BestTimeStockII t = new _122BestTimeStockII();
        System.out.println(t.maxProfit(prices));
        System.out.println(t.maxProfit1(prices));
    }


    /**
     * 思路：在每个低点买入，往后找高点，第一个最高点（比前一天和后一天价值都高）时卖出，累加即为最大值
     *
     * @param prices
     * @return
     */
    public int maxProfit0(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        int low, high;
        int index = 0;
        while (index < prices.length - 1) {
            while (index < prices.length - 1 && prices[index] >= prices[index + 1]) {
                index++;
            }
            low = prices[index];

            while (index < prices.length - 1 && prices[index] <= prices[index + 1]) {
                index++;
            }
            high = prices[index];

            maxProfit += high - low;
        }
        return maxProfit;
    }

    /**
     * 思路：对上诉思路的提升，因为每次都在上升期操作（上升期都最低点买入，最高点卖出）
     * 因此，每个上升期的利润都能吃的到，因此简单的对每个上升期（i天的价格小于i+1天）的差值相加即可
     */
    public int maxProfit(int[] prices) {
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

    /**
     * 动态规划+空间优化解法
     * 由于可以交易无数次，因此和121题的推导公式不同
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int len = prices.length;
        // 不持有股票
        int[] sell = new int[len];
        // 持有股票
        int[] hold = new int[len];
        hold[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            sell[i] = Math.max(sell[i-1], hold[i-1] + prices[i]);
            hold[i] = Math.max(hold[i-1], sell[i-1] - prices[i]);
        }

        return sell[len - 1];
    }
}
