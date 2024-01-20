package leetcode.editor.cn;

public class _154FindMinimumInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution t = new _154FindMinimumInRotatedSortedArrayIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] < nums[high]) {
                    high = mid;
                } else if (nums[mid] > nums[high]) {
                    low = mid + 1;
                } else {
                    high --;
                }
            }
            return nums[low];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
