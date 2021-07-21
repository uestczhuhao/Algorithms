package leetcode.week.competition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author mizhu
 * @date 2021/7/21 20:22
 */
public class _250 {
    public static void main(String[] args) {
        _250 t = new _250();
        String text = "leet code", brokenLetters = "e";
//        System.out.println(t.canBeTypedWords(text, brokenLetters));
        int[] rungs = {1, 3, 5, 10};
        int dist = 2;
//        System.out.println(t.addRungs(rungs, dist));
        int[][] points = {{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
//        int[][] points = {{1, 5}, {2, 3}, {4, 2}};
        System.out.println(t.maxPoints(points));
    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/solution/dp-you-hua-ji-qiao-chai-xiang-qian-hou-z-5vvc/
     */
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[][] ans = new long[m][n];
        for (int j = 0; j < n; j++) {
            ans[0][j] = points[0][j];
        }

        for (int i = 1; i < m; i++) {
            long tmpMax = Long.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                // 当k <= j时，points[i][j]−j+max(ans[i−1][k]+k)
                // 因为当k<=j 时，从前往后遍历j，如当j=2时，tmpMax取的是0，1，2三个数的最大值
                tmpMax = Math.max(tmpMax, ans[i - 1][j] + j);
                ans[i][j] = points[i][j] - j + tmpMax;
            }

            // 当k > j时，points[i][j]+j+max(ans[i−1][k]−k)
            tmpMax = Long.MIN_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                tmpMax = Math.max(tmpMax, ans[i - 1][j] - j);
                ans[i][j] = Math.max(ans[i][j], points[i][j] + j + tmpMax);
            }

        }

        long answer = Long.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            answer = Math.max(answer, ans[m - 1][j]);
        }

        return answer;
    }

    public int addRungs(int[] rungs, int dist) {
        int ans = 0;
        if (rungs[0] > dist) {
            ans += (rungs[0] - 1) / dist;
        }
        for (int i = 1; i < rungs.length; i++) {
            ans += (rungs[i] - rungs[i - 1] - 1) / dist;
        }

        return ans;
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> brokenSet = new HashSet<>();
        for (int i = 0; i < brokenLetters.length(); i++) {
            brokenSet.add(brokenLetters.charAt(i));
        }

        int index = 0;
        int ans = 0;
        while (index < text.length()) {
            boolean hasBroken = false;
            while (index < text.length() && text.charAt(index) != ' ') {
                if (brokenSet.contains(text.charAt(index))) {
                    hasBroken = true;
                }
                index++;
            }
            ans += hasBroken ? 0 : 1;
            index++;
        }

        return ans;
    }
}
