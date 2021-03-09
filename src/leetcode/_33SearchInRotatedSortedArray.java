package leetcode;

/**
 * Created by mizhu on 19-6-2 下午8:40
 */
public class _33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 0, 1, 2, 3};
//        System.out.println(findHeadIndex1(nums));
//        System.out.println(search(nums, 3));
        _33SearchInRotatedSortedArray t = new _33SearchInRotatedSortedArray();
        System.out.println(t.search1(nums, 3));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int index = findHeadIndex(nums);
        if (target >= nums[0] && index > 0) {
            return findTarget(nums, 0, index - 1, target);
        } else {
            return findTarget(nums, index, nums.length - 1, target);
        }
    }

    /**
     * 思路：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-bai-liao-9983de-javayong-hu-by-reedfan/
     * 分两种情况
     * 1 nums[low] <= nums[mid] 时，前半部分有序
     *  a 如果nums[low] <= target < nums[mid]，去前面一半找
     *  b 否则，去后面一半找
     * 2 nums[low] > nums[mid]否则后半部分有序
     *  a 如果nums[mid] < target <= nums[high]，则去后面一半找
     *  b 否则，去前面一半找
     */
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int low =0, high = nums.length -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            // 前面一半有序
            if (nums[low] <= nums[mid]) {
                // 前一半有序且target在前面一半
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    // target在后面一半，且后一半无序
                    low = mid + 1;
                }
            } else { // 后面一半有序
                // 后面一半有序且target在后面一半
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    // target在前面一半，且前一半无序
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    private static int findHeadIndex(int[] nums) {
        int low = 0, high = nums.length - 1;
        int pre = low;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[low] > nums[high]) {
                pre = low;
                low = mid;
            } else {
                high = low;
                low = pre;
            }
        }

        return nums[low] > nums[high] ? high : low;
    }

    private static int findHeadIndex1(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int findTarget(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target > nums[mid]) {
                low = mid + 1;
            } else if (target < nums[mid]){
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
