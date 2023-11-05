package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _370 {
    public static void main(String[] args) {
        _370 t = new _370();
//        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
//        int[] values = {20, 10, 9, 7, 4, 3, 5};
        int[][] edges = {{0, 1}, {1, 2}};
//        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {2, 4}, {4, 5}};
//        int[] values = {5, 2, 5, 2, 1, 1};
        int[] values = {100, 1, 2};
        System.out.println(t.maximumScoreAfterOperations(edges, values));

    }

    /**
     * 思路：从反面思考这个问题，假设所有的分数为sum，则题目转化为：保证树为健康的基础上，失去的最小分数
     * 接下来就是讨论在保证树健康的情况下，values的累加值最小即可
     * 在节点p时，有2种方式
     * 1.保留p，那么从以p为根节点的所有子树走都是健康的。(value[p])
     * 2.不保留p，那么dfs返回的值应该是p的所有子树dfs的值之和。(sum)
     * 以p为根节点的树向上规约的值应该是两种情况的最小值
     * <p>
     * 当p为叶子节点时，考虑只有1个节点的树，要使该树健康，则当前的叶子节点一定要选
     */
    int[] vs;
    List<Integer>[] adj;

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        vs = values;
        adj = new ArrayList[n];
        Arrays.setAll(adj, a -> new ArrayList<>());
        // 链接一条从0到-1的虚拟边，防止只有2个点，一条边时，直接不往下走
        adj[0].add(-1);
        for (int[] ed : edges) {
            adj[ed[0]].add(ed[1]);
            adj[ed[1]].add(ed[0]);
        }
        long minScore = dfs(-1, 0);
        return Arrays.stream(values).mapToLong(a -> (long) a).sum() - minScore;
    }

    private long dfs(int parent, int cur) {
        // 递归基
        if (adj[cur].size() == 1) {
            return vs[cur];
        }
        long sum = 0;
        for (int adjIndex : adj[cur]) {
            if (adjIndex == parent) {
                continue;
            }
            sum += dfs(cur, adjIndex);
        }
        return Math.min(vs[cur], sum);
    }

    public int findChampion(int n, int[][] edges) {
        int[] inDegree = new int[n];
        for (int[] eg : edges) {
            inDegree[eg[1]]++;
        }
        int cnt = 0, ans = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                cnt++;
                ans = i;
            }
        }
        return cnt == 1 ? ans : -1;
    }

    public int findChampion(int[][] grid) {
        int n = grid.length;
        boolean[] defeated = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    defeated[j] = true;
                } else {
                    defeated[i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!defeated[i]) {
                return i;
            }
        }
        return -1;
    }
}
