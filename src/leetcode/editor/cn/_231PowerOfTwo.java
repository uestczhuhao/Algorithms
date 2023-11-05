package leetcode.editor.cn;

public class _231PowerOfTwo {
    public static void main(String[] args) {
        Solution t = new _231PowerOfTwo().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n-1)) == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
