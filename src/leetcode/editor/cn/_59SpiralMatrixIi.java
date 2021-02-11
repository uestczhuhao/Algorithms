package leetcode.editor.cn;

//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 
// 👍 297 👎 0


import java.util.Arrays;

public class _59SpiralMatrixIi {
    public static void main(String[] args) {
        Solution t = new _59SpiralMatrixIi().new Solution();
        System.out.println(Arrays.deepToString(t.generateMatrix(1)));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] generateMatrix(int n) {
            if (n < 0) {
                return null;
            }

            int[][] answer = new int[n][n];
            int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int current = 1;
            int row = 0, column = 0;
            int curDirIndex = 0;
            while (current <= n * n) {
                answer[row][column] = current;
                current++;
                int nextRow = row + direction[curDirIndex][0];
                int nextColumn = column + direction[curDirIndex][1];
                if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || answer[nextRow][nextColumn] != 0) {
                    curDirIndex = (curDirIndex + 1) % 4;
                }

                row = row + direction[curDirIndex][0];
                column = column + direction[curDirIndex][1];
            }
            return answer;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}