package LeetCode;

import java.util.Arrays;

/**
 * Created by mizhu on 19-6-2 下午10:50
 */
public class _34FindFirstandLastPositionofElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = {8,8,8,8,8};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
    }
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums == null || nums.length == 0 ||
                target < nums[0] || target > nums[nums.length - 1]) {
            return result;
        }
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private static int[] binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high ) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                min = min < mid ? min : mid;
                max = max > mid ? max : mid;
                binarySearch(nums, mid+1, high, target);
                binarySearch(nums, low, mid-1, target);
                break;
            }

            if (nums[mid] < target){
                low = mid+1;
            } else {
                high = mid-1;
            }

        }
        int[] res = new int[2];
        if (min <= max) {
            res[0] = min;
            res[1] = max;
        } else {
            res[0] = res[1] = -1;
        }
        return res;
    }
}
