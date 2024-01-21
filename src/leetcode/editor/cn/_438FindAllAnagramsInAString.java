package leetcode.editor.cn;

//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
//
// 说明：
//
//
// 字母异位词指字母相同，但排列不同的字符串。
// 不考虑答案输出的顺序。
//
//
// 示例 1:
//
//
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
//
//
// 示例 2:
//
//
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
//
// Related Topics 哈希表
// 👍 458 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution t = new _438FindAllAnagramsInAString().new Solution();
        System.out.println(t.findAnagrams("cbaebabacd", "abc"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> result = new ArrayList<>();
            if (s == null || p == null || s.length() < p.length()) {
                return result;
            }

            int srcLen = s.length();
            int tgtLen = p.length();
            int[] srcWindow = new int[26];
            int[] tgtWindow = new int[26];
            for (int i = 0; i < tgtLen; i++) {
                srcWindow[s.charAt(i) - 'a']++;
                tgtWindow[p.charAt(i) - 'a']++;
            }
            if (Arrays.equals(srcWindow, tgtWindow)) {
                result.add(0);
            }
            for (int i = tgtLen; i < srcLen; i++) {
                srcWindow[s.charAt(i - tgtLen) - 'a']--;
                srcWindow[s.charAt(i) - 'a'] ++;
                if (Arrays.equals(srcWindow, tgtWindow)) {
                    result.add(i - tgtLen + 1);
                }
            }
            return result;
        }

        /**
         * 思路：滑动窗口，记为window
         * 其中存放每个字符出现的次数
         * 当left和right确定时，window数组应该和目标数组targetChars一致
         * 否则，当window中的right位置的字符大于targetChars时，向右移动left，保证二者相等
         * 注意不能移动right，因为此时已经超标，只能尝试移动left，使得子串变短
         */
        public List<Integer> findAnagrams1(String s, String p) {
            List<Integer> result = new ArrayList<>();
            if (s == null || p == null || s.length() < p.length()) {
                return result;
            }

            int srcLen = s.length();
            int tgtLen = p.length();
            // 滑动窗口，存放当前位置（left，right固定）出现的每个字符的次数
            int[] srcWindow = new int[26];
            // 目标数组，存放目标每个字符出现的次数
            int[] targetChars = new int[26];
            for (int i = 0; i < tgtLen; i++) {
                targetChars[p.charAt(i) - 'a']++;
            }

            int left = 0;
            for (int right = 0; right < srcLen; right++) {
                int curRightChIndex = s.charAt(right) - 'a';
                srcWindow[curRightChIndex]++;

                while (srcWindow[curRightChIndex] > targetChars[curRightChIndex]) {
                    char curLeftChar = s.charAt(left++);
                    srcWindow[curLeftChar - 'a']--;
                }

                if (right - left + 1 == tgtLen) {
                    result.add(left);
                }
            }
            return result;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
