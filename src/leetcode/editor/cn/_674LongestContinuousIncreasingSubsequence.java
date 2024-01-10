package leetcode.editor.cn;

public class _674LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        Solution t = new _674LongestContinuousIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int count = 1, max = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    count++;
                } else {
                    count = 1;
                }
                max = Math.max(max, count);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
