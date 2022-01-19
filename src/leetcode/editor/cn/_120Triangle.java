package leetcode.editor.cn;

//给定一个三角形 triangle ，找出自顶向下的最小路径和。
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
//
//
//
// 示例 1：
//
//
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
// 示例 2：
//
//
//输入：triangle = [[-10]]
//输出：-10
//
//
//
//
// 提示：
//
//
// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -10⁴ <= triangle[i][j] <= 10⁴
//
//
//
//
// 进阶：
//
//
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
//
// Related Topics 数组 动态规划 👍 932 👎 0


import java.util.Arrays;
import java.util.List;

public class _120Triangle {
    public static void main(String[] args) {
        Solution t = new _120Triangle().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划，dp[i][j] 表示从位置（i,j）到底部的最小路径和
         * 则dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]，答案即为dp[0][0]
         * <p>
         * 二维降至一维，dp[i] = min(dp[i+1],dp[i+1])+triangle[i][j]
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            List<Integer> lastLine = triangle.get(n - 1);
            int[] dp = new int[lastLine.size()];
            for (int i = 0; i < lastLine.size(); i++) {
                dp[i] = lastLine.get(i);
            }
            for (int i = n - 2; i >= 0; i--) {
                List<Integer> curLine = triangle.get(i);
                int len = curLine.size();
                // 由于要用到dp[j+1]，因此从前往后更新dp的值
                for (int j = 0; j < len; j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + curLine.get(j);
                }
            }
            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
