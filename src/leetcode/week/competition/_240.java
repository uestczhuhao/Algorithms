package leetcode.week.competition;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2021/5/9 21:17
 */
public class _240 {
    public static void main(String[] args) {
        _240 t = new _240();
        int[][] logs = {{1993, 1999}, {2000, 2010}};
//        System.out.println(t.maximumPopulation(logs));
        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {1, 1};
//        System.out.println(t.maxDistance(nums1, nums2));
        int[] nums = {3, 1, 5, 6, 4, 2};
        System.out.println(t.maxSumMinProduct(nums));
        String colors = "abaca";
        int[][] e = {{0,1},{0,2},{2,3},{3,4}};
        System.out.println(t.largestPathValue(colors, e));
    }

    public int maximumPopulation(int[][] logs) {
        int[] yearPop = new int[100];
        int birth, death;
        for (int[] log : logs) {
            birth = log[0];
            death = log[1];
            for (int i = birth; i < death; i++) {
                yearPop[i - 1950]++;
            }
        }

        int maxPop = 0, year = 0;
        for (int i = 0; i < yearPop.length; i++) {
            if (yearPop[i] > maxPop) {
                maxPop = yearPop[i];
                year = i;
            }
        }

        return year + 1950;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int distance = 0;
        while (i < nums1.length && j < nums2.length) {
            if (i > j) {
                j = i;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                i++;
            } else {
                distance = Math.max(distance, j - i);
                j++;
            }
        }

        return distance;
    }

    public int maxSumMinProduct(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        long max = 0;
        int ans = 0;
        int len = nums.length;
        long[] sum = new long[len + 1];
        sum[0] = 0;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                int candidate = stack.pop();
                int leftNe = stack.isEmpty() ? -1 : stack.peek();
                long cur = (sum[i] - sum[leftNe + 1]) * nums[candidate];
                if (cur > max) {
                    max = cur;
                    ans = (int) (cur % 1000_000_007);
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int candidate = stack.pop();
            int leftNe = stack.isEmpty() ? -1 : stack.peek();
            long cur = (sum[len] - sum[leftNe + 1]) * nums[candidate];
            if (cur > max) {
                max = cur;
                ans = (int) (cur % 1000_000_007);
            }
        }


        return ans;
    }

    public int largestPathValue(String colors, int[][] edges) {
        int len = colors.length();
        // 邻接表，每个位置i放置与i直接相连的下游节点
        Node[] adjList = new Node[len];
        // 节点入度数组
        int[] inDegree = new int[len];
        for (int[] eg : edges) {
            int src = eg[0];
            int tgt = eg[1];
            /*  「头插法」等价于下面几行操作,
             *    NewNode node = new NewNode(to);
             *    node.next = adjList[from];
             *    adjList[from] = node;
             */
            adjList[src] = new Node(tgt, adjList[src]);
            inDegree[tgt]++;
        }

        // 拓扑排序，记录遍历节点个数
        int found = 0;
        // 存放入度为0的节点
        Deque<Integer> deque = new LinkedList<>();
        // 截止到第 i 个节点，第 j 种颜色的数量
        int[][] f = new int[len][26];
        for (int i = 0; i < len; i++) {
            if (inDegree[i] == 0) {
                deque.offer(i);
            }
        }

        // 拓扑排序
        while (!deque.isEmpty()) {
            int cur = deque.poll();
            found++;
            // 当前位置的颜色+1
            f[cur][colors.charAt(cur) - 'a']++;

            // 遍历当前节点的所有下一个节点
            for (Node next = adjList[cur]; next != null; next = next.next) {
                inDegree[next.val]--;

                // next节点入度为0时，加入队列
                if (inDegree[next.val] == 0) {
                    deque.offer(next.val);
                }

                // 更新 next 所有颜色数量，即f[next][c] = Math.max(f[cur][c], f[next][c])
                for (int c = 0; c < 26; c++) {
                    f[next.val][c] = Math.max(f[next.val][c], f[cur][c]);
                }
            }
        }

        if (found != len) {
            return -1;
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, Arrays.stream(f[i]).max().getAsInt());
        }
        return ans;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

}
