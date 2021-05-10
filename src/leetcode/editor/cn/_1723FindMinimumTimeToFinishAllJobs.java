package leetcode.editor.cn;

//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。 
//
// 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
//设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。 
//
// 返回分配方案中尽可能 最小 的 最大工作时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：jobs = [3,2,3], k = 3
//输出：3
//解释：给每位工人分配一项工作，最大工作时间是 3 。
// 
//
// 示例 2： 
//
// 
//输入：jobs = [1,2,4,7,8], k = 2
//输出：11
//解释：按下述方式分配工作：
//1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
//2 号工人：4、7（工作时间 = 4 + 7 = 11）
//最大工作时间是 11 。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= jobs.length <= 12 
// 1 <= jobs[i] <= 107 
// 
// Related Topics 递归 回溯算法 
// 👍 158 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

public class _1723FindMinimumTimeToFinishAllJobs {
    public static void main(String[] args) {
        Solution t = new _1723FindMinimumTimeToFinishAllJobs().new Solution();
        int[] jobs = {5, 5, 4, 4, 4};
        int k = 2;
        System.out.println(t.minimumTimeRequired(jobs, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // TODO:待完成
        public int minimumTimeRequired(int[] jobs, int k) {
            Arrays.sort(jobs);

            int len = jobs.length;
            if (len <= k) {
                return jobs[len - 1];
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(k);
            for (int i = len - 1; i >= len - k; i--) {
                queue.add(jobs[i]);
            }

            for (int i = len - k - 1; i >= 0; i--) {
                queue.add(queue.poll() + jobs[i]);
            }

            for (int i = 0; i < k - 1; i++) {
                queue.poll();
            }

            return queue.poll();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}