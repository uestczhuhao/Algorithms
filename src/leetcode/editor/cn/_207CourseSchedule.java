package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _207CourseSchedule {
    public static void main(String[] args) {
        Solution t = new _207CourseSchedule().new Solution();
//        int[][] ps = {{0, 1}, {1, 2}, {2, 3}, {0, 3}};
        int[][] ps = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        System.out.println(t.canFinish(4, ps));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 拓补排序，构建dag图，然后从入度为0的点开始遍历，遍历到最后时，查看是否经历了n个点
         * 若是，表示dag无环，题目要求可以满足；否则不能满足
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 存放每个课程的入度，默认为0
            int[] inDegree = new int[numCourses];
            // 邻接矩阵，存放每个课程的邻居list
            List<Set<Integer>> adj = new ArrayList<>(numCourses);
            for (int i = 0; i < numCourses; i++) {
                adj.add(new HashSet<>());
            }
            for (int[] pre : prerequisites) {
                int cur = pre[0];
                int dep = pre[1];
                inDegree[cur]++;
                adj.get(dep).add(cur);
            }
            // 用queue存放课程，从入度为0的节点依次选课
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                int curCourse = queue.poll();
                for (int downstream : adj.get(curCourse)) {
                    if (--inDegree[downstream] == 0) {
                        queue.add(downstream);
                    }
                }
                numCourses--;
            }
            return numCourses == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
