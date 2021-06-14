package leetcode.week.competition;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author mizhu
 * @date 2021/6/6 21:41
 */
public class _244 {
    public static void main(String[] args) {
        int[][] mat = {{0, 1}, {1, 1}};
//        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
//        int[][] target = {{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
        int[][] target = {{1, 0}, {0, 1}};
        _244 t = new _244();
//        System.out.println(t.findRotation(mat, target));
//        int[] nums = {1, 1, 2, 2, 3};
//        System.out.println(t.reductionOperations(nums));
        System.out.println(t.minFlips("1110"));
    }

    /**
     * 思路：把s往后加一遍，模拟操作1，再用长度为len的滑动窗口解决
     * 注意：可能是0101...类型的结果最小，也可能是1010...类型的结果最小，但结果最大为len
     */
    public int minFlips(String s) {
        int len = s.length();
        String zeroOne = "01";
        String oneZero = "10";
        int ans = len;
        int zDiff = 0, oDiff = 0;
        s += s;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != zeroOne.charAt(i % 2)) {
                zDiff++;
            }
            if (s.charAt(i) != oneZero.charAt(i % 2)) {
                oDiff++;
            }
        }

        for (int i = len; i < 2 * len; i++) {
            if (s.charAt(i) != zeroOne.charAt(i % 2)) {
                zDiff++;
            }
            if (s.charAt(i) != oneZero.charAt(i % 2)) {
                oDiff++;
            }
            if (s.charAt(i - len) != zeroOne.charAt((i - len) % 2)) {
                zDiff--;
            }
            if (s.charAt(i - len) != oneZero.charAt((i - len) % 2)) {
                oDiff--;
            }
            ans = Math.min(Math.min(ans, zDiff), oDiff);
        }
        return ans;
    }

    /**
     * 旋转90度之后：第i行变成第col-1-i列（如第一行变成最后一列，第二行变成倒数第二列）
     * 第j列变成第j行，如第一列变成第一行
     * 即 rotate[j][n - 1 -i] = mat[i][j]
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int row = mat.length;
        int col = mat[0].length;
        return equal(mat, target, 0, 0, 0)
            || equal(mat, target, 0, col - 1, 1)
            || equal(mat, target, row - 1, col - 1, 2)
            || equal(mat, target, row - 1, 0, 3);
    }

    private boolean equal(int[][] src, int[][] tgt, int si, int sj, int sDirIndex) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = src.length;
        int col = src[0].length;
        boolean[][] sVisit = new boolean[row][col];
        boolean[][] tVisit = new boolean[row][col];
        int count = 0, ti = 0, tj = 0, tDirIndex = 0;
        while (count < row * col) {
            count++;
            if (src[si][sj] != tgt[ti][tj]) {
                return false;
            }
            sVisit[si][sj] = true;
            si += dir[sDirIndex][0];
            sj += dir[sDirIndex][1];
            tVisit[ti][tj] = true;
            ti += dir[tDirIndex][0];
            tj += dir[tDirIndex][1];

            if (!nextValid(si, sj, sDirIndex, dir, row, col, sVisit)) {
                sDirIndex = (sDirIndex + 1) % dir.length;
            }

            if (!nextValid(ti, tj, tDirIndex, dir, row, col, tVisit)) {
                tDirIndex = (tDirIndex + 1) % dir.length;
            }
        }

        return true;
    }

    private boolean nextValid(int i, int j, int dirIndex, int[][] dir, int row, int col, boolean[][] visit) {
        int ni = i + dir[dirIndex][0];
        int nj = j + dir[dirIndex][1];
        return ni >= 0 && ni < row
            && nj >= 0 && nj < col && !visit[ni][nj];
    }

    public int reductionOperations(int[] nums) {
        TreeMap<Integer, Integer> numFre = new TreeMap<>((a, b) -> b - a);
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            numFre.put(num, numFre.getOrDefault(num, 0) + 1);
        }
        int ans = 0, step = numFre.size();
        for (Map.Entry<Integer, Integer> entry : numFre.entrySet()) {
            int value = entry.getValue();
            ans += (--step) * value;
        }

        return ans;
    }
}
