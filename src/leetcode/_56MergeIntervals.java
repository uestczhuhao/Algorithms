package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author mizhu
 * @date 2021/1/26 21:05
 */
public class _56MergeIntervals {
    /**
     * 核心思路：按起点排序后，从前往后merge，只有以下两种情况
     * 1 新区间比现有区间都大，直接放在最右侧
     * 2 新区间的右侧和旧区间列表的最后一个对比，取最大值为旧区间的最后一个的最大值
     * <p>
     * 注意：在2中不必考虑新区间的左侧，因为按起点排序后，新区间起点不可能小于已合并区间的任意一个的起点
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        // 根据区间起点排序
        Arrays.sort(intervals, Comparator.comparingInt(valArr -> valArr[0]));
        int[][] merged = new int[intervals.length][];
        int preIndex = -1;
        for (int[] valArr : intervals) {
            int left = valArr[0];
            int right = valArr[1];
            // 题解情况1
            if (preIndex < 0 || merged[preIndex][1] < left) {
                merged[++preIndex] = valArr;
            } else {
                // 情况2，只需要更新最右侧区间最大值
                merged[preIndex][1] = Math.max(right, merged[preIndex][1]);
            }
        }

        return Arrays.copyOf(merged, preIndex + 1);
    }

    public static void main(String[] args) {
        _56MergeIntervals t = new _56MergeIntervals();
        int[][] nums  = {{4,5},{1,4}};
        int[][] nums1  = {{4,5},{1,4}};
        int[][][] n = new int[2][][];
        n[0] = nums;
        n[1] = nums1;
//        System.out.println(Arrays.deepToString(t.merge(nums)));
        System.out.println(Arrays.deepToString(n));
    }
}
