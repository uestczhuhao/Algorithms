package leetcode.editor.cn;

//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个
//单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。 
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的 最短时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 示例 2： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
// 
//
// 示例 3： 
//
// 
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待
//命) -> (待命) -> A
// 
//
// 
//
// 提示： 
//
// 
// 1 <= task.length <= 104 
// tasks[i] 是大写英文字母 
// n 的取值范围为 [0, 100] 
// 
// Related Topics 贪心算法 队列 数组 
// 👍 572 👎 0


import java.util.HashMap;
import java.util.Map;

public class _621TaskScheduler {
    public static void main(String[] args) {
        Solution t = new _621TaskScheduler().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode-solution-ur9w/
         * 思路：找到tasks中的最大执行时间任务，记为A
         * 以A为标的，构造桶，其余的排列在后
         */
        public int leastInterval(char[] tasks, int n) {
            if (tasks == null || tasks.length == 0) {
                return 0;
            }

            int maxExecTime = 0;
            // 最后一列的个数
            int countMaxExec = 0;
            Map<Character, Integer> taskExecTimeMap = new HashMap<>();
            for (char task : tasks) {
                int execTime = taskExecTimeMap.getOrDefault(task, 0);
                taskExecTimeMap.put(task, execTime + 1);
                if (execTime > maxExecTime) {
                    countMaxExec = 1;
                    maxExecTime = execTime;
                } else if (execTime == maxExecTime) {
                    countMaxExec ++;
                }
            }

            return Math.max(tasks.length, countMaxExec + (maxExecTime) * (n + 1));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}