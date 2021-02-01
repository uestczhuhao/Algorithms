package leetcode.editor.cn;

//给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。 
//
// 找到所有在 [1, n] 范围之间没有出现在数组中的数字。 
//
// 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。 
//
// 示例: 
//
// 
//输入:
//[4,3,2,7,8,2,3,1]
//
//输出:
//[5,6]
// 
// Related Topics 数组 
// 👍 547 👎 0


import java.util.ArrayList;
import java.util.List;

public class _448FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        Solution t = new _448FindAllNumbersDisappearedInAnArray().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：由于1-n的位置放1-n的数，可以思考在i位置的值nums[i]
         * 以nums[i] 为下标，找到 nums[i] - 1 位置，把这个位置的数取反（或做其他能起到标志作用的标记）
         * 第二次遍历时，遇到了负数，则其下表 index + 1代表的数已经出现过了，否则该数没出现过，记录下来
         */
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> result = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return result;
            }
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                int index = Math.abs(nums[i]) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            for (int i = 0; i < len; i++) {
                if (nums[i] > 0) {
                    result.add(i + 1);
                }
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}