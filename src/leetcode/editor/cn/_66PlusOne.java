package leetcode.editor.cn;

//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 
// 👍 625 👎 0


import java.util.Arrays;

public class _66PlusOne {
    public static void main(String[] args) {
        Solution t = new _66PlusOne().new Solution();
        int[] digits = {1, 9, 9};
        int[] digits1 = {9, 9};
        System.out.println(Arrays.toString(t.plusOne(digits)));
        System.out.println(Arrays.toString(t.plusOne(digits1)));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            if (digits == null || digits.length == 0) {
                return digits;
            }

            int carry = 0;
            int[] answer = Arrays.copyOf(digits, digits.length);
            for (int i = answer.length - 1; i >= 0; i--) {
                int sum = answer[i] + 1;
                answer[i] = sum % 10;
                carry = sum / 10;
                if (carry == 0) {
                    break;
                }
            }

            if (carry > 0) {
                int[] answerPlus = new int[answer.length + 1];
                answerPlus[0] = carry;
                System.arraycopy(answer, 0, answerPlus, 1, answerPlus.length - 1);
                answer = answerPlus;
            }

            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}