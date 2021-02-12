package leetcode.editor.cn;

//给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。 
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3, k = 3
//输出："213"
// 
//
// 示例 2： 
//
// 
//输入：n = 4, k = 9
//输出："2314"
// 
//
// 示例 3： 
//
// 
//输入：n = 3, k = 1
//输出："123"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 1 <= k <= n! 
// 
// Related Topics 数学 回溯算法 
// 👍 467 👎 0


public class _60PermutationSequence {
    public static void main(String[] args) {
        Solution t = new _60PermutationSequence().new Solution();
        System.out.println(t.getPermutation(4,7));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：当第一个数字为1时，总共有(n-1)! 种可能，如果k < (n-1)! ，则可以判定第1个数字为1，否则第一个数字为2...
         * 同理，可以判定第2个数字，第3个数字...第n个数字
         */
        public String getPermutation(int n, int k) {
            if (k < 0) {
                return "";
            }

            // 先求阶乘序列
            int[] factorial = new int[n];
            factorial[0] = 1;
            for (int i = 1; i < n; i++) {
                factorial[i] = factorial[i - 1] * i;
            }

            // 数字的全排列，每个数字只能取一次
            boolean[] visited = new boolean[n + 1];
            StringBuilder ans = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 1; j <= n; j++) {
                    if (visited[j]) {
                        continue;
                    }

                    // 从高位到低位筛选
                    // 如，j == 1时，k > factorial[i]
                    // 代表当此位不能取1（取1的所有可能加起来也到不了k），因此j要往后取值
                    if (k > factorial[i]) {
                        k -= factorial[i];
                        continue;
                    }

                    visited[j] = true;
                    ans.append(j);
                    break;
                }
            }
            return ans.toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}