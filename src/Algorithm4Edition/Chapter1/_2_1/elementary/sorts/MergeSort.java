package Algorithm4Edition.Chapter1._2_1.elementary.sorts;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-12 下午4:19
 */
public class MergeSort {

    private static int[] temp;

    public static void main(String[] args) {
        int[] nums = {4, 5, 12, 3, 1, 2};
        mergeSort(nums);
//        down2UpMergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 递归版
     */
    static void mergeSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        temp = Arrays.copyOf(nums, nums.length);
        doMergeSort(nums, 0, nums.length - 1);

    }

    static void doMergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        doMergeSort(nums, low, mid);
        doMergeSort(nums, mid + 1, high);
        // 提升，左边的数组已经小于右边，则不需要排序
        // 比较次数为线性
        if (mid + 1 < nums.length && nums[mid] <= nums[mid + 1]) {
            return;
        }
        doMerge(nums, low, mid, high);
    }

    /**
     * 自底向上，两两合并，非递归
     */
    static void down2UpMergeSort(int[] nums) {
        temp = Arrays.copyOf(nums, nums.length);
        int len = nums.length;
        for (int gap = 1; gap < len; gap *= 2) {
            // i < len - gap 是为了保证剩余的长度能做merge
            for (int i = 0; i < len - gap; i += (2 * gap)) {
                doMerge(nums, i, i + gap - 1, Math.min(i + 2 * gap - 1, len - 1));
            }
        }
    }

    static void doMerge(int[] nums, int low, int mid, int high) {
        System.arraycopy(nums, low, temp, low, high - low + 1);
        int i = low, j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = temp[i++];
        }
        while (j <= high) {
            nums[k++] = temp[j++];
        }
    }
}
