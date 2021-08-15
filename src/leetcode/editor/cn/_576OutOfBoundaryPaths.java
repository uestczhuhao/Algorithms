package leetcode.editor.cn;

//给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以
//穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。 
//
// 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 
//109 + 7 取余 后的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 50 
// 0 <= maxMove <= 50 
// 0 <= startRow < m 
// 0 <= startColumn < n 
// 
// Related Topics 动态规划 
// 👍 187 👎 0


public class _576OutOfBoundaryPaths {
    public static void main(String[] args) {
        Solution t = new _576OutOfBoundaryPaths().new Solution();
        System.out.println(t.findPaths(1, 3, 3, 0, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划，dp[i][j][k]代表位置在位置i,j处，移动步数为k时的方案数
         * dp[i][j][k] = dp[i - 1][j][k - 1] + dp[i + 1][j][k - 1] + dp[i][j - 1][k - 1] + dp[i][j + 1][k - 1];
         * 初始化：四条边界处初始化，当i为0或m-1时+1，同理j为0或n-1时也+1（当m==1时，dp[0][0][x] = dp[0][n-1][x] = 3，n==1时同理），
         * 且注意要初始化每一个k（1<=k<=maxMove），因为要求dp[i][j][k]时，所求的是k-1步中的上下左右四个方向之和，
         * 假设上方越界了，则求的是下左右三个方向，其实往上还有一种可能，因此把这种可能初始化到dp数组中
         */
        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

            int[][][] dp = new int[m][n][maxMove + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0) {
                        initDp(i, j, dp, maxMove);
                    }
                    if (j == 0) {
                        initDp(i, j, dp, maxMove);
                    }
                    if (i == m - 1) {
                        initDp(i, j, dp, maxMove);
                    }
                    if (j == n - 1) {
                        initDp(i, j, dp, maxMove);
                    }
                }
            }

            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            for (int k = 1; k <= maxMove; k++) {
                for (int i = 0; i <= m - 1; i++) {
                    for (int j = 0; j <= n - 1; j++) {
                        for (int[] d : dirs) {
                            int r = i + d[0];
                            int c = j + d[1];
                            // 当前方向越界，则换一个方向继续
                            if (r < 0 || r >= m || c < 0 || c >= n) {
                                continue;
                            }
                            dp[i][j][k] += dp[r][c][k - 1];
                            dp[i][j][k] %= 1000000007;
                        }

                    }
                }
            }

            return dp[startRow][startColumn][maxMove];
        }

        private void initDp(int i, int j, int[][][] dp, int maxMove) {
            for (int k = 1; k <= maxMove; k++) {
                dp[i][j][k]++;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}