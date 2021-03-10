package leetcode;

/**
 * Created by mizhu on 19-6-2 下午11:26
 */
public class _35SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = {1,3,5,9};
        System.out.println(searchInsert(nums,0));
    }
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 经过二分法（low <= high的方式），low刚好指向下一个大于（没找到）或等于target（找到了）的位置，right指向low的左边位置
        return low ;
    }
}
