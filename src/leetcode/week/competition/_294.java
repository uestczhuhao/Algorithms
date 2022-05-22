package leetcode.week.competition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _294 {
    public static void main(String[] args) {
        _294 t = new _294();
//        System.out.println(t.percentageLetter("foobar", 'p'));
        int[] ca = {10, 2, 2};
        int[] ra = {2, 2, 0};
//        System.out.println(t.maximumBags(ca, ra, 10));
        int[][] prs = {{1, 7}, {2, 6}, {3, 5}, {4, 4}, {5, 4}, {6, 3}, {7, 2}, {8, 1}};
//        System.out.println(t.minimumLines(prs));
        int[] strength = {558, 1567, 644, 40, 411, 1039, 37, 802, 461, 1001, 1814, 1195, 746, 790, 374, 1215, 61, 1348, 14, 1501, 885, 885, 240, 1655, 1916, 763, 213, 1453, 281, 528, 1659, 938, 1266, 1109, 558, 808, 1086, 341, 920, 746};
        System.out.println(t.totalStrength(strength));
    }

    public int totalStrength(int[] strength) {
        int n = strength.length;
        long[] preSum = new long[n];
        preSum[0] = strength[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + strength[i];
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int min = strength[i];
            for (int j = i; j < n; j++) {
                min = Math.min(strength[j], min);
                long curStrength = (preSum[j] - (i == 0 ? 0 : preSum[i - 1])) * min;
                ans = (ans + curStrength) % 1_000_000_007;
            }
        }
        return (int) ans;
    }

    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length < 2) {
            return 0;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] dayPrice : stockPrices) {
            queue.offer(dayPrice);
        }
        int ans = 1;
        if (queue.size() <= 2) {
            return ans;
        }
        int[] pre = queue.poll();
        int[] cur = queue.poll();
        int[] next;
        while (!queue.isEmpty()) {
            next = queue.poll();
            long tmp1 = (long) (cur[1] - pre[1]) * (next[0] - cur[0]);
            long tmp2 = (long) (next[1] - cur[1]) * (cur[0] - pre[0]);
            boolean equal = tmp1 == tmp2;
            if (!equal) {
                ans++;
            }
            pre = cur;
            cur = next;
        }
        return ans;
    }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] remain = new int[n];
        for (int i = 0; i < n; i++) {
            remain[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(remain);
        int ans = 0, i = 0;
        while (i < n && remain[i] == 0) {
            ans++;
            i++;
        }
        for (; i < n; i++) {
            additionalRocks -= remain[i];
            if (additionalRocks < 0) {
                break;
            }
            ans++;
        }
        return ans;
    }

    public int percentageLetter(String s, char letter) {
        int count = 0, len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return count * 100 / len;
    }
}
