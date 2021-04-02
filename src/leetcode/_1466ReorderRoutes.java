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
    public int minReorder1(int n, int[][] connections) {
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

    /**
     * 广度优先遍历，从城市0出发（src），找到其所有的邻接城市tgt，若当前是从src指向tgt，则答案加（因为这种情况要调转方向，目的城市是0）
     * 继续找邻接城市的邻接城市，直到所有的城市遍历完成
     */
    public int minReorder(int n, int[][] connections) {
        if (connections == null || n != connections.length + 1) {
            return -1;
        }

        // 存放与某个city相邻的所有节点，包括从city出发和到city的两种
        // 维护的是含有城市i的connection的下标
        Map<Integer, Set<Integer>> cityAdjMap = new HashMap<>();
        for (int i = 0; i < connections.length; i++) {
            int src = connections[i][0];
            int tgt = connections[i][1];
            Set<Integer> srcAdjCity = cityAdjMap.getOrDefault(src, new HashSet<>());
            srcAdjCity.add(i);
            cityAdjMap.put(src, srcAdjCity);

            Set<Integer> tgtAdjCity = cityAdjMap.getOrDefault(tgt, new HashSet<>());
            tgtAdjCity.add(i);
            cityAdjMap.put(tgt, tgtAdjCity);
        }

        Deque<Integer> queue = new LinkedList<>();
        // 遍历过的边，记录下来
        boolean[] visited = new boolean[n];
        queue.offer(0);
        int result = 0;

        while (!queue.isEmpty()) {
            int curCity = queue.poll();
            Set<Integer> set = cityAdjMap.get(curCity);
            // 遍历与curCity相邻的所有边（注意存放的是下标）
            for (int adj : set) {
                if (visited[adj]) {
                    continue;
                }
                visited[adj] = true;

                int src = connections[adj][0];
                int tgt = connections[adj][1];

                // 当前处理curCity，由于是从0往外扩展，且tgt还没遍历到
                // 因此如果curCity == src，代表这条边朝外，需要调转方向
                if (curCity == src) {
                    result++;
                }

                // 把邻接城市放入queue中
                // 同上原因，当curCity == src 时，需要遍历下一个邻接城市tgt，否则下一个城市为src
                queue.offer(curCity == src ? tgt : src);
            }


        }

        return result;
    }


    public static void main(String[] args) {
        _1466ReorderRoutes t = new _1466ReorderRoutes();
        int n = 5;
//        int[][] cons = {{0,1}, {1,3},{2,3},{4,0},{4,5}};
        int[][] cons = { {4,3},{2,3},{1,2},{1,0}};
//        int[][] cons = {{1, 0}, {2, 0}};
        System.out.println(t.minReorder(n, cons));
        System.out.println(t.minReorder1(n, cons));
    }
}
