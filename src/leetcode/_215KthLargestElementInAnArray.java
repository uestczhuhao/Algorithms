package leetcode;

import java.util.Arrays;
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

    public static void main(String[] args) {
        HeapSort h = new _215KthLargestElementInAnArray().new HeapSort();
        int[] a = {2, 1, 3, 8, 4, 2, 9, 12};
        System.out.println((h.findKthLargest(a,2)));
        System.out.println((Arrays.toString(h.heapSort(a))));
    }

    class HeapSort {

        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0
                || k <= 0 || k > nums.length) {
                return Integer.MIN_VALUE;
            }

            buildHeap(nums, k);
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > nums[0]) {
                    swap(nums, 0, i);
                    heapify(nums, 0, k - 1);
                }
            }

            return nums[0];
        }


        /**
         * 堆排序，先构建大顶堆，再交换尾部值后调整（等效为删除）
         *
         * @param nums
         * @return
         */
        public int[] heapSort(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            int heapSize = nums.length - 1;
            // 从下往上构建堆
            for (int i = heapSize / 2 - 1; i >= 0; i--) {
                heapify(nums, i, heapSize - 1);
            }
            // 从上往下构建堆
//            for (int i = 0; i <= nums.length / 2 - 1; i++) {
//                heapify(nums, i, heapSize - 1);
//            }

            for (int i = nums.length - 1; i >= 1; i--) {
                swap(nums, 0, i);
                heapify(nums, 0, i - 1);
            }
            return nums;
        }

        public void buildHeap(int[] nums, int heapSize) {
            // 从下往上构建堆
            for (int i = heapSize / 2 - 1; i >= 0; i--) {
                heapify(nums, i, heapSize - 1);
            }
          // 从上往下构建堆
//            for (int i = 0; i <= nums.length / 2 - 1; i++) {
//                heapify(nums, i, heapSize - 1);
//            }
        }

        // 从上往下调整
        public void heapify(int[] nums, int low, int high) {
            int lChildIndex = low * 2 + 1;
            int rChildIndex = low * 2 + 2;
            int smaller = low;
            // 取左右子节点这较小的那个
            // 注意下标要合法
            if (lChildIndex <= high && nums[lChildIndex] < nums[low]) {
                smaller = lChildIndex;
            }

            if (rChildIndex <= high && nums[rChildIndex] < nums[smaller]) {
                smaller = rChildIndex;
            }

            if (nums[smaller] < nums[low]) {
                swap(nums, smaller, low);
                heapify(nums, smaller, high);
            }
        }
    }
}
