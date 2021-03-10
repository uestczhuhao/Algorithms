package leetcode;

import java.util.Arrays;

/**
 * Created by mizhu on 19-6-2 下午10:50
 */
public class _34FindFirstandLastPositionofElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = {8, 8, 8, 8, 8};
//        int[] nums = {5, 7, 8, 8, 10};
//        System.out.println(Arrays.toString(searchRange(nums, 8)));
        _34FindFirstandLastPositionofElementInSortedArray t = new _34FindFirstandLastPositionofElementInSortedArray();
        System.out.println(Arrays.toString(t.searchRange1(nums, 8)));
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
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                min = Math.min(min, mid);
                max = Math.max(max, mid);
                binarySearch(nums, mid + 1, high, target);
                binarySearch(nums, low, mid - 1, target);
                break;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
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

    public int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // 先找第一个位置
        int firstPos = findFirstPosition(nums, target);
        if (firstPos == -1) {
            return new int[]{-1, -1};
        }

        // 再找最后一个位置
        int lastPos = findLastPosition(nums, target);
        return new int[]{firstPos, lastPos};
    }

    private int findFirstPosition (int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 此处采用left < right 的写法，保证退出时left == right
        // 参考：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/da-jia-bu-yao-kan-labuladong-de-jie-fa-fei-chang-2/
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 此时不能退出，要在左边寻找，试图找到target的第一次出现位置
            // 下一轮搜索区间是[left, mid]
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 若left合法，且left的值和target的值相等，则其为target第一次出现的位置
        if (left < nums.length && nums[left] == target) {
            // left == right，也可以返回right
            return left;
        }

        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 此处采用left < right 的写法，保证退出时left == right
        while (left < right) {
            // 向上取整，避免死循环，例如left = 2，right = 3，且nums[left] == nums[right] == target时
            // 向上取证保证mid = 3，下一次退出，否则mid = 2，死循环
            int mid = left + (right - left + 1) / 2;
            // 此时不能退出，要在右边寻找，试图找到target的第一次出现位置
            // 下一轮搜索区间是 [mid, right]
            if (nums[mid] == target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 一定能找到这个最后的位置，因为第一个位置能找到，代表target一定出现过
        // 返回left 和 right 效果等同
        return left;
    }
}
