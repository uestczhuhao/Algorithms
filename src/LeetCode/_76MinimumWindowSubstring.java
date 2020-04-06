package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/4/6 16:21
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class _76MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    /**
     * 滑动窗口解法，left和right两个指针
     * left用于缩小窗口（满足条件）
     * right用于不满足条件时扩张窗口
     */
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> candidateFreqMap = new HashMap<>(t.length());
        for (char c : t.toCharArray()) {
            candidateFreqMap.put(c, candidateFreqMap.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        String minSubStr = "";
        int minSubLen = Integer.MAX_VALUE;
        int foundCount = 0;
        Map<Character, Integer> windowCharNumMap = new HashMap<>();
        while (right < s.length()) {
            char curChar = s.charAt(right);
            windowCharNumMap.put(curChar, windowCharNumMap.getOrDefault(curChar, 0) + 1);
            if (candidateFreqMap.containsKey(curChar)
                    && candidateFreqMap.get(curChar).equals(windowCharNumMap.get(curChar))) {
                foundCount++;
            }

            while (left <= right && foundCount == candidateFreqMap.size()) {
                if (right - left + 1 < minSubLen) {
                    minSubLen = right - left + 1;
                    minSubStr = s.substring(left, right + 1);
                }
                windowCharNumMap.put(s.charAt(left), windowCharNumMap.getOrDefault(s.charAt(left), 0) - 1);
                if (candidateFreqMap.containsKey(s.charAt(left)) && candidateFreqMap.get(s.charAt(left)) > windowCharNumMap.get(s.charAt(left))) {
                    foundCount--;
                }

                left++;
            }

            right++;
        }

        return minSubStr;
    }
}
