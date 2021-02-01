package leetcode.editor.cn;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 938 👎 0


import java.util.Arrays;

public class _283MoveZeroes {
    public static void main(String[] args) {
        Solution t = new _283MoveZeroes().new Solution();
        int[] a = {0, 1, 0, 3, 12};
        t.moveZeroes(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：移动非0值到数组头，剩下到补0
         */
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            int nonZeroIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[nonZeroIndex++] = nums[i];
                }
            }

            while (nonZeroIndex < nums.length) {
                nums[nonZeroIndex++] = 0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}