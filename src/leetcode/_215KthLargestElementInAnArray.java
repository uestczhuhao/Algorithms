package leetcode;

import java.util.Random;

/**
 * @author mizhu
 * @date 2021/1/31 11:17
 */
public class _215KthLargestElementInAnArray {
    /**
     * 思路：快排中的partition，
     * 把数组分为两部分，左边小于该值，右边大于该值，保证该值是数组的第k大即可
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0
            || k <= 0 || k > nums.length) {
            return Integer.MIN_VALUE;
        }

        int low = 0, high = nums.length - 1;
        int kThLargest = partition(nums, low, high);
        int targetPos = nums.length - k;
        while (kThLargest != targetPos) {
            if (kThLargest > targetPos) {
                high = kThLargest - 1;
            } else {
                low = kThLargest + 1;
            }
            kThLargest = partition(nums, low, high);
        }

        return nums[kThLargest];
    }

    private int partition(int[] nums, int low, int high) {
        int index = low + new Random().nextInt(high - low + 1);
        swap(nums, low, index);
        int target = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= target) {
                high--;
            }
            nums[low] = nums[high];

            while (low < high && nums[low] <= target) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = target;
        return low;
    }

    private void swap(int[] nums, int src, int tgt) {
        int tmp = nums[src];
        nums[src] = nums[tgt];
        nums[tgt] = tmp;
    }
}
