package leetcode.editor.cn;

//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 475 👎 0


import java.util.Deque;
import java.util.LinkedList;

public class _130SurroundedRegions {
    public static void main(String[] args) {
        Solution t = new _130SurroundedRegions().new Solution();
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        t.solve(board);
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[][] board;
        int row, column;

        /**
         * 思路：由于边界上的O不能被包围，可以推得只有和边界的O连通的才不被包围
         * 利用dfs 或 bfs，寻找和边界O相连的区域，标记之（改成#）
         * 最后把没有标记的改成X，标记的#改回O即可
         */
        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return;
            }

            this.board = board;
            row = board.length;
            column = board[0].length;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    // 如果是边界的O
                    if (board[i][j] == 'O' && (i == 0 || j == 0 || i == row - 1 || j == column - 1)) {
                        dfsWithoutRecursion(i, j);
                    }
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    // 先改O，否则#的改成O，紧接着被改成X
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }

                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }

        }

        /**
         * 递归版dfs
         */
        public void dfs(int curRow, int curColumn) {
            // 不合法位置和已经访问过的位置，直接返回
            if (curRow < 0 || curRow >= row || curColumn < 0 || curColumn >= column
                || board[curRow][curColumn] == '#' || board[curRow][curColumn] == 'X') {
                return;
            }

            board[curRow][curColumn] = '#';
            // 上下左右
            dfs(curRow - 1, curColumn);
            dfs(curRow + 1, curColumn);
            dfs(curRow, curColumn - 1);
            dfs(curRow, curColumn + 1);
        }

        /**
         * 非递归版dfs，用栈记录走过的路径
         * ps：不如递归版的快
         */
        public void dfsWithoutRecursion(int curRow, int curColumn) {
            if (curRow < 0 || curRow >= row || curColumn < 0 || curColumn >= column
                || board[curRow][curColumn] == '#' || board[curRow][curColumn] == 'X') {
                return;
            }

            Deque<Point> stack = new LinkedList<>();
            stack.push(new Point(curRow, curColumn));
            while (!stack.isEmpty()) {
                Point point = stack.peek();
                int rowNum = point.x;
                int columnNum = point.y;
                board[rowNum][columnNum] = '#';
                // 处理当前位置的上下左右位置
                if (rowNum - 1 >= 0 && board[rowNum - 1][point.y] == 'O') {
                    stack.push(new Point(rowNum - 1, columnNum));
                    continue;
                }
                if (rowNum + 1 < row && board[rowNum + 1][point.y] == 'O') {
                    stack.push(new Point(rowNum + 1, columnNum));
                    continue;
                }
                if (columnNum - 1 >= 0 && board[rowNum][columnNum - 1] == 'O') {
                    stack.push(new Point(rowNum, columnNum - 1));
                    continue;
                }
                if (columnNum + 1 < column && board[rowNum][columnNum + 1] == 'O') {
                    stack.push(new Point(rowNum, columnNum + 1));
                    continue;
                }
                // 前后左右都不满足条件，则弹出
                stack.pop();
            }
        }

        public void bfsWithoutRecursion(int curRow, int curColumn) {
            if (curRow < 0 || curRow >= row || curColumn < 0 || curColumn >= column
                || board[curRow][curColumn] == '#' || board[curRow][curColumn] == 'X') {
                return;
            }

            Deque<Point> stack = new LinkedList<>();
            stack.add(new Point(curRow, curColumn));
            while (!stack.isEmpty()) {
                Point point = stack.peek();
                int rowNum = point.x;
                int columnNum = point.y;
                board[rowNum][columnNum] = '#';
                // 处理当前位置的上下左右位置
                if (rowNum - 1 >= 0 && board[rowNum - 1][point.y] == 'O') {
                    stack.add(new Point(rowNum - 1, columnNum));
                }
                if (rowNum + 1 < row && board[rowNum + 1][point.y] == 'O') {
                    stack.add(new Point(rowNum + 1, columnNum));
                }
                if (columnNum - 1 >= 0 && board[rowNum][columnNum - 1] == 'O') {
                    stack.add(new Point(rowNum, columnNum - 1));
                }
                if (columnNum + 1 < column && board[rowNum][columnNum + 1] == 'O') {
                    stack.add(new Point(rowNum, columnNum + 1));
                }
                // 前后左右都不满足条件，则弹出
                stack.remove();
            }
        }


        private class Point {
            int x;
            int y;

            public Point() {
            }

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}