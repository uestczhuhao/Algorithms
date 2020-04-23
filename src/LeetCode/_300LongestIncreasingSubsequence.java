package LeetCode;

/**
 * @author mizhu
 * @date 2020/4/23 21:31
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class _300LongestIncreasingSubsequence {
    public static void main(String[] args) {
//        int[] nums = {1,3,6,7,9,4,10,5,6};
        int[] nums = {1,2,3,1,2};
        _300LongestIncreasingSubsequence t = new _300LongestIncreasingSubsequence();
        System.out.println(t.lengthOfLIS(nums));
    }

    /**
     * 动态规划，dp[i] 表示前i个元素，以i结尾的最长递增子序列的最大值
     */
    public int lengthOfLIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 0;

        int totalMax = 1;
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            // 这一步的目的是找全局最大值，因为可能会出现1 2 3 1 2这种序列，最后一项dp[n-1] = 2并非全局最大值
            totalMax = Math.max(totalMax, dp[i]);
        }

        return totalMax;
    }
}
