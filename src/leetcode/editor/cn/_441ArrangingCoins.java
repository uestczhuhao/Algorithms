package leetcode.editor.cn;

//你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。 
//
// 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5
//输出：2
//解释：因为第三行不完整，所以返回 2 。
// 
//
// 示例 2： 
//
// 
//输入：n = 8
//输出：3
//解释：因为第四行不完整，所以返回 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 二分查找 👍 132 👎 0


public class _441ArrangingCoins {
    public static void main(String[] args) {
        Solution t = new _441ArrangingCoins().new Solution();
        System.out.println(t.arrangeCoins(1804289383));
        System.out.println((long) Math.pow(2, 31));
        System.out.println(Integer.MAX_VALUE);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 二分法，用<的方式，把结果归拢到层数k，其中k为总硬币数小于等于n的最大的台阶
         * @param n
         * @return
         */
        public int arrangeCoins(int n) {
            long high = (long) Math.sqrt(2 * (long) n);
            long low = (long) Math.sqrt(n);
            while (low < high) {
                long mid = low + (high - low + 1) / 2;
                if ((1 + mid) * mid / 2 <= n) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            return (int) low;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}