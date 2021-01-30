package leetcode;

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
//        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int[] nums = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        _300LongestIncreasingSubsequence t = new _300LongestIncreasingSubsequence();
        System.out.println(t.lengthOfLIS2(nums));
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

    /**
     * 贪心+二分，时间复杂度降低至nlogn
     */
    public int lengthOfLIS2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        // 辅助数组，tail[i]表示长度为i + 1的所有上升子序列的结尾的最小值
        // 可以证明tail是严格递增的，见：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
        int[] tail = new int[nums.length];
        // 从0开始，长度为1的递增子序列只有nums[0]
        tail[0] = nums[0];

        int end = 0;
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素大于tail数组的尾，则直接添加至tail中
            if (nums[i] > tail[end]) {
                tail[++end] = nums[i];
            } else {
                // 二分，插入nums[i]至tail中合适的位置
                // 即比当前值大的第一个数，替换之
                int low = 0, high = end;
                // 相等时再判断一次，因为可能此时的位置正好是比目标值大（或相等）的第一个数
                while (low <= high) {
                    int mid = low + ((high - low) >>> 1);
                    // 最后一次，如果进了if分之，则刚好mid + 1处为大于等于num[i]的第一个数
                    if (tail[mid] < nums[i]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                // 此时low指向的就是tail中大于等于目标值nums[i]的第一个值
                tail[low] = nums[i];
            }
        }

        return end + 1;
    }


}
