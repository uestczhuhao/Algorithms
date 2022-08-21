package leetcode.week.competition;

import java.util.HashMap;
import java.util.PriorityQueue;

public class _306 {
    public static void main(String[] args) {
        _306 t = new _306();
        int[] edges = {2, 0, 0, 2};
//        System.out.println(t.edgeScore(edges));
        String par = "DDD";
//        String par = "IIIDIDDD";
        // "4321567"
        System.out.println(t.smallestNumber(par));
    }

    public String smallestNumber(String pattern) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= 9; i++) {
            queue.offer(i);
        }
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while (i < pattern.length()) {
            StringBuilder sb = new StringBuilder();
            for (; i < pattern.length(); i++) {
                if (pattern.charAt(i) != 'D') {
                    break;
                }
                sb.append(queue.poll());
            }
            if (sb.length() != 0) {
                sb.append(queue.poll());
                ans.append(sb.reverse());
            } else {
                ans.append(sb.reverse());
                ans.append(queue.poll());
            }
            i++;
        }

        if (ans.length() == pattern.length()) {
            ans.append(queue.poll());
        }

        return ans.toString();
    }

    public int edgeScore(int[] edges) {
        HashMap<Integer, Integer> nodeSum = new HashMap<>();
        int maxIndex = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < edges.length; i++) {
            int node = edges[i];
            nodeSum.put(node, nodeSum.getOrDefault(node, 0) + i);
            if (nodeSum.get(node) >= max) {
                maxIndex = nodeSum.get(node) == max ? Math.min(maxIndex, node) : node;
                max = nodeSum.get(node);
            }
        }
        return maxIndex;
    }

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length - 2;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = calculateMax(grid, i, j);
            }
        }
        return ans;
    }

    private int calculateMax(int[][] grid, int row, int col) {
        row += 1;
        col += 1;
        int max = grid[row][col];
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                max = Math.max(grid[i][j], max);
            }
        }
        return max;
    }
}
