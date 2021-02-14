package leetcode.editor.cn;

//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 316 👎 0


public class _74SearchA2dMatrix {
    public static void main(String[] args) {
        Solution t = new _74SearchA2dMatrix().new Solution();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 12, 13}, {15, 19, 20, 22}};
        System.out.println(t.searchMatrix(matrix, 3));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路，标准的二维查找
         */
        int[][] tgtMatrix;
        int row, column;

        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            tgtMatrix = matrix;
            row = matrix.length;
            column = matrix[0].length;
            int low = 0, high = row * column - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midValue = matrixValue(mid);
                if (midValue == target) {
                    return true;
                } else if (midValue > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return false;
        }

        private int matrixValue(int flatIndex) {
            int rowIndex = flatIndex / column;
            int columnIndex = flatIndex - rowIndex * column;
            return tgtMatrix[rowIndex][columnIndex];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}