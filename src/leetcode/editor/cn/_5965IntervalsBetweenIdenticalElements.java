package leetcode.editor.cn;

//给你一个下标从 0 开始、由 n 个整数组成的数组 arr 。
//
// arr 中两个元素的 间隔 定义为它们下标之间的 绝对差 。更正式地，arr[i] 和 arr[j] 之间的间隔是 |i - j| 。
//
// 返回一个长度为 n 的数组 intervals ，其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同
//）的 间隔之和 。
//
// 注意：|x| 是 x 的绝对值。
//
//
//
// 示例 1：
//
// 输入：arr = [2,1,3,1,2,3,3]
//输出：[4,2,7,2,4,4,5]
//解释：
//- 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
//- 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
//- 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
//- 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
//- 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
//- 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
//- 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
//
//
// 示例 2：
//
// 输入：arr = [10,5,10,10]
//输出：[5,0,3,4]
//解释：
//- 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
//- 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
//- 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
//- 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
//
//
//
//
// 提示：
//
//
// n == arr.length
// 1 <= n <= 10⁵
// 1 <= arr[i] <= 10⁵
//
// 👍 9 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _5965IntervalsBetweenIdenticalElements {
    public static void main(String[] args) {
        Solution t = new _5965IntervalsBetweenIdenticalElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 前缀和+后缀和算法，对同一个数字的下标数组indexList，要求第i个数的下标和
         * 先求出i的左边所有的值到i的距离绝对值之和left[i]，再求出右边所有值到i的距离绝对值之和right[i]，二者累加即可
         * <p>
         * left数组：left[i]为左边所有数到i的绝对值之和，考虑left[i-1]，从left[i-1]到left[i]
         * 只需要把indexList[i] - indexList[i-1]这段距离加 i 次即可
         * right数组同理
         */
        public long[] getDistances(int[] arr) {
            Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                int value = arr[i];
                valueIndexMap.putIfAbsent(value, new ArrayList<>());
                valueIndexMap.get(value).add(i);
            }

            long[] ans = new long[arr.length];
            for (Map.Entry<Integer, List<Integer>> entry : valueIndexMap.entrySet()) {
                List<Integer> indexList = entry.getValue();
                int size = indexList.size();
                long[] leftSum = new long[size];
                long[] rightSum = new long[size];
                for (int i = 1; i < size; i++) {
                    leftSum[i] = leftSum[i - 1] + (long) i * (indexList.get(i) - indexList.get(i - 1));
                }

                for (int j = size - 2; j >= 0; j--) {
                    rightSum[j] = rightSum[j + 1] + (long) (size - 1 - j) * (indexList.get(j + 1) - indexList.get(j));
                }
                for (int i = 0; i < size; i++) {
                    ans[indexList.get(i)] = leftSum[i] + rightSum[i];
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
