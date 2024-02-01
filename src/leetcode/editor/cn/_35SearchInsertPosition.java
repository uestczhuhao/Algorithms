package leetcode.editor.cn;

public class _35SearchInsertPosition {
    public static void main(String[] args) {
        Solution t = new _35SearchInsertPosition().new Solution();
        int[] nums = {3, 5};
        System.out.println(t.searchInsert(nums, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                // 此处(low + high) / 2 也是对的
                // 因为对于 low <= high 来说，如果数组中没有target，总会走到low=high的位置（殊途同归）
                int mid = (low + high + 1) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return low;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
