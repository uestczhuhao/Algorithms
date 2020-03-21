package LeetCode;

/**
 * @author mizhu
 * @date 2020/3/21 16:57
 */
public class _121BestTimeStock {
    public static void main(String[] args) {
        _121BestTimeStock t = new _121BestTimeStock();
        int[] prices = {7,6,4,3,1};
        System.out.println(t.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int maxProfit = 0;
        for (int i=1;i<prices.length;i++) {
            if (prices[i] > min) {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }
}
