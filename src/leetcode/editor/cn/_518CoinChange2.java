package leetcode.editor.cn;

//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
//
// 
//
// 
// 
//
// 示例 1: 
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2: 
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
// 
//
// 示例 3: 
//
// 输入: amount = 10, coins = [10] 
//输出: 1
// 
//
// 
//
// 注意: 
//
// 你可以假设： 
//
// 
// 0 <= amount (总金额) <= 5000 
// 1 <= coin (硬币面额) <= 5000 
// 硬币种类不超过 500 种 
// 结果符合 32 位符号整数 
// 
// 👍 328 👎 0


import java.util.Arrays;

public class _518CoinChange2 {
    public static void main(String[] args) {
        Solution t = new _518CoinChange2().new Solution();
        int[] coins = {1,2,5};
        System.out.println(t.change(5, coins));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 完全背包问题：dp[i] = dp[i] + dp[i-coins[i]]
         * 其中dp[i] 表示不取coins[i]，dp[i-coins[i]] 表示要取coins[i]
         *
         * 注意：循环不能交换，理由是若交换，上一层的 dp[i - k1]  dp[i - k2] 已经选取了k1，k2
         * 当下一层选k1 时，从dp[i - k1] 到 dp[i]，当下一层选k2 时，从dp[i - k2] 到 dp[i]
         * 则k1， k2的组合选了两次，分别是k1 和 k2
         *
         * 因此要先遍历物品，防止这种情况的出现
         */
        public int change(int amount, int[] coins) {
            if (amount < 0) {
                return 0;
            }
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            // 遍历coins
            for (int coin : coins) {
                // 遍历容量
                for (int j = coin; j <= amount; j++) {
                    dp[j] += dp[j - coin];
                }
                System.out.println(Arrays.toString(dp));
            }

            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}