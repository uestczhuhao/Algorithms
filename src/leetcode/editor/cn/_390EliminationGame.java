package leetcode.editor.cn;

//列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
//
//
//
//
// 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
// 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
// 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
//
//
// 给你整数 n ，返回 arr 最后剩下的数字。
//
//
//
// 示例 1：
//
//
//输入：n = 9
//输出：6
//解释：
//arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
//arr = [2, 4, 6, 8]
//arr = [2, 6]
//arr = [6]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 10⁹
//
//
//
// Related Topics 数学 👍 259 👎 0


public class _390EliminationGame {
    public static void main(String[] args) {
        Solution t = new _390EliminationGame().new Solution();
        System.out.println(t.lastRemaining(9));
        System.out.println(t.lastRemaining(24));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 每个回合更新和记录head变量，当数组的总数变为1时，head就是最后的一个数
         * <p>
         * 什么时候更新这个head变量呢？
         * <p>
         * 当我们从左边开始移除的时
         * 当我们从右边开始移除并且剩余的数的总数 number % 2 == 1时
         * 比如 2 4 6 8 10，我们从10开始移除，我们将会移除10，6，2，head被移除并且变为4
         * 比如 2 4 6 8 10 12，我们从12开始移除，我们将会移除12，8，4，head仍然是2
         */
        public int lastRemaining(int n) {
            int head = 1, step = 1;
            boolean left = true;
            while (n > 1) {
                if (left || n % 2 == 1) {
                    head = head + step;
                }
                step *= 2;
                left = !left;
                n /= 2;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
