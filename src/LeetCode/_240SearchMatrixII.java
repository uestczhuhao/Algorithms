package LeetCode;

/**
 * @author mizhu
 * @date 2020/11/8 23:24
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 */
public class _240SearchMatrixII {

    private int[][] matrix;
    private int target;

    /**
     * 方法1：剪枝，找到刚好比target大的值matrix[row][mid] (mid为中间列)
     * 通过row 和 mid把矩阵分为4部分
     * 1 左上部分，都比target小，剪掉
     * 2 右上部分，可能存在target，保留
     * 3 左下部分，可能存在target，保留
     * 4 右下部分，都比target大，剪掉
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        this.matrix = matrix;
        this.target = target;
        return doSearchMatrix(0, matrix[0].length - 1, 0, matrix.length - 1);
    }

    /**
     * 在子矩阵中搜索target
     *
     * @param left  矩阵最左
     * @param right 矩阵最右
     * @param up    矩阵最上
     * @param down  矩阵最下
     */
    private boolean doSearchMatrix(int left, int right, int up, int down) {
        // 越界
        if (left > right || up > down) {
            return false;
        }

        // target比子矩阵的最小值小 或 最大值大，子矩阵肯定不包含target
        if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        // 找到子矩阵的中心
        int mid = left + (right - left) / 2;
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        // 由于matrix[row][mid] > target，所以左下矩阵的列可以安全向左平移1（唯独不能向下平移）
        return doSearchMatrix(left, mid - 1, row, down) ||
            // 同理，右上矩阵可以向右平移1，向上平移1
            doSearchMatrix(mid + 1, right, up, row - 1);
    }

    /**
     * 从左下角开始，一直找到右上角
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        int column = 0;
        /*
         * 思路：从左下角到右上角
         * 比目标值大则向上，比目标值小则向右
         * 要么在途中找到目标值，要么越界
         * 时间复杂度：O(n)
         */
        while (row >= 0 && column <= matrix[0].length - 1) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                row--;
            } else {
                column++;
            }
        }
        return false;
    }

}
