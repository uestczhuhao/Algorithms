package leetcode;

/**
 * @author mizhu
 * @date 2021/1/29 11:01
 */
public class _152MaximumProductSubarray {
    /**
     * 解法：维护最大值 && 最小值
     * 1 若当前值为正（或0），则期望的是原先的最大值乘以当前值为最大
     * 2 若当前为负，则期望的是原先的最小值（负的越多，乘出来越大）为最大值
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int maxMulti = nums[0];
        int minMulti = nums[0];
        int resultMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current < 0) {
                int maxTmp = maxMulti;
                maxMulti = Math.max(minMulti * current, current);
                minMulti = Math.min(maxTmp * current, current);
            } else {
                maxMulti = Math.max(maxMulti * current, current);
                minMulti = Math.min(minMulti * current, current);
            }
            resultMax = Math.max(maxMulti, resultMax);
        }

        return resultMax;
    }

    public static void main(String[] args) {
        int[] arr = {-2,3,2,-4};
        _152MaximumProductSubarray t = new _152MaximumProductSubarray();
        System.out.println(t.maxProduct(arr));
    }
}
