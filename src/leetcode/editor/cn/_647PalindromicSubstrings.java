package leetcode.editor.cn;

//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 479 👎 0


public class _647PalindromicSubstrings {
    public static void main(String[] args) {
        Solution t = new _647PalindromicSubstrings().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：中心扩展法，中心有一个（aba）或两个（baab）
         * 遍历每个中心点，共有2*len - 1个，其中中心一个的有 len个，中心两个的有 len - 1个
         */
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                // 中心有1个或两个
                for (int j = 0; j < 2; j++) {
                    int left = i, right = i + j;
                    while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right ++)) {
                        num ++;
                    }
                }
            }
            return num;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}