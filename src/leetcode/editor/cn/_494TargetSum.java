package leetcode.editor.cn;

//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选
//择一个符号添加在前面。 
//
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。 
//
// 
//
// 示例： 
//
// 输入：nums: [1, 1, 1, 1, 1], S: 3
//输出：5
//解释：
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//一共有5种方法让最终目标和为3。
// 
//
// 
//
// 提示： 
//
// 
// 数组非空，且长度不会超过 20 。 
// 初始的数组的和不会超过 1000 。 
// 保证返回的最终结果能被 32 位整数存下。 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 547 👎 0


public class _494TargetSum {
    public static void main(String[] args) {
        Solution t = new _494TargetSum().new Solution();
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        t.findTargetSumWays(nums, 1);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：动态规划，dp[i][j] 表示前i个数，组成和为j的种类个数
         * 直接的递推公式为dp[i][j] =  dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]]
         * 也可以转化为下面的公式
         * 若第i个数取 +，则dp[i][j + nums[i]] += dp[i-1][j]
         * 若第i个数取 -，则dp[i][j - nums[i]] += dp[i-1][j]
         */
        public int findTargetSumWays1(int[] nums, int S) {
            if (nums == null || nums.length == 0 || S > 1000) {
                return 0;
            }

            // 所有的值加个偏移量，保证下标为正
            int offset = 1000;
            int len = nums.length;
            // 列数保持足够，防止有num[m] = 1000的情况发生
            int[][] dp = new int[len][2001];
            // 初始化dp[0][nums[0]] 和 dp[0][ -nums[0]] 为 1
            dp[0][nums[0] + offset] = 1;
            // 此处注意nums[0] == 0时，dp[0][0+offset] = 2
            // 原因是0取 + - 是两种可能，且两种可能性都满足题意
            dp[0][-nums[0] + offset] += 1;
            for (int i = 1; i < len; i++) {
                // 从上往下，从最左往右计算，保持结果不被漏掉
                for (int j = 0; j <= 2 * offset; j++) {
                    // 跳过不需要累加的结果
                    if (dp[i - 1][j] > 0) {
                        dp[i][j + nums[i]] += dp[i - 1][j];
                        dp[i][j - nums[i]] += dp[i - 1][j];
                    }
                }
            }

            return dp[len - 1][S + 1000];

        }

        /**
         * 空间优化
         */
        public int findTargetSumWays(int[] nums, int S) {
            if (nums == null || nums.length == 0 || S > 1000) {
                return 0;
            }

            int offset = 1000;
            int len = nums.length;
            int[] dp = new int[2001];
            dp[nums[0] + offset] = 1;
            dp[-nums[0] + offset] += 1;
            for (int i = 1; i < len; i++) {
                int[] next = new int[2001];
                for (int j = 0; j <= 2000; j++) {
                    if (dp[j] > 0) {
                        next[nums[i] + j] += dp[j];
                        next[-nums[i] + j] += dp[j];
                    }
                }
                dp = next;
            }

            return dp[S + offset];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}