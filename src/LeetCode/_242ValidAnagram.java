package LeetCode;

/**
 * @author mizhu
 * @date 2020/11/12 20:31
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 */
public class _242ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null) {
            return null == t;
        }

        if (t == null) {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        int len = s.length();
        int allCharLen = 26;
        int[] charNum = new int[allCharLen];
        for (int i = 0; i < len; i++) {
            int srcIndex = s.charAt(i) - 'a';
            int tgtIndex = t.charAt(i) - 'a';
            charNum[srcIndex]++;
            charNum[tgtIndex]--;
        }

        for (int i = 0; i < allCharLen; i++) {
            if (charNum[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
