package leetcode.editor.cn;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class _313SuperUglyNumber {
    public static void main(String[] args) {
        Solution t = new _313SuperUglyNumber().new Solution();
        int[] primes = {2, 7, 13, 19};
        System.out.println(t.nthSuperUglyNumber(4, primes));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            Queue<Long> queue = new PriorityQueue<>();
            queue.add(1L);
            for (int i = 0; i < n - 1; i++) {
                long cur = queue.poll();
                for (int p : primes) {
                    long mul = cur * p;
                    if (mul <= Integer.MAX_VALUE) {
                        queue.offer(mul);
                    }
                    if (cur % p == 0) {
                        break;
                    }
                }
            }
            return queue.peek().intValue();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
