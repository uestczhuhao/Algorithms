package leetcode.editor.cn;

//给你一个 非递减 有序整数数组 nums 。
//
// 请你建立并返回一个整数数组 result，它跟 nums 长度相同，且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。
//
// 换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，其中 0 <= j < nums.length 且 j != i （
//下标从 0 开始）。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,5]
//输出：[4,3,5]
//解释：假设数组下标从 0 开始，那么
//result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
//result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
//result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
//
//
// 示例 2：
//
//
//输入：nums = [1,4,6,8,10]
//输出：[24,15,13,15,21]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 10⁵
// 1 <= nums[i] <= nums[i + 1] <= 10⁴
//
// Related Topics 数组 数学 前缀和 👍 20 👎 0


public class _1685SumOfAbsoluteDifferencesInASortedArray {
    public static void main(String[] args) {
        Solution t = new _1685SumOfAbsoluteDifferencesInASortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 先求左边前缀和left，再求右边后缀和right，第i个位置的答案为left[i] + right[i]
         * <p>
         * left数组：left[i]为左边所有数到i的绝对值之和，考虑left[i-1]，从left[i-1]到left[i]
         * 只需要把nums[i] - nums[i-1]这段距离加 i 次即可
         */
        public int[] getSumAbsoluteDifferences(int[] nums) {
            int len = nums.length;
            int[] left = new int[len];
            int[] right = new int[len];
            int[] ans = new int[len];
            for (int i = 1; i < len; i++) {
                left[i] = left[i - 1] + i * (nums[i] - nums[i - 1]);
            }

            for (int i = len - 2; i >= 0; i--) {
                right[i] = right[i + 1] + (len - 1 - i) * (nums[i + 1] - nums[i]);
                ans[i] = left[i] + right[i];
            }
            ans[len - 1] = left[len - 1];
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
