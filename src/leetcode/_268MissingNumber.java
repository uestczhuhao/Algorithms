package leetcode;

/**
 * @author mizhu
 * @date 2020/11/14 20:22
 */
public class _268MissingNumber {
    /**
     * 从1加到n，再依次减去nums中的值
     * 最后剩余的即为丢失的数字
     */
    public int missingNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }

        int sum = nums.length;
        for (int i=0;i<nums.length;i++){
            sum+=i;
            sum -= nums[i];
        }

        return sum;
    }
}
