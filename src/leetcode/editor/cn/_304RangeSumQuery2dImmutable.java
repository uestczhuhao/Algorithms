package leetcode.editor.cn;

public class _304RangeSumQuery2dImmutable {
    public static void main(String[] args) {
//        Solution t = new _304RangeSumQuery2dImmutable().new Solution();
        int[][] ns = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        _304RangeSumQuery2dImmutable.NumMatrix numMatrix = new _304RangeSumQuery2dImmutable().new NumMatrix(ns);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumMatrix {
        // 存放(0,0) 到 (i,j)的前缀和
        int[][] preSum;

        /**
         * 图示和思路：https://leetcode.cn/problems/range-sum-query-2d-immutable/solutions/629187/ru-he-qiu-er-wei-de-qian-zhui-he-yi-ji-y-6c21/
         * preSum的推倒思路：preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i-1][j-1]
         */
        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            preSum = new int[row + 1][col + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++;
            row2++;
            col1++;
            col2++;
            return preSum[row2][col2] - preSum[row2][col1 - 1] - preSum[row1 - 1][col2] + preSum[row1 - 1][col1 - 1];
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
