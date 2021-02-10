package leetcode.editor.cn;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 745 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _51NQueens {
    public static void main(String[] args) {
        Solution t = new _51NQueens().new Solution();
        List<List<String>> list = t.solveNQueens(4);
        System.out.println(list);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int nQueens;

        /**
         * 思路：当某个位置放了Q之后，其同一行，同一列，同一斜线都不能再放置Q，可以采用以下策略
         * 1 一行一个，依据row循环
         * 2 column设置一个集合，存放当前哪些列已经放置了Q，则当前列不能再放置Q
         * 3 斜线分为两个，左上-右下 + 右上-左下，前者规律是 行 - 列 = 常量；后者规律是 行 + 列 = 常数
         */
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> answer = new ArrayList<>();
            if (n <= 0) {
                return answer;
            }
            nQueens = n;

            // 第i个位置放置第i行放置Q的列
            // 如queenColumn[0] = 5，代表第一行放置的Q在第6（0开始）列
            int[] queenColumn = new int[n];
            Arrays.fill(queenColumn, -1);
            doSolveQueens(answer, 0, queenColumn, new HashSet<>(), new HashSet<>(), new HashSet<>());
            return answer;
        }

        /**
         * @param answer        结果集合
         * @param row           当前行
         * @param columns       列集合
         * @param leftDiagonal  左上-右下对角线
         * @param rightDiagonal 右上-左下对角线
         */
        private void doSolveQueens(List<List<String>> answer, int row, int[] queenColumn, Set<Integer> columns, Set<Integer> leftDiagonal, Set<Integer> rightDiagonal) {
            if (row == nQueens) {
                answer.add(generateQueens(queenColumn));
                return;
            }

            for (int column = 0; column < nQueens; column++) {
                if (columns.contains(column)) {
                    continue;
                }
                int leftRightConstant = row - column;
                if (leftDiagonal.contains(leftRightConstant)) {
                    continue;
                }
                int rightLeftConstant = row + column;
                if (rightDiagonal.contains(rightLeftConstant)) {
                    continue;
                }

                queenColumn[row] = column;
                columns.add(column);
                leftDiagonal.add(leftRightConstant);
                rightDiagonal.add(rightLeftConstant);
                doSolveQueens(answer, row + 1, queenColumn, columns, leftDiagonal, rightDiagonal);
                queenColumn[row] = -1;
                columns.remove(column);
                leftDiagonal.remove(leftRightConstant);
                rightDiagonal.remove(rightLeftConstant);

            }
        }

        public List<String> generateQueens(int[] queenColumn) {
            List<String> positions = new ArrayList<>();
            for (int row = 0; row < nQueens; row++) {
                char[] curRow = new char[nQueens];
                Arrays.fill(curRow, '.');
                curRow[queenColumn[row]] = 'Q';
                positions.add(new String(curRow));
            }
            return positions;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}