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
        int[] nums = {1, 3, 1};
        System.out.println(t.jump(nums));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：贪心算法，从位置0出发，每次跳最大距离，判断最后是否能到最后
         * 选len-2而不是len-1的原因是，若选len-1为最后一个i的位置
         * 则每次i == curFurthest 时，要判断 curFurthest== len-1是否成立，成立则步数不+1，不成立+1
         * <p>
         * 注意：题设为一定能跳到最后一个位置，因此能到达len-2位置就一定能到最后一个位置
         */
        public int jump(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }
            int step = 0;
            // 代表当前位置i所能跳到的最远位置
            int curFurthest = 0;
            int nextFurthest = 0;
            // 注意不需要访问最后一个元素，因为在最后一个元素之前是最后一跳
            for (int i = 0; i < nums.length - 1; i++) {
                // 计算在边界内，能跳到的最远距离
                nextFurthest = Math.max(nextFurthest, i + nums[i]);
                // 代表此时处于边界，必须进行下一跳
                // 如果当前的边界boundary大于len-2，则i达不到这里，步数不需要+1，否则需要+1
                if (i == curFurthest) {
                    curFurthest = nextFurthest;
                    step++;
                }
            }

            return step;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}