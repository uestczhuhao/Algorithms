package LeetCode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2020/11/8 22:22
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class _238ProductOfArrayExceptSelf {
    /**
     * 先求前缀积，保存下来，再求后缀积，保存下来
     * 最后相乘即为结果
     * 时间 3*O(n)
     * 空间 2*O(n)
     *
     * 提升：
     * 1. 后缀积无需保存，现算即可，空间节省O(n)，同时节省时间O(n)
     * 2. 前缀积可与结果（不计入空间）使用同一个数组，空间节省O(n）
     * 总结，时间2*O(n)  空间O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int len = nums.length;
        int[] expectSelfProduct = new int[len];
        expectSelfProduct[0] = 1;
        int i;
        for (i = 1; i < len; i++) {
            expectSelfProduct[i] = expectSelfProduct[i - 1] * nums[i - 1];
        }
        int posProduct = 1;
        for (i = len - 1; i >= 0; i--) {
            expectSelfProduct[i] = posProduct * expectSelfProduct[i];
            posProduct = posProduct * nums[i];
        }

        return expectSelfProduct;
    }

    public static void main(String[] args) {
        _238ProductOfArrayExceptSelf t = new _238ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(t.productExceptSelf(nums)));

    }
}
