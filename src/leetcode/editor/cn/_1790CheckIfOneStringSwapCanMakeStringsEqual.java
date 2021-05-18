package leetcode.editor.cn;

//给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。 
//
// 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入：s1 = "bank", s2 = "kanb"
//输出：true
//解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
// 
//
// 示例 2： 
//
// 输入：s1 = "attack", s2 = "defend"
//输出：false
//解释：一次字符串交换无法使两个字符串相等
// 
//
// 示例 3： 
//
// 输入：s1 = "kelb", s2 = "kelb"
//输出：true
//解释：两个字符串已经相等，所以不需要进行字符串交换
// 
//
// 示例 4： 
//
// 输入：s1 = "abcd", s2 = "dcba"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 100 
// s1.length == s2.length 
// s1 和 s2 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 9 👎 0


public class _1790CheckIfOneStringSwapCanMakeStringsEqual {
    public static void main(String[] args) {
        Solution t = new _1790CheckIfOneStringSwapCanMakeStringsEqual().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 考虑 abc 和 ace的情况，本解法能过
         * 但是题目没有这种情况发生
         */
        public boolean areAlmostEqual(String s1, String s2) {
            if (s1.length() != s2.length()) {
                return false;
            }

            int diff = 0;
            int[] chs1 = new int[26];
            int[] chs2 = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                chs1[s1.charAt(i) - 'a']++;
                chs2[s2.charAt(i) - 'a']++;
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                }

                if (diff > 2) {
                    return false;
                }
            }

            for (int i = 0; i < 26; i++) {
                if (chs1[i] != chs2[i]) {
                    return false;
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}