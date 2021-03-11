package leetcode.editor.cn;

//给你一个 无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。 
//
// 示例 3： 
//
// 
//输入：intervals = [], newInterval = [5,7]
//输出：[[5,7]]
// 
//
// 示例 4： 
//
// 
//输入：intervals = [[1,5]], newInterval = [2,3]
//输出：[[1,5]]
// 
//
// 示例 5： 
//
// 
//输入：intervals = [[1,5]], newInterval = [2,7]
//输出：[[1,7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= intervals[i][0] <= intervals[i][1] <= 105 
// intervals 根据 intervals[i][0] 按 升序 排列 
// newInterval.length == 2 
// 0 <= newInterval[0] <= newInterval[1] <= 105 
// 
// Related Topics 排序 数组 
// 👍 361 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _57InsertInterval {
    public static void main(String[] args) {
        Solution t = new _57InsertInterval().new Solution();
//        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] intervals = {{1, 5}};
        int[] newInterval = {0, 0};
        int[][] res = t.insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals == null || intervals.length == 0) {
                return new int[][]{newInterval};
            }

            if (newInterval == null || newInterval.length == 0) {
                return intervals;
            }

            int low = newInterval[0];
            int high = newInterval[1];
            List<int[]> answer = new ArrayList<>();
            boolean place = false;
            for (int i = 0; i < intervals.length; i++) {
                int curIntervalLow = intervals[i][0];
                int curIntervalHigh = intervals[i][1];
                if (curIntervalHigh < low) {
                    answer.add(intervals[i]);
                } else if (curIntervalLow > high) {
                    // 如果此时已经合并的区间还没有加入结果，则加入之
                    if (!place) {
                        answer.add(new int[]{low, high});
                        place = true;
                    }
                    answer.add(intervals[i]);
                } else {
                    // 此时一定交叉，取并集即可
                    low = Math.min(curIntervalLow, low);
                    high = Math.max(curIntervalHigh, high);
                }

            }
            // 当合并的区间是最后一个时，把它加入结果
            if (!place) {
                answer.add(new int[]{low, high});
            }

            int[][] result = new int[answer.size()][];
            int index = 0;
            for (int[] ans : answer) {
                result[index++] = ans;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}