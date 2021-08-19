package leetcode.editor.cn;

//给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。 
//
// 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 
//输入：s = "leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由 可打印的 ASCII 字符组成 
// 
// Related Topics 双指针 字符串 
// 👍 203 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _345ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution t = new _345ReverseVowelsOfAString().new Solution();
        System.out.println(t.reverseVowels("aA"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseVowels(String s) {
            Set<Character> vowelSet = new HashSet<>();
            vowelSet.add('a');
            vowelSet.add('e');
            vowelSet.add('i');
            vowelSet.add('o');
            vowelSet.add('u');
            char[] chs = s.toCharArray();
            List<Integer> vowelIndexList = new ArrayList<>();
            for (int i = 0; i < chs.length; i++) {
                if (vowelSet.contains(Character.toLowerCase(chs[i]))) {
                    vowelIndexList.add(i);
                }
            }
            int len = vowelIndexList.size();
            for (int i = 0; i < len / 2; i++) {
                int src = vowelIndexList.get(i);
                int tgt = vowelIndexList.get(len - 1 - i);
                char tmp = chs[src];
                chs[src] = chs[tgt];
                chs[tgt] = tmp;
            }

            return new String(chs);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}