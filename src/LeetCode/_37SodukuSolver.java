package LeetCode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2020/4/17 22:09
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * <p>
 * <p>
 * 一个数独。
 * <p>
 * <p>
 * <p>
 * 答案被标成红色。
 * <p>
 * Note:
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class _37SodukuSolver {


    /**
     * 回溯+剪枝
     * 在所有 . 位置尝试放入1-9的数字，直到非法后回退
     * <p>
     * 剪枝：记录下行/列/小九宫格中已经出现过的数字，这些数字直接不考虑
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        solve(0, board);
    }

    public boolean solve(int curIndex, char[][] board) {
        if (curIndex >= 81) {
            System.out.println();
            return true;
        }

        while (curIndex < 81 && board[curIndex / 9][curIndex % 9] != '.') {
            curIndex++;
        }


        if (curIndex < 81) {
            for (char i = '1'; i <= '9'; i++) {
                int curRow = curIndex / 9;
                int curCol = curIndex % 9;

                if (checkValid(board, curRow, curCol, i)) {
                    board[curRow][curCol] = i;
                    if (solve(curIndex + 1, board)) {
                        return true;
                    }
                    board[curRow][curCol] = '.';
                }

            }
            return false;
        }

        return true;
    }

    private boolean checkValid(char[][] board, int row, int col, char candidate) {

        for (int i = 0; i < 9; i++) {
            // 验证同一行，除去当前位置是否出现过candidate
            if (i != col && board[row][i] == candidate) {
                return false;
            }

            // 验证同一列，除去当前位置是否出现过candidate
            if (i != row && board[i][col] == candidate) {
                return false;
            }
        }

        // 验证同一块，除去当前位置是否出现过candidate
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (i == row && j == col) {
                    continue;
                }

                if (board[i][j] == candidate) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] board1 = {{"5", "3", ".", ".", "7", ".", ".", ".", "."}, {"6", ".", ".", "1", "9", "5", ".", ".", "."}, {".", "9", "8", ".", ".", ".", ".", "6", "."}, {"8", ".", ".", ".", "6", ".", ".", ".", "3"}, {"4", ".", ".", "8", ".", "3", ".", ".", "1"}, {"7", ".", ".", ".", "2", ".", ".", ".", "6"}, {".", "6", ".", ".", ".", ".", "2", "8", "."}, {".", ".", ".", "4", "1", "9", ".", ".", "5"}, {".", ".", ".", ".", "8", ".", ".", "7", "9"}};
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = board1[i][j].charAt(0);
            }
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(board1[i]));
        }
        System.out.println();
        System.out.println();
        _37SodukuSolver t = new _37SodukuSolver();
        t.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
