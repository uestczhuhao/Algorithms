package leetcode;

/**
 * @author mizhu
 * @date 20-3-17 下午10:59
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class _64MinimumPathSum {

    /**
     * 简单dp，从左上角开始，取从上和从左边来的更小值即可
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i > 0 && j > 0) {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                } else if (i > 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else if (j > 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, column = grid[0].length;
        int[] dp = new int[column];
        // 初始化dp第一行
        for (int j = 0; j < column; j++) {
            dp[j] = j == 0 ? grid[0][0] : dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                dp[j] = j == 0 ? dp[j] + grid[i][j] : Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        return dp[column - 1];
    }


    public static void main(String[] args) {
        _64MinimumPathSum t = new _64MinimumPathSum();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(t.minPathSum1(grid));
    }
}
