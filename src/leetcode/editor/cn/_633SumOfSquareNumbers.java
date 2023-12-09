package leetcode.editor.cn;

//给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
//
//
//
// 示例 1：
//
// 输入：c = 5
//输出：true
//解释：1 * 1 + 2 * 2 = 5
//
//
// 示例 2：
//
// 输入：c = 3
//输出：false
//
//
// 示例 3：
//
// 输入：c = 4
//输出：true
//
//
// 示例 4：
//
// 输入：c = 2
//输出：true
//
//
// 示例 5：
//
// 输入：c = 1
//输出：true
//
//
//
// 提示：
//
//
// 0 <= c <= 231 - 1
//
// Related Topics 数学
// 👍 266 👎 0


public class _633SumOfSquareNumbers {
    public static void main(String[] args) {
        Solution t = new _633SumOfSquareNumbers().new Solution();
        System.out.println(t.judgeSquareSum(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 双指针，从1到sqrt(c)，
         * 若二者平方和小于c，则left++
         * 若大于c，则right --
         * 等于c，则找到了题解，返回true
         * 正确性证明：https://leetcode.cn/problems/sum-of-square-numbers/solutions/748260/shuang-zhi-zhen-de-ben-zhi-er-wei-ju-zhe-ebn3/
         */
        public boolean judgeSquareSum(int c) {
            int left = 0, right = (int) Math.sqrt(c);
            while (left <= right) {
                if (left * left + right * right > c) {
                    right --;
                } else if (left * left + right * right < c) {
                    left ++;
                } else {
                    return true;
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
