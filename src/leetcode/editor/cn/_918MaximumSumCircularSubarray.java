package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _918MaximumSumCircularSubarray {
    public static void main(String[] args) {
        Solution t = new _918MaximumSumCircularSubarray().new Solution();
        int[] nums = {1, -2, 3, -2};
        System.out.println(t.maxSubarraySumCircular(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1. 如果最大子数组在nums中，则退化为求数组的最大子数组和
         * 2. 如果最大子数组在首尾两端，则取其差集部分，差集一定是最小子数组和
         * 简单证明下2：如果有子数组c，则c的和要么>=0，要么<0，前者可以加到最大子数组中去，使其更大；后者加到最小子数组中，使其更小
         */
        public int maxSubarraySumCircular1(int[] nums) {
            int total = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            int curMaxSum = 0, curMinSum = 0;
            for (int num : nums) {
                total += num;
                curMaxSum = curMaxSum <= 0 ? num : curMaxSum + num;
                max = Math.max(max, curMaxSum);
                curMinSum = curMinSum > 0 ? num : curMinSum + num;
                min = Math.min(min, curMinSum);
            }
            // 注意：当nums全为负数时，最大子数组和为最大的那个负数（所有数相加），此时total - min == 0，需要排除
            return max <= 0 ? max : Math.max(total - min, max);
        }

        /**
         * 把nums拼接一遍，问题转化为求2*nums中不超过n的长度的最大子数组问题
         */
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;
            Deque<int[]> deque = new LinkedList<>();
            int sum = nums[0], ans = nums[0];
            deque.offerLast(new int[]{0, nums[0]});
            for (int i = 1; i < 2 * n; i++) {
                // 到i时，所有超过长度的前缀和都删除
                while (!deque.isEmpty() && deque.peekFirst()[0] < i - n) {
                    deque.pollFirst();
                }
                sum += nums[i % n];
                ans = Math.max(ans, sum - deque.peekFirst()[1]);
                // 裁剪掉deque中的所有比前i项和大的前缀和，保证队列是单调的
                // 这个裁剪不能删，否则，当遍历到i时，i-n+1 ~ i的所有前缀和都在deque中留着，那么取最大时，需要全部遍历才能得到
                // 而全部遍历是不需要的，只需要每次插入当前之前，在deque中只保留比当前小的数（大的都poll了），就能保证deque中的单调的
                // 这个时候取最大值就简单了，只需要用sum - deque的队首元素即可
                while (!deque.isEmpty() && deque.peekLast()[1] >= sum) {
                    deque.pollLast();
                }
                deque.offerLast(new int[]{i, sum});
            }

            return ans;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
