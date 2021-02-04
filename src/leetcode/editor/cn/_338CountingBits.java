package leetcode.editor.cn;

//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。 
//
// 示例 1: 
//
// 输入: 2
//输出: [0,1,1] 
//
// 示例 2: 
//
// 输入: 5
//输出: [0,1,1,2,1,2] 
//
// 进阶: 
//
// 
// 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？ 
// 要求算法的空间复杂度为O(n)。 
// 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。 
// 
// Related Topics 位运算 动态规划 
// 👍 494 👎 0


public class _338CountingBits {
    public static void main(String[] args) {
        Solution t = new _338CountingBits().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：num的1的个数取决于奇偶
         * 1 num为奇数，则只比前一个偶数多1个（最后那个）如：111 和 110
         * 2 num为偶数，则其最后一位为0，除以2则右移一位，1的个数不变
         */
        public int[] countBits(int num) {
            int[] answer = new int[num + 1];
            for (int i = 1; i <= num; i++) {
//                if ((i & 1) == 1) {
//                    answer[i] = answer[i - 1] + 1;
//                } else {
//                    answer[i] = answer[i / 2];
//                }
                // 改写成不需要if判断的版本
                // 若i为奇数，则answer[i] = answer[i - 1] + 1 = answer[(i - 1) / 2] + 1 = answer[i/2] + 1
                answer[i] = answer[i / 2] + (i & 1);
            }

            return answer;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}