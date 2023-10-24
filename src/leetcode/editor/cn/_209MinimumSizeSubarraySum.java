package leetcode.editor.cn;

public class _209MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution t = new _209MinimumSizeSubarraySum().new Solution();
        int[] ns = {2, 3, 1, 2, 4, 3};
        int[] ns1 = {1, 4, 4};
        System.out.println(t.minSubArrayLen(7, ns));
        System.out.println(t.minSubArrayLen(4, ns1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 滑动窗口，每次把high位置的元素加入sum，当sum >= target时，把low的位置移出区间，直到sum < target
         */
        public int minSubArrayLen(int target, int[] nums) {
            int sum = 0, low = 0, high = 0, n = nums.length;
            int ans = Integer.MAX_VALUE;
            while (high < n) {
                sum += nums[high];
                while (sum >= target) {
                    ans = Math.min(ans, high - low + 1);
                    sum -= nums[low++];
                }
                high++;
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
