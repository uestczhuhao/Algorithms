package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-4-16 下午4:55.
 * Description:
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class _52NQueensII {

    public static void main(String[] args) {
        _52NQueensII t = new _52NQueensII();
        System.out.println(t.totalNQueens(4));
    }

    /**
     * nums 皇后问题，回溯法
     * 将皇后放置在(i,j)位置，要检查i行，j列，主对角线（左上到右下）和副对角线（右上到左下）
     * 用二维数组表示N行N列到棋盘，每次都要遍历行，列和主/副对角线，太多冗余操作
     * 考虑用三个一维数组代替，具体见注释
     * <p>
     * <p>
     * 1 观察主对角线的元素，在同一条线上的元素，其｜行 - 列｜ = 定值
     * 2 副对角线，其 行+列 = 定值
     * 3 通过行遍历，每行放一个，没有冲突
     * 4 遍历列，查看同一列中是否有其他皇后
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        // 行数组，row[i] 代表i列放置了一个皇后
        int[] rows = new int[n];

        // 主对角线数组，由于其满足｜行 - 列｜ = 定值，因此长度为4*n-1，将绝对值打开，整体右移2*n
        int[] mainDiag = new int[4 * n - 1];

        // 次对角线，满足行+列 = 定值，其长度为2*n - 1
        int[] secondDiag = new int[2 * n - 1];
        countTotal(0, n, rows, mainDiag, secondDiag);
        return result;
    }

    int result = 0;

    public void countTotal(int curRow, int n, int[] rows, int[] mainDiag, int[] secondDiag) {
        if (curRow == n) {
            result++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (validPosition(curRow, col, n, rows, mainDiag, secondDiag)) {
                rows[col] =1;
                mainDiag[curRow - col + 2*n] = 1;
                secondDiag[curRow + col] = 1;

                countTotal(curRow + 1, n, rows, mainDiag, secondDiag);

                rows[col] =0;
                mainDiag[curRow - col + 2*n] = 0;
                secondDiag[curRow + col] = 0;
            }
        }
    }

    private boolean validPosition(int row, int col, int n, int[] rows, int[] mainDiag, int[] secondDiag) {
        return rows[col] + mainDiag[row - col + 2 * n] + secondDiag[row + col] == 0;
    }
}
