package leetcode.editor.cn;

//当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 
//'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。 
//
// 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "YazaAay"
//输出："aAa"
//解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
//"aAa" 是最长的美好子字符串。
// 
//
// 示例 2： 
//
// 
//输入：s = "Bb"
//输出："Bb"
//解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。 
//
// 示例 3： 
//
// 
//输入：s = "c"
//输出：""
//解释：没有美好子字符串。 
//
// 示例 4： 
//
// 
//输入：s = "dDzeE"
//输出："dD"
//解释："dD" 和 "eE" 都是最长美好子字符串。
//由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含大写和小写英文字母。 
// 
// Related Topics 字符串 
// 👍 10 👎 0


public class _1763LongestNiceSubstring {
    public static void main(String[] args) {
        Solution t = new _1763LongestNiceSubstring().new Solution();
        String s = "dDzeE";
        String s1 = "c";
//        System.out.println(t.longestNiceSubstring(s1));
        System.out.println(t.longestNiceSubstring(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 暴力解法，能过
        public String longestNiceSubstring1(String s) {
            int left = 0, right = -1, maxLen = 0;
            for (int i = 0; i < s.length() - maxLen; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (isNiceString(s, i, j)) {
                        if (j - i + 1 > maxLen) {
                            maxLen = j - i + 1;
                            left = i;
                            right = j;
                        }
                    }
                }
            }

            return s.substring(left, right + 1);
        }

        public boolean isNiceString(String s, int left, int right) {
            int[] chs = new int[32];
            int[] upChs = new int[32];

            for (int i = left; i <= right; i++) {
                char ch = s.charAt(i);
                if (Character.isUpperCase(s.charAt(i))) {
                    upChs[ch - 'A'] = 1;
                } else {
                    chs[ch - 'a'] = 1;
                }
            }

            for (int i = 0; i < 32; i++) {
                if ((chs[i] + upChs[i]) % 2 == 1) {
                    return false;
                }
            }
            return true;
        }


        // 全局最大最小值
        int start = 0, end = -1;

        // 滑动窗口
        public String longestNiceSubstring(String s) {
            // 统计出满足条件的总个数
            // 条件：s中存在某个字符的大小写
            int totalTypes = 0;
            char[] ch = new char[26];
            for (int i = 0; i < s.length(); i++) {
                char lower = Character.toLowerCase(s.charAt(i));
                if (ch[lower - 'a'] == 0) {
                    totalTypes++;
                }
                ch[lower - 'a']++;
            }

            // 对满足条件的每一个值i，在s中找到满足i的最大长度
            for (int i = 1; i <= totalTypes; i++) {
                int[] sub = computeSubLen(s, i);
                if (sub[1] - sub[0] > end - start) {
                    start = sub[0];
                    end = sub[1];
                }
            }

            return s.substring(start, end + 1);
        }

        /**
         * 计算s中有nums种字符（大小写都有）时的左右边界
         */
        private int[] computeSubLen(String s, int nums) {
            char[] upper = new char[26];
            char[] lower = new char[26];
            int left = 0, right = 0;
            int start = 0, end = -1;
            // 当前窗口中存在的字符种类数
            // 存在大小写的任意一种都算
            int lowOrUpType = 0;
            while (right < s.length()) {
                char curCh = s.charAt(right);
                if (curCh >= 'a' && curCh <= 'z') {
                    if (lower[curCh - 'a'] == 0 && upper[curCh - 'a'] == 0) {
                        lowOrUpType++;
                    }
                    lower[curCh - 'a']++;
                } else {
                    if (upper[curCh - 'A'] == 0 && lower[curCh - 'A'] == 0) {
                        lowOrUpType++;
                    }
                    upper[curCh - 'A']++;
                }

                while (lowOrUpType > nums) {
                    curCh = s.charAt(left++);
                    if (curCh >= 'a' && curCh <= 'z') {
                        lower[curCh - 'a']--;
                        if (lower[curCh - 'a'] == 0 && upper[curCh - 'a'] == 0) {
                            lowOrUpType--;
                        }
                    } else {
                        upper[curCh - 'A']--;
                        if (upper[curCh - 'A'] == 0 && lower[curCh - 'A'] == 0) {
                            lowOrUpType--;
                        }
                    }
                }
                // 字符大小都存在的种类数
                int typeNum = 0;
                for (int i = 0; i < 26; i++) {
                    if (lower[i] > 0 && upper[i] > 0) {
                        typeNum++;
                    }
                }
                if (typeNum == nums && right - left > end - start) {
                    start = left;
                    end = right;
                }

                right++;
            }

            return new int[]{start, end};
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}