package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-20 下午7:02.
 * <p>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class _128LongestConsecutive {
    public static void main(String[] args) {
//        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {0, -1};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 思路：用一个set放置所有元素，从每个连续序列的头开始找，
     * 判定此序列是否大于当前的最大子序列的长度
     * <p>
     * 如：有[100, 1, 90, 2, 3, 4]，则从1开始找，循环判定set.contain(num+1)，同时子序列长度+1
     * 循环退出，从1开始的连续序列长度就确定下来
     */
    static int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestSub = 1;
        for (Integer num : numSet) {
            // 一个优化，如果包含了num-1，则num不是这一串序列的头
            // 应该从头开始遍历，保证每个元素遍历一次
            if (!numSet.contains(num - 1)) {
                int thisSubLen = 1;
                while (numSet.contains(num + 1)) {
                    thisSubLen++;
                    num += 1;
                }
                if (thisSubLen > longestSub) {
                    longestSub = thisSubLen;
                }
            }
        }

        return longestSub;
    }
}
