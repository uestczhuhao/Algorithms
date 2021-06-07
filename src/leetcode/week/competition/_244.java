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
        System.out.println(t.findRotation(mat, target));
//        int[] nums = {1, 1, 2, 2, 3};
//        System.out.println(t.reductionOperations(nums));
    }

    public int minFlips(String s) {
        int len = s.length();
        int ans = 0, i = 0;
        for (; i < len - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                break;
            }
        }

        if (i == len - 1) {
            return ans;
        }

        if (i > 0) {
            s = s.substring(i) + s.substring(0, i);
        }

//        for ()

        return 0;

    }

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
        if (ni < 0 || ni >= row
            || nj < 0 || nj >= col || visit[ni][nj]) {
            return false;
        }

        return true;
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
