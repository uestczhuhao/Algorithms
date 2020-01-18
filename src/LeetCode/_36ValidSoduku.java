package LeetCode;

import java.util.HashSet;

/**
 * Created by mizhu on 19-7-20 下午10:23
 */
public class _36ValidSoduku {
    public static void main(String[] args) {

    }

    /**
     * 9*9 矩阵，对每一次的内层循环（j=0~9）
     * 1. i为行，j为列，判断行
     * 2. j为行，i为列，判断列
     * 3. 判断小九宫格的合法性
     *    例如： i=0 时，判断row=0~2  col=0~2的九宫格合法性
     *          i=1 时，判断row=0~2  col=3~5的九宫格合法性
     *          i=2 时，判断row=0~2  col=6~8的九宫格合法性
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9){
            return false;
        }

        for (int i=0;i<9;i++){
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> col = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j=0;j<9;j++){
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !col.add(board[j][i])){
                    return false;
                }

                // 初始行的要求： i=0~3:0   3~5:3  6~8:6
                int rowIndex = 3*(i/3);

                // 初始列的要求： i=0,3,6:0   1,4,7:3  2,5,8:6
                int colIndex = 3*(i%3);

                // 动态：在 j=0~9时，遍历一个九宫格，即 row + 0~2  col + 0~2
                if (board[rowIndex + j/3][colIndex + j%3]!='.' && !cube.add(board[rowIndex + j/3][colIndex + j%3])) {
                    return false;
                }
            }
        }
        return true;
    }
}
