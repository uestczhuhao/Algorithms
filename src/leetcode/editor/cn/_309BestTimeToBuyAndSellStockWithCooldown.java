package leetcode.editor.cn;

//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 动态规划 
// 👍 668 👎 0


import java.util.Map;

public class _309BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        Solution t = new _309BestTimeToBuyAndSellStockWithCooldown().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 第i天有以下三种状态，且都能从前一天状态推导出来
         * 0 有股票，则前一天可以有；也可以没有（但非冷冻期），第i天现买入，最大收益记为 f[i][0] = max {f[i-1][0], f[i-1][2] - price[i] }
         * 1 没有股票，且在冷冻期，则前一天必有股票，且卖出了，最大收益记为 f[i][1] = f[i-1][0] + price[i]
         * 2 没有股票，且不在冷冻期，则前一天要么在冷冻期，要么不持股且不在冷冻期（可能前天或更早的时间处于冷冻期），最大收益为 f[i][2] = max{f[i-1][1], f[i-1][2]}
         * <p>
         * 初始化
         * f[0][0] = -price[0]，第一天就买入，收益为负
         * f[0][1] = 0，第一天不可能为冷冻期，用0代替
         * f[0][2] = 0，同上，用0代替
         */
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }

            int hold = -prices[0];
            int freeze = 0;
            int trade = 0;
            for (int i = 1; i < prices.length; i++) {
                int iHold = Math.max(hold, trade - prices[i]);
                int iFreeze = hold + prices[i];
                int iTrade = Math.max(freeze, trade);
                hold = iHold;
                freeze = iFreeze;
                trade = iTrade;
            }

            // 若最后一天持股，则一定不如提前卖出的收益大
            return Math.max(freeze, trade);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}