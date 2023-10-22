package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class _452MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        Solution t = new _452MinimumNumberOfArrowsToBurstBalloons().new Solution();
//        int[][] ps = {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
        int[][] ps = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(t.findMinArrowShots(ps));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：按照区间的右端点排序，然后做合并区间
         * 取合并区间的最小右端点为标的，若后续的左端点小于等于该标的，则合并（能用一根箭射穿），否则不能射穿
         * 如：[1, 6] [2, 8] [7 12]，标的为6，第二个可以合并，第三个合并不了（7 > 6）
         */
        public int findMinArrowShots(int[][] points) {
            Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
            int[] pre;
            int i = 0, n = points.length, ans = 0;
            while (i < n) {
                pre = points[i++];
                while (i < n && points[i][0] <= pre[1]) {
                    i++;
                }
                ans++;
            }
            return ans;
        }

        public int findMinArrowShots1(int[][] points) {
            Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
            int[] pre = points[0];
            int ans = 1, i = 1;
            int low = pre[0], high = pre[1];
            while (i < points.length) {
                int[] cur = points[i++];
                if (cur[0] > high) {
                    low = cur[0];
                    high = cur[1];
                    ans++;
                } else {
                    low = Math.max(low, cur[0]);
                    high = Math.min(high, cur[1]);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
