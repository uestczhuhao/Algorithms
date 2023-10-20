package leetcode.editor.cn;

public class _190ReverseBits {
    public static void main(String[] args) {
        Solution t = new _190ReverseBits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int rev = 0;
            for (int i = 0; i < 32 && n != 0; ++i) {
                int cur = n & 1;
                rev |= cur << (31 - i);
                n >>>= 1;
            }
            return rev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
