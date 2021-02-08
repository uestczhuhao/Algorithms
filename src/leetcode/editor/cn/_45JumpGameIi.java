package leetcode.editor.cn;

//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组 
// 👍 821 👎 0


public class _45JumpGameIi {
    public static void main(String[] args) {
        Solution t = new _45JumpGameIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：贪心算法，从位置0出发，每次跳最大距离，判断最后是否能到最后
         *
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }
            int step = 1;
            // 代表当前位置i所能跳到的最远位置
            // 初始化：第一跳的边界为nums[0]
            int boundary = nums[0];
            int furthest = 0;
            // 注意不需要访问最后一个元素，因为在最后一个元素之前是最后一跳
            for (int i = 1; i < nums.length - 1; i++) {
                // 计算在边界内，能跳到的最远距离
                furthest = Math.max(furthest, i+ nums[i]);
                // 代表此时处于边界，必须进行下一跳
                if (i == boundary) {
                    boundary = furthest;
                    step ++;
                }
            }

            return step;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}