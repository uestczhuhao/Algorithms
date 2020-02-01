package LeetCode;

import com.sun.xml.internal.ws.util.StringUtils;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-2-1 下午7:40
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
public class _179LargestNumber {

    static int[] temp;

    public static void main(String[] args) {
        int[] nums = {0, 0};
//        temp = new int[nums.length];
        _179LargestNumber test = new _179LargestNumber();
        String result = test.largestNumber(nums);
        System.out.println(result);
    }

    public String largestNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return "";
        }
        temp = new int[nums.length];

        mergeSortByCombine(nums);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < nums.length && nums[i] == 0) {
            i++;
        }
        for (; i < nums.length; i++) {
            result.append(nums[i]);
        }
        if (result.length() <= 0) {
            return "0";
        }
        return result.toString();
    }

    public void mergeSortByCombine(int[] nums) {
        int length = nums.length;

        for (int step = 1; step < length; step += step) {
            for (int i = 0; i < length - step; i += 2 * step) {
                merge(nums, i, i + step - 1, Math.min(i + 2 * step - 1, length - 1));
            }
        }
    }

    private void merge(int[] nums, int low, int mid, int high) {
        System.arraycopy(nums, low, temp, low, high - low + 1);
        int left = low, right = mid + 1;
        int mergerIndex = low;
        // 倒序
        while (left <= mid && right <= high) {
            if (isCombineLarger(temp[left], temp[right])) {
                nums[mergerIndex++] = temp[left++];
            } else {
                nums[mergerIndex++] = temp[right++];
            }
        }

        while (left <= mid) {
            nums[mergerIndex++] = temp[left++];
        }

        while (right <= high) {
            nums[mergerIndex++] = temp[right++];
        }
    }

    private boolean isCombineLarger(int left, int right) {
        String leftRight = "" + left + right;
        String rightLeft = "" + right + left;

        return Long.parseLong(leftRight) > Long.parseLong(rightLeft);
    }
}
