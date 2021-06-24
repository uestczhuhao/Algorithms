package leetcode.editor.cn;

//给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。 
//
// 
//
// 示例： 
//
// 
//输入：
//[1,2,3]
//输出：
//3
//解释：
//只需要3次操作（注意每次操作会增加两个元素的值）：
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
// 
// Related Topics 数组 数学 
// 👍 231 👎 0


public class _453MinimumMovesToEqualArrayElements {
    public static void main(String[] args) {
        Solution t = new _453MinimumMovesToEqualArrayElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将除了一个元素之外的全部元素+1，等价于将该元素-1，因为我们只对元素的相对大小感兴趣。
         */
        public int minMoves(int[] nums) {
            int min = Integer.MAX_VALUE;
            int count = 0;
            for (int n : nums) {
                min = Math.min(min, n);
                count += n;
            }

            return count - nums.length * min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}