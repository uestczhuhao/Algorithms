package leetcode.editor.cn;

//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 数学 二分查找 
// 👍 576 👎 0


public class _50PowxN {
    public static void main(String[] args) {
        Solution t = new _50PowxN().new Solution();
        t.myPow(1.00, -2147483648);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：快速求幂，每次把n缩减为半
         * 如 pow(x, 77) = pow(x, 1) * pow(x, 4) * pow(x, 8) * pow(x, 64)
         * 其中1，4，8，64恰好为77的二进制表示(1001101)中的每个1
         */
        public double myPow(double x, int n) {
            long absN = n < 0 ? -(long) n : n;
            if (n == 0 || x == 1) {
                return 1;
            }

            double answer = 1.0D;
            // absN每次右移一位，判断此时的最低位是否为1
            // 最低位为1则记入结果，否则不记入
            // 注意每次要将x *= x，理由是每次都要找到pow(x,2^n)，其中n为右移的次数
            while (absN > 0) {
                if ((absN & 1) == 1) {
                    answer *= x;
                }

                // 计算pow(x,2^n)
                x *= x;
                absN >>= 1;
            }

            return n < 0 ? 1.0 / answer : answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}