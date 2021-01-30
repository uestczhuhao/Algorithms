package leetcode;

/**
 * Created by mizhu on 19-6-2 下午8:40
 */
public class _33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 0, 1, 2, 3};
        System.out.println(findHeadIndex1(nums));
        System.out.println(search(nums, 3));
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
            if (target == nums[mid]) {
                return mid;
            }

            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
