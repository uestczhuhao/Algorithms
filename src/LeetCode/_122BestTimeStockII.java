package LeetCode;

/**
 * @author mizhu
 * @date 2020/3/21 20:05
 */
public class _122BestTimeStockII {
    public static void main(String[] args) {

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
}
