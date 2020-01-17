package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-17 下午8:03.
 * Description:
 */
public class _581ShortestSubArray {
    public static void main(String[] args) {
//        int[] nums = {2, 6, 4, 8, 10, 9, 15};
//        int[] nums = {1, 2, 3, 4};
        int[] nums = {1, 3, 2, 2, 2};
//        int[] nums = { 3, 2};
        System.out.println(findShortestSubArray(nums));
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     */
    static int findShortestSubArray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int minLeft = 0, maxRight = nums.length-1;
        boolean reverse = false;
        int min = nums[nums.length-1], max = nums[0];
        for (int i=0 ;i<nums.length; i++) {
            // max表示遍历到i时遇到的最大值，用其和i比较
            // 如果i位置上更大，则更新max
            // 否则i位置是当前的最右边界
            if (max > nums[i]) {
                reverse = true;
                maxRight = i;
            } else {
                max = nums[i];
            }

            int right = nums.length - 1 - i;
            if (min < nums[right]) {
                reverse = true;
                minLeft = right;
            } else {
                min = nums[right];
            }
        }
        if (!reverse) {
            return 0;
        }

        return maxRight - minLeft + 1;
    }
}
