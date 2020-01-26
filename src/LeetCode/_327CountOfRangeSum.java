package LeetCode;

/**
 * @author mizhu
 * @date 20-1-26 下午10:23
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class _327CountOfRangeSum {
    public static long[] temp;

    public static void main(String[] args) {
//        int[] nums = {-2, 5, -1};
//        int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE, -1, 0};
        int[] nums = {Integer.MIN_VALUE + 1, 0, Integer.MIN_VALUE + 1, Integer.MAX_VALUE};
        System.out.println(countRangeSum(nums, -1, 0));
    }

    /**
     * 解法：
     * 1. 先计算前i项和数组sums，注意sums是long类型，以免越界（有大数的测试用例）
     * 2. [i,j] 中的元素值在目标中（low <= k <= upper, i <= k <=），当且仅当
     *
     * @param lower 下限值
     * @param upper 上限值
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        temp = new long[nums.length];
        // 求出数组的前i项和，对和数组进行归并排序
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return mergeSortWithCount(sums, 0, nums.length - 1, lower, upper);
    }

    public static int mergeSortWithCount(long[] sums, int start, int end, int lower, int upper) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return sums[start] >= lower && sums[start] <= upper ? 1 : 0;
        }
        int mid = start + (end - start) / 2;

        int leftCount = mergeSortWithCount(sums, start, mid, lower, upper);
        int rightCount = mergeSortWithCount(sums, mid + 1, end, lower, upper);

        return leftCount + rightCount + doMergeWithCount(sums, start, mid, end, lower, upper);
    }

    public static int doMergeWithCount(long[] sums, int start, int mid, int end, int lower, int upper) {
        System.arraycopy(sums, start, temp, start, end - start + 1);

        int i = start, j = mid + 1;
        int k = start;

        int left = start;
        int low = mid + 1, up = mid + 1;
        int count = 0;

        while (left <= mid) {

            while (low <= end && temp[low] - temp[left] < lower) {
                low++;
            }
            while (up <= end && temp[up] - temp[left] <= upper) {
                up++;
            }
//            System.out.println("low = " + low + " up = " + up);
            count += up - low;
            left++;
        }

        while (i <= mid && j <= end) {

            if (temp[i] <= temp[j]) {
                sums[k++] = temp[i++];
            } else {
                sums[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            sums[k++] = temp[i++];
        }

        while (j <= end) {
            sums[k++] = temp[j++];
        }

        return count;
    }
}
