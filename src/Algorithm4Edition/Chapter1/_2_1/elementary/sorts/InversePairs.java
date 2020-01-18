package Algorithm4Edition.Chapter1._2_1.elementary.sorts;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-18 下午6:19
 */
public class InversePairs {
    static int[] temp;

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 6, 5, 4};
//        int[] nums = {10, 9, 5, 6, 7, 8};
        System.out.println(inversePairs(nums));
    }

    /**
     * 确定数组中的逆序对，利用归并排序的思想
     *
     * @param nums
     * @return
     */
    static int inversePairs(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return 0;
        }
        int length = nums.length;
        temp = Arrays.copyOf(nums, length);

        // 非递归版本的归并排序
        int result = 0;
        int result1 = 0;
        for (int gap = 1; gap < length; gap *= 2) {
            for (int i = 0; i < length - gap; i += 2 * gap) {
//                result += countInverse(nums, i, i + gap - 1, Math.min(i + 2 * gap - 1, length - 1));
                result1 += countInverseFromSmall(nums, i, i + gap - 1, Math.min(i + 2 * gap - 1, length - 1));
            }
        }

        return result1;
    }

    /**
     * 从大到小merge，书上的解法
     */
    static int countInverse(int[] nums, int low, int mid, int high) {
        System.arraycopy(nums, low, temp, low, high - low + 1);
        int left = mid, right = high;
        int merge = high;
        int inverse = 0;
        while (left >= low && right >= mid + 1) {
            if (temp[left] > temp[right]) {
                // 严格大于才算逆序对
                inverse += right - mid;
                nums[merge--] = temp[left--];
            } else {
                nums[merge--] = temp[right--];
            }
        }

        while (left >= low) {
            nums[merge--] = temp[left--];
        }

        while (right >= mid + 1) {
            nums[merge--] = temp[right--];
        }
        return inverse;
    }

    /**
     * 从小到大解法，和归并排序顺序一致
     */
    static int countInverseFromSmall(int[] nums, int low, int mid, int high) {
        System.arraycopy(nums, low, temp, low, high - low + 1);
        int left = low, right = mid + 1;
        int merge = low;
        int inverse = 0;
        while (left <= mid && right <= high) {
            if (temp[left] > temp[right]) {
                // 严格大于才算逆序对
                inverse += mid - left + 1;
                nums[merge++] = temp[right++];
            } else {
                nums[merge++] = temp[left++];
            }
        }

        while (left <= mid) {
            nums[merge++] = temp[left++];
        }

        while (right <= high) {
            nums[merge++] = temp[right++];
        }
        return inverse;
    }
}
