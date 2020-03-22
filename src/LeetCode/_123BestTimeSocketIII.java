package LeetCode;

/**
 * @author mizhu
 * @date 2020/3/21 20:29
 */
public class _123BestTimeSocketIII {
    public static void main(String[] args) {
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        _123BestTimeSocketIII t = new _123BestTimeSocketIII();
        System.out.println(t.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int low, high;
        int index = 0;
        int max = 0, secMax = 0;
        while (index < prices.length - 1) {
            while (index < prices.length - 1 && prices[index] >= prices[index + 1]) {
                index++;
            }
            low = prices[index];

            while (index < prices.length - 1 && prices[index] <= prices[index + 1]) {
                index++;
            }
            high = prices[index];
            int gap = high - low;
            if (gap > max) {
                secMax = max;
                max = gap;
            } else {
                secMax = Math.max(secMax, gap);
            }
        }
        return max + secMax;
    }
}
