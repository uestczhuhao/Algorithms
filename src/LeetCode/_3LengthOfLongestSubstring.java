package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mizhu
 * @date 2020/3/28 11:12
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class _3LengthOfLongestSubstring {

    public static void main(String[] args) {
        _3LengthOfLongestSubstring t = new _3LengthOfLongestSubstring();
        String s = "abcda";
        System.out.println(t.lengthOfLongestSubstring1(s));
    }


    /**
     * 滑动窗口解法，i，j分别为滑动窗口的起始和结束位置
     * 若j处的字符已经在窗口中出现过，则i往前走一步
     * 若j处的字符未在窗口中出现过，则j加入窗口
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }

        int i = 0, j = 0, len = s.length();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        while (i < len && j < len) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                maxLength = Math.max(maxLength, j - i + 1);
                j++;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }

    /**
     * 提升：上面的解法是每次滑动窗口向前移动一格，但是如果能找到滑动窗口中与j处相等的字符（假设位置j1），
     * 就可以一次移动j1-i格，时间复杂度从2n减少到n
     */
    public int lengthOfLongestSubstring1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int i = 0, j = 0, len = s.length();
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int manLength = 0;
        while (i < len && j < len) {
            if (charIndexMap.containsKey(s.charAt(j))) {
                i = Math.max(i, charIndexMap.get(s.charAt(j)) + 1);
            }
            manLength = Math.max(manLength, j - i + 1);
            charIndexMap.put(s.charAt(j), j);
            j++;
        }

        return manLength;
    }
}
