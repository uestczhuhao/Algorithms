package leetcode.editor.cn;

//在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。 
//
// 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足： 
//
// 
// nums1[i] == nums2[j] 
// 且绘制的直线不与任何其他连线（非水平线）相交。 
// 
//
// 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。 
//
// 以这种方法绘制线条，并返回可以绘制的最大连线数。 
//
// 
//
// 示例 1： 
//
//
// 
//输入：nums1 = [1,4,2], nums2 = [1,2,4]
//输出：2
//解释：可以画出两条不交叉的线，如上图所示。 
//但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相
//交。
// 
//
// 
// 示例 2： 
//
// 
//输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
//输出：3
// 
//
// 
// 示例 3： 
//
// 
//输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
//输出：2 
//
// 
// 
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 500 
// 1 <= nums2.length <= 500 
// 1 <= nums1[i], nums2[i] <= 2000 
// 
//
// 
// Related Topics 数组 
// 👍 139 👎 0


public class _1035UncrossedLines {
    public static void main(String[] args) {
        Solution t = new _1035UncrossedLines().new Solution();
        int[] nums1 = {3};
        int[] nums2 = {3, 3, 2};
        System.out.println(t.maxUncrossedLines(nums1, nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 最长公共子序列
         */
        public int maxUncrossedLines1(int[] nums1, int[] nums2) {
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            return dp[nums1.length][nums2.length];
        }

        /**
         * 二维降为一维
         */
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            // 代表i-1行
            int[] dp = new int[nums2.length + 1];
            // 代表i行
            int[] tmp;
            for (int i = 1; i <= nums1.length; i++) {
                tmp = new int[nums2.length + 1];
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        tmp[j] = dp[j - 1] + 1;
                    } else {
                        tmp[j] = Math.max(tmp[j - 1], dp[j]);
                    }
                }
                dp = tmp;
            }

            return dp[nums2.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}