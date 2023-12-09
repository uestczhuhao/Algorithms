package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-20 下午5:09.
 * Description:
 */
public class _581ShortestSubArray {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {1, 3, 2, 2, 2};
//        int[] nums = { 3, 2};
        System.out.println(findShortestSubArray(nums));
        _581ShortestSubArray t = new _581ShortestSubArray();
        System.out.println(t.findShortestSubArray2(nums));
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * <p>
     * 你找到的子数组应是最短的，请输出它的长度。
     * <p>
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     */
    static int findShortestSubArray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int minLeft = 0, maxRight = nums.length - 1;
        boolean reverse = false;
        int min = nums[nums.length - 1], max = nums[0];
        for (int i = 0; i < nums.length; i++) {
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

    /**
     * 两个指针，min从左到右遍历，遇到nums[i] < num[i-1]时记录，最后取最小值，这个值为无序部分的最小值；从右到左可以找到无序最大值max
     * 第二次遍历数组，第一个比min大的数的左边，即为左边界（把min放到这里，可以保证整个数组有序）
     * 同理，右边第一个比max大的数左边为右边界
     * 优化：上诉的max，min，左边界left，右边界right可以在一次遍历中完成
     */
    public int findShortestSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        boolean leftReverse = false, rightReverse = false;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] < nums[i - 1]) {
                leftReverse = true;
            }
            if (leftReverse) {
                min = Math.min(min, nums[i]);
            }

            int j = len - 1 - i;
            if (j > 0 && nums[j] < nums[j - 1]) {
                rightReverse = true;
            }

            if (rightReverse) {
                max = Math.max(max, nums[j]);
            }
        }

        int left = -1, right = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] > min && left == -1) {
                left = i;
            }
            int j = len - 1 - i;
            if (nums[j] < max && right == -1) {
                right = j;
            }
        }

        return right > left ? right - left + 1 : 0;
    }

    /**
     * 用栈来解，思路更清晰
     */
    public int findShortestSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right = Math.max(stack.pop(), right);
            }
            stack.push(i);
        }

        return left < right ? right - left + 1 : 0;
    }

}
