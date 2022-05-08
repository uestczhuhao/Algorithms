package leetcode.week.competition;

import java.util.HashMap;
import java.util.Map;

public class _292 {
    public static void main(String[] args) {
        _292 t = new _292();
//        System.out.println(t.largestGoodInteger("42352338"));
//        TreeNode root = new TreeNode(4);
//        TreeNode node1 = new TreeNode(8);
//        TreeNode node2 = new TreeNode(5);
//        TreeNode node3 = new TreeNode(0);
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(6);
//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        node2.right = node5;
//        String s = "222222222222222222222222222222222222";
//
////        System.out.println(t.averageOfSubtree(root));
//        long a = (long) Math.pow(4, 12);
//        System.out.println(a);
//        char[][] grid = {{'(', '(', '('}, {')', '(', ')'}, {'(', '(', ')'}, {'(', '(', ')'}};
        char[][] grid =
            {{'(', '(', ')', ')', '(', ')', '(', ')', ')', '(', '(', ')', '(', ')', ')', ')', ')', ')', '(', ')', '(', ')', ')', '(', '(', '(', '(', ')', '(', ')', ')', ')', '(', ')', ')', ')', '(',
                ')', '(', ')', '(', '(', '(', ')'},
                {'(', ')', '(', '(', '(', ')', ')', ')', ')', '(', '(', '(', '(', ')', ')', ')', '(', '(', ')', '(', '(', ')', ')', '(', '(', ')', '(', '(', '(', ')', '(', '(', ')', '(', ')', '(',
                    ')', '(', '(', '(', '(', ')', ')', '('},
                {'(', ')', ')', '(', ')', '(', ')', '(', ')', ')', ')', '(', '(', '(', ')', ')', '(', ')', ')', '(', '(', ')', ')', '(', '(', ')', '(', ')', '(', '(', '(', '(', '(', '(', ')', '(',
                    ')', ')', ')', ')', '(', '(', '(', ')'},
                {')', '(', '(', ')', '(', '(', ')', '(', ')', '(', '(', ')', ')', ')', '(', ')', '(', '(', ')', '(', '(', '(', '(', '(', '(', ')', ')', '(', ')', ')', '(', ')', '(', '(', ')', ')',
                    ')', ')', '(', '(', '(', '(', ')', ')'},
                {')', ')', ')', '(', '(', '(', '(', ')', '(', '(', ')', '(', ')', ')', ')', ')', ')', '(', ')', '(', ')', '(', '(', ')', '(', '(', '(', ')', ')', '(', '(', ')', '(', '(', ')', '(',
                    ')', '(', ')', '(', ')', '(', ')', '('},
                {'(', '(', '(', ')', '(', '(', '(', ')', ')', ')', ')', ')', '(', '(', '(', '(', ')', ')', ')', '(', ')', '(', ')', '(', ')', ')', ')', ')', '(', '(', ')', '(', '(', ')', '(', '(',
                    ')', ')', '(', ')', ')', '(', '(', '('},
                {'(', ')', '(', '(', '(', '(', ')', ')', ')', '(', ')', '(', '(', ')', '(', '(', '(', ')', '(', ')', ')', ')', '(', '(', '(', ')', ')', ')', '(', ')', '(', '(', '(', ')', ')', '(',
                    '(', ')', '(', '(', ')', ')', ')', '('},
                {')', ')', '(', '(', ')', ')', '(', '(', ')', '(', '(', '(', ')', ')', '(', ')', '(', ')', ')', ')', ')', ')', ')', '(', ')', ')', ')', '(', ')', '(', '(', ')', ')', ')', '(', '(',
                    ')', ')', ')', ')', '(', ')', '(', '('},
                {'(', ')', ')', ')', '(', ')', ')', ')', '(', '(', ')', '(', '(', '(', ')', '(', '(', ')', '(', ')', '(', '(', '(', ')', ')', ')', '(', '(', ')', '(', '(', ')', '(', '(', '(', '(',
                    ')', '(', '(', '(', '(', ')', '(', ')'},
                {')', '(', '(', ')', '(', ')', '(', ')', '(', '(', ')', ')', ')', ')', '(', ')', '(', '(', '(', '(', ')', '(', '(', ')', ')', ')', ')', ')', '(', '(', ')', '(', ')', '(', '(', ')',
                    ')', ')', '(', ')', '(', '(', ')', '('},
                {'(', ')', ')', ')', '(', ')', ')', ')', '(', ')', ')', '(', '(', '(', '(', '(', '(', ')', '(', ')', ')', ')', '(', '(', '(', '(', '(', '(', '(', ')', '(', ')', ')', '(', '(', '(',
                    '(', '(', '(', ')', '(', '(', ')', ')'}};
        System.out.println(t.hasValidPath(grid));
    }

    int row, col;
    char[][] grids;
    /**
     * 在位置i，j时，左括号有k个，当visit[i][j][k]为true时，代表此位置被访问过，且没能走到最后（否则在那个位置就直接返回true了），直接返回false即可
     * 此剪枝能极大提升效率
     */
    boolean[][][] visit;

    public boolean hasValidPath(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        grids = grid;
        /**
         * 向下走row-1，向右走col-1，路径总长度为row - 1 + col-1 + 1（初始位置），
         * 则row + col - 1必须为偶数，否则没有匹配数量的正反括号
         */
        if (grid[0][0] == ')' || grid[row - 1][col - 1] == '(' || (row + col) % 2 == 0) {
            return false;
        }
        visit = new boolean[row][col][row + col];
        return checkPath(0, 0, 0);
    }

    private boolean checkPath(int rIndex, int cIndex, int leftPas) {
        leftPas += grids[rIndex][cIndex] == '(' ? 1 : -1;
        if (rIndex == row - 1 && cIndex == col - 1) {
            return leftPas == 0;
        }
        // 剩下的左括号小于0，则一定不合法；剩下的左括号比剩余路径更长，也肯定不合法
        // 剩下的路径：运行到i，j时，剩下往下的有row - 1 - rIndex，往右的有 col - 1 - cIndex
        // 路径总长度（包含i，j）为 (row - 1 - rIndex) + (col - 1 - cIndex) + 1
        if (leftPas < 0 || leftPas > row - rIndex + col - cIndex - 1) {
            return false;
        }
        if (visit[rIndex][cIndex][leftPas]) {
            return false;
        }
        visit[rIndex][cIndex][leftPas] = true;
        boolean down = false;
        boolean right = false;
        if (rIndex < row - 1) {
            down = checkPath(rIndex + 1, cIndex, leftPas);
        }
        if (cIndex < col - 1) {
            right = checkPath(rIndex, cIndex + 1, leftPas);
        }

        return down || right;
    }

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        calSumAndCount(root);
        return ans;
    }

    /**
     * 一次递归，同时获取子树的个数 & 和
     */
    private int[] calSumAndCount(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int[] left = new int[] {0, 0}, right = new int[] {0, 0};
        if (node.left != null) {
            left = calSumAndCount(node.left);
        }

        if (node.right != null) {
            right = calSumAndCount(node.right);
        }
        int subSum = left[0] + right[0] + node.val;
        int subCount = left[1] + right[1] + 1;
        // 由于是从下往上递归，当运行到node时，subNum和subCount已经计算完成
        if (node.val == subSum / subCount) {
            ans++;
        }
        return new int[] {subSum, subCount};
    }

    public String largestGoodInteger(String num) {
        String ans = "";
        int max = Integer.MIN_VALUE;
        for (int i = 0; i + 2 < num.length(); i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                int subInt = Integer.parseInt(num.substring(i, i + 3));
                if (subInt > max) {
                    ans = num.substring(i, i + 3);
                    max = subInt;
                }
            }
        }
        return ans;
    }
}
