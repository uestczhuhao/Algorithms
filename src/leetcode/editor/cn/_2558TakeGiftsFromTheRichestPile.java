package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _2558TakeGiftsFromTheRichestPile {
    public static void main(String[] args) {
        Solution t = new _2558TakeGiftsFromTheRichestPile().new Solution();
        int[] a = {56, 41, 27, 71, 62, 57, 67, 34, 8, 71, 2, 12, 52, 1, 64, 43, 32, 42, 9, 25, 73, 29, 31};
        System.out.println(t.pickGifts(a, 52));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long pickGifts(int[] gifts, int k) {
            long sum = 0L;
            int n = gifts.length;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int gift : gifts) {
                sum += gift;
                if (queue.size() < Math.min(k, n) || gift > queue.peek()) {
                    queue.offer(gift);
                }
                if (queue.size() > Math.min(k, n)) {
                    queue.poll();
                }
            }
            PriorityQueue<Integer> topQueue = new PriorityQueue<>(Comparator.reverseOrder());
            topQueue.addAll(queue);
            for (int i = 0; i < k; i++) {
                int top = topQueue.poll();
                int sqrt = (int) Math.sqrt(top);
                sum -= top - sqrt;
                topQueue.offer(sqrt);
            }
            return sum;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
