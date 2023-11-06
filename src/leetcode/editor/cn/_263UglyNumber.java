package leetcode.editor.cn;

public class _263UglyNumber {
    public static void main(String[] args) {
        Solution t = new _263UglyNumber().new Solution();
        System.out.println(t.isUgly(-2147483648));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * n = 2^a * 3 ^ b * 5 ^ c，因此可以直接一直对n/2（假设满足n % 2 == 0）
         * 同理可以一直运行 n / 3 和 n / 5
         * 时间复杂度O(log n)，时间复杂度取决于对 n 除以 2,3,5 的次数，
         * 由于每次至少将 n 除以 2，因此除法运算的次数不会超过 O(log n)。
         *
         */
        public boolean isUgly(int n) {
            if (n <= 0) {
                return false;
            }

            while (n % 2 == 0) {
                n /= 2;
            }

            while (n % 3 == 0) {
                n /= 3;
            }

            while (n % 5 == 0) {
                n /= 5;
            }

            return n == 1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
