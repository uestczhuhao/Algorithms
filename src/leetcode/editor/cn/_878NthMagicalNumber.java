package leetcode.editor.cn;

public class _878NthMagicalNumber {
    public static void main(String[] args) {
        Solution t = new _878NthMagicalNumber().new Solution();
        System.out.println(t.nthMagicalNumber(5, 2, 4));
        System.out.println(t.nthMagicalNumber(4, 2, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 思路参考1201-丑数III
        public int nthMagicalNumber(int n, int a, int b) {
            long low = Math.min(a, b);
            long high = n * low;
            while (low < high) {
                long mid = low + ((high - low) >> 1);
                if (checkLess(mid, n, a, b)) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return (int) (low % 1000_000_007);
        }

        // 优化：可以把lcm(a, b)算一次存下来，以后每次直接使用
        private boolean checkLess(long mid, long n, long a, long b) {
            long magNum = mid / a + mid / b - mid / lcm(a, b);
            return magNum < n;
        }

        private long lcm(long a, long b) {
            long res = a * b;
            while (b > 0) {
                long tmp = b;
                b = a % b;
                a = tmp;
            }
            return res / a;
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}
