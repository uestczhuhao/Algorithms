package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _210CourseScheduleIi {
    public static void main(String[] args) {
        Solution t = new _210CourseScheduleIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
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
            int[] ans = new int[numCourses];
            int i = 0;
            while (!queue.isEmpty()) {
                int curCourse = queue.poll();
                for (int downstream : adj.get(curCourse)) {
                    if (--inDegree[downstream] == 0) {
                        queue.add(downstream);
                    }
                }
                ans[i++] = curCourse;
                numCourses--;
            }
            return numCourses == 0 ? ans : new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
