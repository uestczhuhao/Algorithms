package leetcode.editor.cn;

//给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。 
//
// 下图是字符串 s1 = "great" 的一种可能的表示形式。 
//
//     great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
// 
//
// 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。 
//
// 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。 
//
//     rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
// 
//
// 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。 
//
// 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。 
//
//     rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
// 
//
// 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。 
//
// 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。 
//
// 示例 1: 
//
// 输入: s1 = "great", s2 = "rgeat"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "abcde", s2 = "caebd"
//输出: false 
// Related Topics 字符串 动态规划 
// 👍 183 👎 0


import java.util.Objects;

public class _87ScrambleString {
    public static void main(String[] args) {
        Solution t = new _87ScrambleString().new Solution();
        String s1 = "great", s2 = "rgeat";
        System.out.println(t.isScramble(s1, s2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：动态规划，dp[i][j][len] 表示，s1 中以i 开头的和s2 中以j 开头的，长度为len的子串是否可以相互扰乱
         * 其中dp[i][j][len] = (dp[i][j][p] && dp[i + p][j + p][len - p]) || (dp[i][j + len - p][p] && dp[i + p][j][len - p]) ，其中 1 <= p <= len - 1
         */
        public boolean isScramble(String s1, String s2) {
            if (s1 == null || s2 == null) {
                return Objects.equals(s1, s2);
            }

            // 长度不同，直接返回false
            if (s1.length() != s2.length()) {
                return false;
            }

            int totalLen = s1.length();
            boolean[][][] dp = new boolean[totalLen][totalLen][totalLen + 1];
            for (int i = 0; i < totalLen; i++) {
                for (int j = 0; j < totalLen; j++) {
                    dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
                }
            }
            // 先递归长度，保证当计算len = 3时，len = 2的结果全部已经计算完成（因为前者要依赖后者的结果）
            for (int len = 2; len <= totalLen; len++) {
                for (int i = 0; i <= totalLen - len; i++) {
                    for (int j = 0; j <= totalLen - len; j++) {
                        for (int k = 1; k <= len - 1; k++) {
                            if ((dp[i][j][k] && dp[i + k][j + k][len - k])
                                || (dp[i][j + len - k][k] && dp[i + k][j][len - k])) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
            return dp[0][0][totalLen];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}