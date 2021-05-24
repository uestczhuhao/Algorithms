package leetcode.editor.cn;

//有台奇怪的打印机有以下两个特殊要求： 
//
// 
// 打印机每次只能打印由 同一个字符 组成的序列。 
// 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。 
// 
//
// 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabbb"
//输出：2
//解释：首先打印 "aaa" 然后打印 "bbb"。
// 
//
// 示例 2： 
//
// 
//输入：s = "aba"
//输出：2
//解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由小写英文字母组成 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 184 👎 0


import java.util.Map;

public class _664StrangePrinter {
    public static void main(String[] args) {
        Solution t = new _664StrangePrinter().new Solution();
        System.out.println(t.strangePrinter("aaabbb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划，dp[i][j]表示打印i～j之间字符需要的最小打印次数
         * 当s[i] == s[j] 时，打印i处字符时可以顺便打印j处字符，不影响打印次数，dp[i][j] = dp[i][j-1]
         * 当s[i] != s[j] 时，需要枚举i～j-1的所有可能性dp[i][j] = min(dp[i][k] + dp[k+1][j])，i<=k<=j-1
         * 注意：dp[i][j]依赖 dp[i][k] 和 dp[k+1][j] 的值，即下边和左边的值，要注意顺序
         */
        public int strangePrinter(String s) {
            int len = s.length();
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = 1;
            }

            for (int i = len - 1; i >= 0; i--) {
                for (int j = i + 1; j < len; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        int min = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++) {
                            min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                        }
                        dp[i][j] = min;
                    }
                }
            }

            return dp[0][len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}