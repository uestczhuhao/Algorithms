package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author mizhu
 * @date 2020/4/12 17:13
 * 有 nums 个网络节点，标记为 1 到 nums。
 * <p>
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], nums = 4, K = 2
 * 输出：2
 *  
 * <p>
 * 注意:
 * <p>
 * nums 的范围在 [1, 100] 之间。
 * K 的范围在 [1, nums] 之间。
 * times 的长度在 [1, 6000] 之间。
 * 所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= nums 且 0 <= w <= 100。
 */
public class _743NetworkDelayTime {
    public static void main(String[] args) {
        _743NetworkDelayTime t = new _743NetworkDelayTime();
        int[][] times = new int[3][3];
        int N = 4;
        int K = 2;
        times[0] = new int[]{2, 1, 1};
        times[1] = new int[]{2, 3, 1};
        times[2] = new int[]{3, 4, 1};
        System.out.println(t.networkDelayTime(times, N, K));
        System.out.println(t.networkDelayTime1(times, N, K));
    }

    /**
     * Dijkstra算法，思路详解：https://www.cnblogs.com/skywang12345/p/3711516.html
     * 注：严格按照思路的解法，需要几倍的代码量，且时间/空间复杂度更差
     *
     * 邻接矩阵实现，3ms
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times.length == 0 || times[0].length <= 0) {
            return -1;
        }

        // 邻接矩阵，graph[i][j] 代表i 到 j 的传递时间，初始化为最大值
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        // 根据times填充邻接表
        for (int[] time : times) {
            graph[time[0] - 1][time[1] - 1] = time[2];
        }


        // seen数组，代表从i到K是否联通
        // 也可以用作判定某节点是否已经访问过
        boolean[] seen = new boolean[N];

        // 从K出发，因此初始化为true
        seen[K - 1] = true;

        // 距离K的距离表，默认为最大值
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 按照题意初始化K到各个节点的传递时间
        System.arraycopy(graph[K - 1], 0, distance, 0, N);
        // 节点自身的传递时间为0
        distance[K - 1] = 0;

        // 外层循环需要N-1次，代表N个节点（除K外）都处理了一遍
        for (int i = 0; i < N - 1; i++) {
            int minIndex = 0;
            int minDistance = Integer.MAX_VALUE;

            // 找到本轮的最小距离
            for (int j = 0; j < N; j++) {
                // 从unVisit中寻找最小值
                if (!seen[j] && distance[j] < minDistance) {
                    minIndex = j;
                    minDistance = distance[j];
                }
            }
            // 标记minIndex为找到
            // 等效为每次从unVisit中取最小值，放入visit
            seen[minIndex] = true;

            // 根据本轮的minIndex，更新distance列表
            for (int j = 0; j < N; j++) {
                // 更新从本轮minIndex可达的节点，用于下一轮寻找最小值，即更新邻接矩阵
                if (graph[minIndex][j] != Integer.MAX_VALUE) {
                    distance[j] = Math.min(distance[j], distance[minIndex] + graph[minIndex][j]);
                }
            }
        }

        int maxDistance = 0;
        for (int i = 0; i < N; i++) {
            // 若有节点不可达，直接返回-1
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }

            maxDistance = Math.max(maxDistance, distance[i]);
        }


        return maxDistance;
    }

    /**
     * 用小顶堆（距离小的优先）优化Dijkstra算法
     * 优化空间，时间消耗更大，24ms
     */
    public int networkDelayTime1(int[][] times, int N, int K) {
        if (times.length == 0 || times[0].length <= 0) {
            return -1;
        }

        // 邻接表map，稀疏时更优
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表
        for (int[] t : times) {
            map.computeIfAbsent(t[0] - 1, k -> new ArrayList<>()).add(new int[]{t[1] - 1, t[2]});
        }

        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K - 1] = 0;
        // seen数组，代表从i到K是否联通
        // 也可以用作判定某节点是否已经访问过
        boolean[] seen = new boolean[N];

        PriorityQueue<Integer> indexQueue = new PriorityQueue<>(Comparator.comparingInt(index -> distance[index]));
        indexQueue.add(K - 1);
        while (!indexQueue.isEmpty()) {
            int curIndex = indexQueue.poll();
            if (seen[curIndex]) {
                continue;
            }

            // 标记当前节点为已访问
            seen[curIndex] = true;
            List<int[]> timeList = map.getOrDefault(curIndex, new ArrayList<>());
            for (int[] time : timeList) {
                int next = time[0];
                // 如果这个next已经访问过，代表已经取过最小路径，直接跳过
                if (seen[next]) {
                    continue;
                }
                // 更新next的到达时间
                // next的时间，取从curIndex出发的值（distance[curIndex] + time[1]）和其自身值中的较小值
                if (distance[curIndex] != Integer.MAX_VALUE) {
                    distance[next] = Math.min(distance[next], distance[curIndex] + time[1]);
                }
                // curIndex的所有邻居入队列，按距离从小到大访问
                indexQueue.add(next);
            }

        }

        int res = Integer.MIN_VALUE;
        for (int dis : distance) {
            if (dis == Integer.MAX_VALUE) {
                return -1;
            }

            res = Math.max(res, dis);
        }
        return res;
    }
}
