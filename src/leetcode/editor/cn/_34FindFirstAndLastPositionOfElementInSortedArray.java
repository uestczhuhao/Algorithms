package leetcode.editor.cn;

import java.util.Arrays;

public class _34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution t = new _34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] nums = {1};
        System.out.println(Arrays.toString(t.searchRange(nums, 1)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] ans = {-1, -1};
            if (nums == null || nums.length == 0) {
                return ans;
            }

            int first = findFirstIndex(nums, target);
            if (first == -1) {
                return ans;
            }
            ans[0] = first;
            int last = findLastIndex(nums, target);
            ans[1] = last;
            return ans;
        }

        private int findFirstIndex(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] >= target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            if (low < nums.length && nums[low] == target) {
                return low;
            }
            return -1;
        }

        private int findLastIndex(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (nums[mid] <= target) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            if (low < nums.length && nums[low] == target) {
                return low;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
