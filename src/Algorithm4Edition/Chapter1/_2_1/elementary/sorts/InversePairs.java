package Algorithm4Edition.Chapter1._2_1.elementary.sorts;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-18 下午6:19
 */
public class InversePairs {
    static int[] temp;

    public static void main(String[] args) {
        int[] nums = {10, 9, 5, 6, 8, 7};
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
        for (int gap = 1; gap < length; gap *= 2) {
            for (int i = 0; i < length - gap; i += 2 * gap) {
                result += countInverse(nums, i, i + gap - 1, Math.min(i + 2 * gap - 1, length - 1));
            }
        }

        return result;
    }

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
}
