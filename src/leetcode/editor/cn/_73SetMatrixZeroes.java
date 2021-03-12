package leetcode.editor.cn;

//给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出: 
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2: 
//
// 输入: 
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出: 
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//] 
//
// 进阶: 
//
// 
// 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个常数空间的解决方案吗？ 
// 
// Related Topics 数组 
// 👍 366 👎 0


import java.util.Arrays;

public class _73SetMatrixZeroes {
    public static void main(String[] args) {
        Solution t = new _73SetMatrixZeroes().new Solution();
//        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}, {0, 10, 11, 12}, {13, 14, 15, 0}};
        t.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：用每一行/列的第一个元素标记此行/列需要置0
         * 其中matrix[0][0] 既是第一行的标识位，也是第一列的标识位
         * 因此需要两个额外的值判定是否是第一行/列需要置0
         */
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            boolean firsRow = false;
            boolean firstColumn = false;
            int row = matrix.length, column = matrix[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;

                        if (j == 0) {
                            firstColumn = true;
                        }

                        if (i == 0) {
                            firsRow = true;
                        }
                    }
                }
            }

            // 第一行和第一列已经在第一遍遍历中被当作标记置位，这里要先从第二行/列开始，之后再视情况置位第一行/列
            for (int i = row - 1; i > 0; i--) {
                for (int j = column - 1; j > 0; j--) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 设置第一行和第一列
            if (firstColumn) {
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }

            if (firsRow) {
                for (int j = 0; j < column; j++) {
                    matrix[0][j] = 0;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}