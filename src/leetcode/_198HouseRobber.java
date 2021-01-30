package leetcode;

/**
 * @author mizhu
 * @date 2021/1/29 23:04
 */
public class _198HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        int result = 0;
        for (int i=2;i<nums.length;i++) {
            result = Math.max(first + nums[i], second);
            first = second;
            second = result;
        }

        return result;
    }


}
