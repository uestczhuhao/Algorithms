package leetcode.editor.cn;

//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 
// 👍 603 👎 0


import java.util.ArrayList;
import java.util.List;

public class _54SpiralMatrix {
    public static void main(String[] args) {
        Solution t = new _54SpiralMatrix().new Solution();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(t.spiralOrder(matrix));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：
         * 1 初始化四个方向：右-下-左-上，按顺序遍历
         * 2 利用visited数组跟踪已访问的元素，遇到已访问的元素则调转方向
         * 3 记录当前为止的总访问元素个数，访问数达到总节点个数则退出
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> answer = new ArrayList<>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return answer;
            }

            int rows = matrix.length, columns = matrix[0].length;
            int totalNum = rows * columns, visitNum = 0;
            boolean[][] visited = new boolean[rows][columns];
            int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int row = 0, column = 0;
            int curDirIndex = 0;
            while (visitNum < totalNum) {
                visited[row][column] = true;
                answer.add(matrix[row][column]);
                visitNum++;

                int nextRow = row + direction[curDirIndex][0];
                int nextColumn = column + direction[curDirIndex][1];
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
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