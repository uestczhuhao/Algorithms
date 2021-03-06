package leetcode;

/**
 * @author mizhu
 * @date 20-2-5 下午9:01
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class _4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        _4MedianOfTwoSortedArrays t = new _4MedianOfTwoSortedArrays();
        int[] nums1 = {3};
//        int[] nums2 = {4, 5, 6, 7};
        int[] nums2 = {-1, -2};
        System.out.println(t.findMedianSortedArrays(nums1, nums2));
        System.out.println(t.findMedianSortedArrays1(nums1, nums2));
    }

    /**
     * 解法：二分+剪枝，每次寻找范围缩小一半
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-shu-b/
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }

        int shortLen, longLen;
        int[] shortNums, longNums;
        if (nums1.length < nums2.length) {
            shortLen = nums1.length;
            shortNums = nums1;
            longLen = nums2.length;
            longNums = nums2;
        } else {
            shortLen = nums2.length;
            shortNums = nums2;
            longLen = nums1.length;
            longNums = nums1;
        }

        int start = 0, end = shortLen;
        int halfLen = (shortLen + longLen + 1) / 2;
        // 注意是小于等于，而不是小于，原因是在边界情况发生时，最后的终止条件（其中一种）为start=end=shortLen
        // 例： [1,2,3] 和 [4,5,6,7]找中位数
        // 如果是小于，则上诉例子没有机会进入else分之，也就无法输出正确结果
        while (start <= end) {
            int shortMid = (start + end) / 2;
            // 保证较短的数组的中位数和较大数组的中位数之和是数组长度之和的一半
            int longMid = halfLen - shortMid;
            /*
                共有4种情况（A代表较短数组，B代表较长数组）：
                1. A[shortMid - 1] < B[longMid]
                2. A[shortMid - 1] > B[longMid]
                3. A[shortMid] < B[longMid - 1]
                4. A[shortMid] > B[longMid - 1]
                其中，2可以覆盖4，3可以覆盖1，以2为例：A[shortMid - 1] > B[longMid] 如果成立，
                A[shortMid] 比左边更大，B[longMid - 1]比右边更小，4显然成立。
                因此取2和3作为判断条件，注意指针越界
             */
            if (shortMid > start && shortNums[shortMid - 1] > longNums[longMid]) {
                // shortMid的值过大
                end = shortMid - 1;
            } else if (shortMid < end && shortNums[shortMid] < longNums[longMid - 1]) {
                // shortMid的值过小
                start = shortMid + 1;
            } else {
                /*
                    考虑边界情况
                    1. shortMid = 0，表示较短的数组A都比较长数组B大
                    2. longMid = 0，表示A都比B小
                    3. shortMid = shortLen，同情况2
                    4. longMid = longLen，同情况1
                    以上四种情况，均为两个数组的某一个搜索到了边界，
                    此时另一个数组的mid值就是要找的位置（因为始终保持shortMid + longMid值为长度和的一半），
                    此时可以安全的退出，依据长度和的奇偶输出mid的值即可

                    非边界情况而进入else分之，则表明A[shortMid - 1] > B[longMid]和A[shortMid] < B[longMid - 1]都不满足，
                    且两个中值都在合法范围内，即A[shortMid - 1] <= B[longMid] && B[longMid - 1] >= A[shortMid]，
                    再加上前置条件（shortMid + longMid值为长度和的一半），则中位数依据找到，
                    此时可以安全的退出，依据长度和的奇偶输出mid的值即可
                 */
                int leftMax = 0;
                if (shortMid == 0) {
                    leftMax = longNums[longMid - 1];
                } else if (longMid == 0) {
                    leftMax = shortNums[shortMid - 1];
                } else {
                    leftMax = Math.max(shortNums[shortMid - 1], longNums[longMid - 1]);
                }

                // 注意：如果数组长度之和为奇数，halfLen位于中位数的后面一位（halfLen = (shortLen + longLen + 1) / 2），
                // 因此其左边紧挨着的较大值即为中位数
                if ((shortLen + longLen) % 2 == 1) {
                    return leftMax;
                }

                int rightMin = 0;
                if (shortMid == shortLen) {
                    rightMin = longNums[longMid];
                } else if (longMid == longLen) {
                    rightMin = shortNums[shortMid];
                } else {
                    rightMin = Math.min(longNums[longMid], shortNums[shortMid]);
                }

                return 1.0 * (leftMax + rightMin) / 2;
            }
        }
        return 0.0;
    }

    /**
     * 上诉简化版
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
            || (nums1.length == 0 && nums2.length == 0)) {
            return 0.0D;
        }

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays1(nums2, nums1);
        }

        int shortLen = nums1.length, longLen = nums2.length;
        int midLeft = 0, midRight = 0;
        // right 要初始化为shortLen
        // 因为分割线是有可能位于第一个数组的最右边的
        int left = 0, right = shortLen;
        int i, j;
        int sMidLeft = 0, sMidRight = 0, lMidLeft = 0, lMidRight = 0;
        while (left <= right) {
            i = left + (right - left) / 2;
            // 如果i取[0-shortLen]，可以计算的j取[0-longLen]
            j = (shortLen + longLen + 1) / 2 - i;
            sMidLeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            sMidRight = i == shortLen ? Integer.MAX_VALUE : nums1[i];
            lMidLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            lMidRight = j == longLen ? Integer.MAX_VALUE : nums2[j];

            // 目标是nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i] ，它的形式是「条件 1 && 条件 2」
            // 只需要对其中一个取反即可完成查找
            if (sMidLeft > lMidRight) {
                right = i - 1;
            } else {
                midLeft = Math.max(sMidLeft, lMidLeft);
                midRight = Math.min(sMidRight, lMidRight);
                left = i + 1;
            }
        }

        return (shortLen + longLen) % 2 != 0 ? midLeft : 1.0 * (midLeft + midRight) / 2;
    }

}
