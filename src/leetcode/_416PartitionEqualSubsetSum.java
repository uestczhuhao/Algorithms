package leetcode;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/3/10 23:15
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class _416PartitionEqualSubsetSum {

    public static void main(String[] args) {
        _416PartitionEqualSubsetSum t = new _416PartitionEqualSubsetSum();
        int[] nums = {1, 5, 11, 5};
        System.out.println(t.canPartition(nums));
        System.out.println(t.canPartition1(nums));
    }

    /**
     * 动态规划，背包问题
     * dp[i][j] 代表，从0～i-1 中选一些数，其和等于j是否为真
     * 因此i为数组长度，j为数组和的一半（若和为奇数，直接返回false）
     */
    public boolean canPartition(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 若和为奇数，则返回false
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 第一行只有一个值可能为true，就是dp[0][j]，其中j == nums[0]
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        /* 递推公式：dp[i][j] = （dp[i-1][j] || dp[i-1][j-num[i]]） || (nums[i] == j)
            若0～i-1 已经有值满足和为j，则0～i必定也满足条件
            若0～i-1 有值满足j-x，x值恰好为第i个值，那么0～i必定存在和为j的值
            若第i个值已经等于j，则直接满足条件
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][target];
    }

    /**
     * 缩减到一维数组
     */
    public boolean canPartition1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        // 二维数组缩减为一维
        // 其含义和上面函数一样
        boolean[] dp = new boolean[target];
        dp[0] = true;
        for (int num : nums) {
            // 当遍历到数num时，若target - num 为真，代表前面的数和为target - num
            // 加上num，正好和为target，可以直接返回true
            if (target >= num && dp[target - num]) {
                return true;
            }
            // 上面的if分支已经讨论了dp[target]的情况，这里可以安全的跳过
            // 此处必须从后往前，因为随着num的加入，求和结果会更大，所以前面的不会被覆盖
            for (int i = target - num - 1; i >= 0; i--) {
                if (dp[i]) {
                    dp[i + num] = true;
                }
            }
        }
        return false;
    }

}
