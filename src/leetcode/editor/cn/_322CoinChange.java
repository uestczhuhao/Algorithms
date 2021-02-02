package leetcode.editor.cn;

//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 动态规划 
// 👍 1043 👎 0


import java.util.Arrays;

public class _322CoinChange {
    public static void main(String[] args) {
        Solution t = new _322CoinChange().new Solution();
        int amount = 9662;
        int[] coins = {94,91,377,368,207,40,415,61};
        System.out.println(t.coinChange(coins, amount));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：dp解法，dp[i] 表示金额为i时，最少兑换的零钱数
         * dp[i] = Min{dp[i-k] + 1}，其中k是coins数组中每个值
         */
        public int coinChange1(int[] coins, int amount) {
            if (coins == null || coins.length == 0) {
                return -1;
            }

            int[] minCoins = new int[amount + 1];
            Arrays.fill(minCoins, Integer.MAX_VALUE);
            minCoins[0] = 0;
            for (int i = 1; i <= amount; i++) {
                int curMinNum = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (coin <= i) {
                        curMinNum = Math.min(minCoins[i - coin], curMinNum);
                    }
                }
                minCoins[i] = curMinNum == Integer.MAX_VALUE ? Integer.MAX_VALUE : curMinNum + 1;
            }

            return minCoins[amount] == Integer.MAX_VALUE ? -1 : minCoins[amount];
        }

        /**
         * 思路：贪心+dfs
         * 对每一个coin属于coins，取最大的k枚硬币，即 k = amount / coin，剩下的钱数再重复这个过程
         * 当取k枚结束dfs后，再取k-1枚，取到0枚
         */
        int minNum = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            if (coins == null || coins.length == 0) {
                return -1;
            }

            Arrays.sort(coins);
            dfs(coins, amount, coins.length - 1, 0);
            return minNum == Integer.MAX_VALUE ? -1 : minNum;
        }

        private void dfs(int[] coins, int amount, int coinIndex, int count) {
            if (amount == 0) {
                minNum = Math.min(count, minNum);
                return;
            }

            if (amount < 0 || coinIndex >= coins.length || coinIndex < 0) {
                return;
            }

            for (int k = amount / coins[coinIndex]; k >= 0; k--) {
                // 剪枝，当前分之已经不可能为最小
                // 因为是从大到小取硬币，假如有5 2 1三枚，此时k = 2，count = 1
                // 如果取2枚2的+1枚5的数量都太多，那么k减小到1枚（或0枚），再取1的话，数量会更多
                // 因此直接break即可
                if (k + count >= minNum) {
                    break;
                }
                dfs(coins, amount - k * coins[coinIndex], coinIndex - 1, k + count);
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}