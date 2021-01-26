package LeetCode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2021/1/26 22:35
 */
public class _62UniquePath {
    /**
     * 动态规划，dp[i][j] = dp[i-1][j] + dp[i][j-1]
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        // 初始化，dp[0][j] = 1，原因是只有向右走这一条路
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        _62UniquePath t = new _62UniquePath();
        System.out.println(t.uniquePaths(3, 7));
    }
}
