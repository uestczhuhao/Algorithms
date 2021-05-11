package leetcode.editor.cn;

//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 示例 1： 
//
// 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 
// Related Topics 递归 动态规划 
// 👍 353 👎 0


import java.util.Arrays;

public class _698PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution t = new _698PartitionToKEqualSumSubsets().new Solution();
        int[] nums = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3};
        System.out.println(t.canPartitionKSubsets(nums, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) {
                return false;
            }
            Arrays.sort(nums);
            int limit = sum / k;
            return dfs(nums, nums.length - 1, new int[k], limit);
        }

        private boolean dfs(int[] nums, int end, int[] sub, int limit) {
            if (end < 0) {
                return true;
            }

            int cur = nums[end];
            for (int i = 0; i < sub.length; i++) {
                if (sub[i] + cur <= limit) {
                    sub[i] += cur;
                    if (dfs(nums, end - 1, sub, limit)) {
                        return true;
                    }
                    sub[i] -= cur;
                }

                // 当第i个集合是0，且后续返回均为false时，
                // 把cur放入第i+1个也必定不合法
                if (sub[i] == 0) {
                    break;
                }
            }

            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}