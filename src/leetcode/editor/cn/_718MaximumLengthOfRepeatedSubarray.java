package leetcode.editor.cn;

public class _718MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        Solution t = new _718MaximumLengthOfRepeatedSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
