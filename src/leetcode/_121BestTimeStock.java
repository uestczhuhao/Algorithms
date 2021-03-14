package leetcode;

/**
 * @author mizhu
 * @date 2020/3/21 16:57
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class _121BestTimeStock {
    public static void main(String[] args) {
        _121BestTimeStock t = new _121BestTimeStock();
        int[] prices = {7, 6, 8, 3, 1};
        System.out.println(t.maxProfit(prices));
        System.out.println(t.maxProfit1(prices));
    }

    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }

    /**
     * 动态规划解法
     */
    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int len = prices.length;
        int[][] dp = new int[len][2];

        // 初始化第一天的dp值
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(-prices[i], dp[i-1][1]);
        }
        // 最后一天不持股获益最多
        return dp[len-1][0];
    }
}
