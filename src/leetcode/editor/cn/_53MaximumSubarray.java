package leetcode.editor.cn;

public class _53MaximumSubarray {
    public static void main(String[] args) {
        Solution t = new _53MaximumSubarray().new Solution();
        int[] ns = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(t.maxSubArray(ns));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int ans = Integer.MIN_VALUE;
            int sum = 0;
            // 只需要关注当前元素之前的sum是否小于0，若小于，则从当前位置开始累加
            // 否则之前的累加值再加上当前元素即可
            for (int num : nums) {
                sum = sum <=0 ? num : num + sum;
                ans = Math.max(sum, ans);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
