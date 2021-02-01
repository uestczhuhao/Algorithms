package leetcode.editor.cn;

//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 动态规划 
// 👍 658 👎 0


public class _221MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new _221MaximalSquare().new Solution();
        char[][] grid = {
            {'1', '1', '1', '1', '1'},
            {'1', '0', '1', '0', '1'},
            {'1', '1', '1', '0', '1'},
            {'0', '0', '0', '0', '0'}
        };

        System.out.println(solution.maximalSquare(grid));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
         * 思路：dp[i][j] 表示以（i,j）为右下角的正方形边长
         * 其计算方式：dp[i][j] = min{dp[i - 1][j] ,dp[i][j - 1] ,dp[i - 1][j - 1]} + 1
         */
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int row = matrix.length;
            int column = matrix[0].length;
            int maxSideLen = 0;
            // 滚动数组
            int[] upperLevel = new int[column + 1];
            int[] currentLevel = new int[column + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    if (matrix[i - 1][j - 1] == '0') {
                        currentLevel[j] = 0;
                    } else {
                        int smaller = Math.min(upperLevel[j], upperLevel[j - 1]);
                        smaller = Math.min(smaller, currentLevel[j - 1]);
                        currentLevel[j] = smaller + 1;
                        if (currentLevel[j] > maxSideLen) {
                            maxSideLen = currentLevel[j];
                        }
                    }

                }
                upperLevel = currentLevel;
                currentLevel = new int[column + 1];
            }

            return maxSideLen * maxSideLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}