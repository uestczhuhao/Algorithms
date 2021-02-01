package leetcode.editor.cn;

//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 哈希表 
// 👍 628 👎 0


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _739DailyTemperatures {
    public static void main(String[] args) {
        Solution t = new _739DailyTemperatures().new Solution();
        int[] t1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temperatures = t.dailyTemperatures(t1);
        System.out.println(Arrays.toString(temperatures));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调栈，栈底到栈顶温度依次减小（栈中存放下标）
         * 0 当遍历到位置i时，比较T[i] 和 栈顶温度 T[stack.peek()]
         * 1 若栈为空，i直接入栈，代表这个温度是当前最高（或第一个）
         * 2 若T[i] > T[stack.peek()]，代表当前温度更大，则stack.peek()位置的第一个更大温度已经出现，
         * 弹出栈顶元素，回到0
         * 3 若T[i] <= T[stack.peek()]，代表当前温度更小，直接入栈即可
         */
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }
            int length = T.length;
            int[] waitDays = new int[length];
            // 单调栈，栈底到栈顶温度依次减小
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < length; i++) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int tgtPos = stack.pop();
                    waitDays[tgtPos] = i - tgtPos;
                }

                stack.push(i);
            }

            return waitDays;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}