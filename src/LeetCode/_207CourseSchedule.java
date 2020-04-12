package LeetCode;

import com.sun.javafx.geom.Edge;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/4/12 15:12
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */
public class _207CourseSchedule {
    public static void main(String[] args) {
        int num = 2;
        int[][] pre = new int[2][2];
        int[] courses = {1, 0};
        int[] courses1 = {0, 1};
        pre[0] = courses;
        pre[1] = courses1;
        _207CourseSchedule t = new _207CourseSchedule();
//        System.out.println(t.canFinish(num, pre));
        System.out.println(t.canFinish(num, pre));
    }

    /**
     * 课程直接的依赖可以组织成一个dag图
     * 问题转化为dag图判环问题
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites.length <= 0) {
            return true;
        }

        // 采用入度表和邻接表的方式表达dag
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        // 初始化每门课都有一个空邻接表，即没有任何先修依赖
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            inDegree[edge[0]]++;
            adjacency.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> courses = new LinkedList<>();
        // 找到入度为0的节点，作为拓补排序的起点
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                courses.add(i);
            }
        }

        int courseRemainNum = numCourses;
        // 拓补排序
        while (!courses.isEmpty()) {
            int course = courses.poll();

            for (int downStream : adjacency.get(course)) {
                if (--inDegree[downStream] == 0) {
                    courses.add(downStream);
                }
            }
            courseRemainNum--;
        }

        return courseRemainNum == 0;
    }


}
