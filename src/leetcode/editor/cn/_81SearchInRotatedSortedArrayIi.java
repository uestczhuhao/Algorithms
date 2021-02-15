package leetcode.editor.cn;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。 
//
// 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。 
//
// 示例 1: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 0
//输出: true
// 
//
// 示例 2: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 3
//输出: false 
//
// 进阶: 
//
// 
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？ 
// 
// Related Topics 数组 二分查找 
// 👍 283 👎 0


public class _81SearchInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution t = new _81SearchInRotatedSortedArrayIi().new Solution();
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(t.search(nums, 13));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：确定前/后半部分有序，然后在有序的部分二分查找
         * 1 如果nums[low] == nums[mid]，不确定前后哪部分有序（10111 和 11101），直接low++
         * 2 如果nums[low] < nums[mid]，前半部有序，如 2 3 4 5 6 7 1
         * 3 如果nums[low] > nums[mid]，后半部有序，如6 7 1 2 3 4 5
         * <p>
         * 在情况2中，如果target < nums[mid] && nums[low] <= target（考虑找1），则在前半部找，否则在后半部找
         * 情况3同上，如果target > nums[mid] && nums[high] >= target（考虑target = 6），则在后半部找，否则在前半部找
         */
        public boolean search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    return true;
                }

                if (nums[low] == nums[mid]) {
                    low++;
                } else if (nums[low] < nums[mid]) {
                    // 情况2，前半部有序
                    // 此时target在前半部的充要条件是
                    // 1 前半部最小值小于等于target
                    // 2 中间值nums[mid] 比target大
                    if (target < nums[mid] && nums[low] <= target) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    // 情况3，后半部有序
                    // 此时target在后半部的充要条件是
                    // 1 后半部最大值大于等于target
                    // 2 中间值nums[mid] 比target小
                    if (target > nums[mid] && nums[high] >= target) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}