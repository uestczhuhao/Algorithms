package leetcode.editor.cn;

public class _36ValidSudoku {
    public static void main(String[] args) {
        Solution t = new _36ValidSudoku().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[][] row = new boolean[9][10];
            boolean[][] col = new boolean[9][10];
            boolean[][] cube = new boolean[9][10];
            // row[i][num]表示第i行有没有数字num；col[i][num]表示第i行有没有数字num；
            // cube[i][num]表示第i个cube有没有数字num，其中i的算法为col / 3 + row / 3 * 3
            // 把9*9的空间分成9个cube，从左到右，从上到下编号
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    int curNum = board[i][j] - '0';
                    if (row[i][curNum]) {
                        return false;
                    }
                    if (col[j][curNum]) {
                        return false;
                    }
                    int cubeIndex = j / 3 + i / 3 * 3;
                    if (cube[cubeIndex][curNum]) {
                        return false;
                    }
                    row[i][curNum] = true;
                    col[j][curNum] = true;
                    cube[cubeIndex][curNum] = true;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
