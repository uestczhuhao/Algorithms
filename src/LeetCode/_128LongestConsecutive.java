package LeetCode;

import java.util.HashSet;
import java.util.Set;
/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-20 下午7:02.
 * Description:
 */
public class _128LongestConsecutive {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * 要求算法的时间复杂度为 O(n)。
     *
     * 示例:
     *
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    static int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (length <=1) {
            return length;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestSub = 1;
        for (Integer num: numSet) {
            if (!numSet.contains(num-1)) {
                int thisSubLen = 1;
                while (numSet.contains(num +1)) {
                    thisSubLen++;
                    num +=1;
                }
                if (thisSubLen > longestSub) {
                    longestSub = thisSubLen;
                }
            }
        }

        return longestSub;
    }
}
