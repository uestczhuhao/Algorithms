package leetcode.editor.cn;

//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 分治算法 动态规划 
// 👍 627 👎 0


public class _312BurstBalloons {
    public static void main(String[] args) {
        Solution t = new _312BurstBalloons().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：选定区间 i j 开区间，dp[i][j] 表示ij之间戳气球的最大收益
         * 对这个区间的任意一个气球k，不失一般性，不妨假设它就是最后一个被戳破的
         * 戳破它的收益为 max{val[i]*val[j]*val[k] + dp[i][k] + dp[k][j]}
         * 对所有对位置都做这种假设，则当i = -1， j = n + 1 时即为解
         */
        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int len = nums.length;
            int[] values = new int[len + 2];
            values[0] = values[len + 1] = 1;
            System.arraycopy(nums, 0, values, 1, len + 1 - 1);
            // 数组长度大2，模拟-1和n+1的位置
            int[][] dp = new int[len + 2][len + 2];
            // i 和 j 的间距从1开始
            // 由于是开区间，因此i从len - 1（倒数第三个）开始，j从 i+2 开始
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i + 2; j <= len + 1; j++) {
                    int curMax = -1;
                    for (int k = i + 1; k < j; k++) {
                        int kThIncome = values[i] * values[j] * values[k];
                        curMax = Math.max(curMax, kThIncome + dp[i][k] + dp[k][j]);
                    }
                    dp[i][j] = curMax;
                }
            }


            return dp[0][len + 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}