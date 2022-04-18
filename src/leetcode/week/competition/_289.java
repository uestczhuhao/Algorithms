package leetcode.week.competition;

import java.util.HashMap;
import java.util.Map;

public class _289 {
    public static void main(String[] args) {
        _289 t = new _289();
        String s = "1114";
//        System.out.println(t.digitSum(s, 3));
//        int[] tasks = {2, 3, 3};
//        System.out.println(t.minimumRounds(tasks));
        int[][] grid = {{437, 230, 648, 905, 744, 416}, {39, 193, 421, 344, 755, 154}, {480, 200, 820, 226, 681, 663}, {658, 65, 689, 621, 398, 608}, {680, 741, 889, 297, 530, 547},
            {809, 760, 975, 874, 524, 717}};
        System.out.println(t.maxTrailingZeros(grid));
    }

    // https://blog.csdn.net/IAMLSL/article/details/116400270
    public int longestPath(int[] parent, String s) {
        return 0;
    }

    public int maxTrailingZeros(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        // 存放本行到i,j 位置的2的个数
        int[][] twoRowSum = new int[row + 1][col + 1];
        // 存放本列到i,j 位置的2的个数
        int[][] twoColSum = new int[row + 1][col + 1];
        // 存放本行到i,j 位置的5的个数
        int[][] fiveRowSum = new int[row + 1][col + 1];
        // 存放本列到i,j 位置的5的个数
        int[][] fiveColSum = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int cur = grid[i - 1][j - 1];
                int two = 0, five = 0;
                while (cur % 2 == 0) {
                    two++;
                    cur /= 2;
                }
                while (cur % 5 == 0) {
                    five++;
                    cur /= 5;
                }
                twoRowSum[i][j] = two + twoRowSum[i][j - 1];
                twoColSum[i][j] = two + twoColSum[i - 1][j];
                fiveRowSum[i][j] = five + fiveRowSum[i][j - 1];
                fiveColSum[i][j] = five + fiveColSum[i - 1][j];
            }
        }

        int maxZero = 0;
        // 乘积尾随零的数量是所有乘数中因子2数量之和与因子5数量之和中的较小值。
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 从左边出发，到上边结束
                maxZero = Math.max(maxZero, Math.min(twoRowSum[i][j] + twoColSum[i - 1][j], fiveRowSum[i][j] + fiveColSum[i - 1][j]));
                // 从左边出发，到下边结束
                maxZero = Math.max(maxZero, Math.min(twoRowSum[i][j] + twoColSum[row][j] - twoColSum[i][j], fiveRowSum[i][j] + fiveColSum[row][j] - fiveColSum[i][j]));
                // 从右边出发，到上边结束
                maxZero = Math.max(maxZero, Math.min(twoRowSum[i][col] - twoRowSum[i][j] + twoColSum[i][j], fiveRowSum[i][col] - fiveRowSum[i][j] + fiveColSum[i][j]));
                // 从右边出发，到下边结束
                maxZero = Math.max(maxZero,
                    Math.min(twoRowSum[i][col] - twoRowSum[i][j] + twoColSum[row][j] - twoColSum[i - 1][j], fiveRowSum[i][col] - fiveRowSum[i][j] + fiveColSum[row][j] - fiveColSum[i - 1][j]));
            }
        }
        return maxZero;
    }

//    public int maxTrailingZeros(int[][] grid) {
//        int n = grid.length, m = grid[0].length;
//        // 存放本行到i,j 位置的2的个数
//        int[][] f2 = new int[n + 1][m + 1];
//        // 存放本列到i,j 位置的2的个数
//        int[][] g2 = new int[n + 1][m + 1];
//        // 存放本行到i,j 位置的5的个数
//        int[][] f5 = new int[n + 1][m + 1];
//        // 存放本列到i,j 位置的5的个数
//        int[][] g5 = new int[n + 1][m + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                int cur = grid[i - 1][j - 1];
//                int two = 0, five = 0;
//                while (cur % 2 == 0) {
//                    two++;
//                    cur /= 2;
//                }
//                while (cur % 5 == 0) {
//                    five++;
//                    cur /= 5;
//                }
//                f2[i][j] = two + f2[i][j - 1];
//                g2[i][j] = two + g2[i - 1][j];
//                f5[i][j] = five + f5[i][j - 1];
//                g5[i][j] = five + g5[i - 1][j];
//            }
//        }
//
//        int ans = 0;
//        // 乘积尾随零的数量是所有乘数中因子 22 数量之和与因子 55 数量之和中的较小值。
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                // 从左边出发，到上边结束
//                ans = Math.max(ans, Math.min(f2[i][j] + g2[i - 1][j], f5[i][j] + g5[i - 1][j]));
//                ans = Math.max(ans, Math.min(f2[i][j] + g2[i - 1][j], f5[i][j] + g5[i - 1][j]));
//
//                // 从左边出发，到下边结束
//                ans = Math.max(ans, Math.min(f2[i][j] + g2[n][j] - g2[i][j], f5[i][j] + g5[n][j] - g5[i][j]));
//                // 从右边出发，到上边结束
//                ans = Math.max(ans, Math.min(f2[i][m] - f2[i][j] + g2[i][j], f5[i][m] - f5[i][j] + g5[i][j]));
//                // 从右边出发，到下边结束
//                ans = Math.max(ans, Math.min(f2[i][m] - f2[i][j] + g2[n][j] - g2[i - 1][j], f5[i][m] - f5[i][j] + g5[n][j] - g5[i - 1][j]));
//
//                // 从左边出发，到下边结束
//                ans = Math.max(ans, Math.min(f2[i][j] + g2[n][j] - g2[i][j], f5[i][j] + g5[n][j] - g5[i][j]));
//                // 从右边出发，到上边结束
//                ans = Math.max(ans, Math.min(f2[i][m] - f2[i][j] + g2[i][j], f5[i][m] - f5[i][j] + g5[i][j]));
//                // 从右边出发，到下边结束
//                ans = Math.max(ans, Math.min(f2[i][m] - f2[i][j] + g2[n][j] - g2[i - 1][j], f5[i][m] - f5[i][j] + g5[n][j] - g5[i - 1][j]));
//
//            }
//        }
//        return ans;
//    }

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> taskFreqMap = new HashMap<>();
        for (int task : tasks) {
            taskFreqMap.put(task, taskFreqMap.getOrDefault(task, 0) + 1);
        }

        long total = 0;
        for (int cost : taskFreqMap.values()) {
            if (cost == 1) {
                return -1;
            }
            if (cost % 3 == 0) {
                total += cost / 3;
            } else {
                total += cost / 3 + 1;
            }
        }
        return (int) total;
    }

    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            while (index * k < s.length()) {
                String cur = s.substring(k * index, Math.min((index + 1) * k, s.length()));
                int sum = 0;
                for (int i = 0; i < cur.length(); i++) {
                    sum += cur.charAt(i) - '0';
                }
                sb.append(sum);
                index++;
            }
            s = sb.toString();
        }
        return s;
    }


}
