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
//        System.out.println(t.hasValidPath(grid));
//        System.out.println(t.countTexts("222222222222222222222222222222222222"));
        System.out.println(t.countTexts("88888888888888888888888888888999999999999999999999999999994444444444444444444444444444488888888888888888888888888888555555555555555555555555555556666666666666666666666666666666666666666666666666666666666222222222222222222222222222226666666666666666666666666666699999999999999999999999999999888888888888888888888888888885555555555555555555555555555577777777777777777777777777777444444444444444444444444444444444444444444444444444444444433333333333333333333333333333555555555555555555555555555556666666666666666666666666666644444444444444444444444444444999999999999999999999999999996666666666666666666666666666655555555555555555555555555555444444444444444444444444444448888888888888888888888888888855555555555555555555555555555555555555555555555555555555555555555555555555555555555999999999999999555555555555555555555555555554444444444444444444444444444444555"));
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


    /**
     * 把原来的字符串分组，各组内的字符相同，每一个组的结果相乘
     * 对每个分组，考虑字符不为 7 或 9 的情况，定义 f[i] 表示长为 i 的只有一种字符的字符串对应的文字信息种类数，
     * 我们可以将末尾的 1 个、2 个或 3 个字符单独视作一个字母，那么有转移方程 f[i] = f[i-1]+f[i-2]+f[i-3]
     * 对于字符为 7 或 9 的情况，定义 g[i]g[i] 表示长为 i 的只有一种字符的字符串对应的文字信息种类数，
     * 可以得到类似的转移方程 g[i] = g[i-1]+g[i-2]+g[i-3]+g[i-4]
     */
    public int countTexts(String pressedKeys) {
        int len = pressedKeys.length();
        int maxLen = 100_010;
        long[] three = new long[maxLen];
        long[] four = new long[maxLen];
        three[0] = 1;
        three[1] = 1;
        three[2] = 2;
        three[3] = 4;
        four[0] = 1;
        four[1] = 1;
        four[2] = 2;
        four[3] = 4;
        long mod = 1_000_000_007;
        // 计算过程中要对mod取余，否则会越界
        for (int i = 4; i < maxLen; i++) {
            three[i] = (three[i - 1] + three[i - 2] + three[i - 3]) % mod;
            four[i] = (four[i - 1] + four[i - 2] + four[i - 3] + four[i - 4]) % mod;
        }

        int i = 0;
        long ans = 1;
        while (i < len) {
            char cur = pressedKeys.charAt(i);
            int j = i + 1;
            while (j < len && pressedKeys.charAt(j) == cur) {
                j ++;
            }
            if (cur == '7' || cur == '9') {
                ans = (ans * four[j - i]) % mod;
            } else {
                ans = (ans * three[j - i]) % mod;
            }
            i = j;
        }
        return (int) ans;
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
