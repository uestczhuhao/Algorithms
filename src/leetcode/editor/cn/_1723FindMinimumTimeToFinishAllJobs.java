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

public class _1723FindMinimumTimeToFinishAllJobs {
    public static void main(String[] args) {
        Solution t = new _1723FindMinimumTimeToFinishAllJobs().new Solution();
        int[] jobs = {5, 5, 4, 4, 4};
        int k = 2;
        System.out.println(t.minimumTimeRequired(jobs, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 二分法，最小值为jobs的最大工作量，最大值为jobs的所有工作量（等效为所有工作一个人做）
         * 在最小值和最大值中寻找最小的满足条件的工作时间
         * 参考：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/wan-cheng-suo-you-gong-zuo-de-zui-duan-s-hrhu/
         */
        public int minimumTimeRequired(int[] jobs, int k) {
            Arrays.sort(jobs);

            int len = jobs.length;
            //
            int min = jobs[len - 1];
            int max = 0;
            for (int j : jobs) {
                max += j;
            }

            while (min < max) {
                int mid = (max - min) / 2 + min;
                if (check(k, jobs, mid)) {
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }

            return min;

        }

        private boolean check(int k, int[] jobs, int limit) {
            int[] workloads = new int[k];
            return disJob(jobs, limit, jobs.length - 1, workloads);
        }

        /**
         * @param jobs
         * @param limit 本次最大工作时间
         * @param end 从大到小分配，传入end
         * @param workloads 工作数组，规模为工人的个数
         * @return
         */
        private boolean disJob(int[] jobs, int limit, int end, int[] workloads) {
            if (end < 0) {
                return true;
            }

            // 本轮分配的工作量
            int cur = jobs[end];
            // 尝试把cur分配给k个工人
            // 第j个工人可以重复分配工作，所以每一层都从0开始
            for (int j = 0; j < workloads.length; j++) {
                if (workloads[j] + cur <= limit) {
                    workloads[j] += cur;
                    if (disJob(jobs, limit, end - 1, workloads)) {
                        return true;
                    }
                    workloads[j] -= cur;
                }

                // 如果当前工人未被分配工作，且cur分配给j时，所有的尝试都是失败的
                // 那么把这个工作分配给后面的任何一个工人都会出现这种失败
                if (workloads[j] == 0) {
                    break;
                }
            }

            // 分配失败，返回false
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}