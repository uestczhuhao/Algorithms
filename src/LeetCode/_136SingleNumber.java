package LeetCode;

/**
 * @author mizhu
 * @date 2021/1/28 11:37
 */
public class _136SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int res = nums[0];
        for (int i=1;i<nums.length;i++) {
            res ^= nums[i];
        }

        return res;
    }
}
