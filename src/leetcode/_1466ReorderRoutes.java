package leetcode;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/7/12 15:54
 * <p>
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * <p>
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * <p>
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * <p>
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * <p>
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 3：
 * <p>
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class _1466ReorderRoutes {
    public int minReorder(int n, int[][] connections) {
        if (connections == null || n != connections.length + 1) {
            return -1;
        }

        Map<Integer, List<Edge>> adjacencyMap = new HashMap<>();
        for (int[] edge : connections) {
            int src = edge[0];
            int tgt = edge[1];
            // 无向边，正向为true，反向为false
            List<Edge> adjEdgeList = adjacencyMap.getOrDefault(src, new LinkedList<>());
            adjEdgeList.add(new Edge(src, tgt, true));
            adjacencyMap.put(src, adjEdgeList);

            adjEdgeList = adjacencyMap.getOrDefault(tgt, new LinkedList<>());
            adjEdgeList.add(new Edge(tgt, src, false));
            adjacencyMap.put(tgt, adjEdgeList);
        }

        int reorderCount = 0;
        Queue<Edge> cityQueue = new LinkedList<>(adjacencyMap.get(0));
        boolean[] visit = new boolean[adjacencyMap.size()];
        visit[0] = true;
        while (!cityQueue.isEmpty()) {
            Edge curCity = cityQueue.poll();
//            if (visit[curCity.target]) {
//                continue;
//            }
            if (curCity.forward) {
                reorderCount++;
            }
            visit[curCity.target] = true;
            List<Edge> neighbors = adjacencyMap.get(curCity.target);
            for (Edge neighbor : neighbors) {
                if (!visit[neighbor.target]) {
                    cityQueue.add(neighbor);
                }
            }
        }

        return reorderCount;
    }

    private static class Edge {
        private int source;
        private int target;
        // 记录原边是否正向
        // 由于记录的是无向边，一个边记录两次，一次正向，一次反向
        private boolean forward;

        Edge(int source, int target, boolean forward) {
            this.source = source;
            this.target = target;
            this.forward = forward;
        }
    }

    public static void main(String[] args) {
        _1466ReorderRoutes t = new _1466ReorderRoutes();
        int n = 6;
//        int[][] cons = {{0,1}, {1,3},{2,3},{4,0},{4,5}};
        int[][] cons = {{1, 0}, {2, 0}};
        System.out.println(t.minReorder(3, cons));
    }
}
