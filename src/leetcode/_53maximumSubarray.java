package leetcode;

/**
 * @author mizhu
 * @date 20-5-31 下午4:09
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class _53maximumSubarray {
    public static void main(String[] args) {
        _53maximumSubarray t = new _53maximumSubarray();
//        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] a = {0};
        System.out.println(t.maxSubArray(a));
        System.out.println(t.maxSubArray1(a));
    }

    /**
     * 动态规划实现，f(i)代表
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int maxSum = nums[0];
        int preSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果之前的和小于0，则抛弃，从第i个位置重新开始求和
            preSum = Math.max(nums[i], preSum + nums[i]);
            maxSum = Math.max(maxSum, preSum);
        }

        return maxSum;
    }

    /**
     * 贪心算法，累加到当前的和sum < 0时，将sum置为0，同时记录最大的sum即可
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            // 等同于Math.max(num,sum + num)，但sum初始值要是int最小
            sum = sum <= 0 ? num : sum + num;

            max = Math.max(sum, max);
        }

        return max;
    }
}
