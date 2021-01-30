package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/11/22 23:03
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class _49GroupAnagrams {
    /**
     * 思路：字符串转换，把abbb 转换为 13000...00 (长度为26)，再用map统计汇总转换后相同的字符串
     * 理由是字母异位词经过转换后的字符串相同，可以作为区分标志
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (null == strs || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> groupMap = new HashMap<>();
        char[] charCount;
        for (String s: strs) {
             charCount = new char[26];

            char[] strChars = s.toCharArray();
            for (char ch:strChars) {
                charCount[ch - 'a'] ++;
            }
            String key = new String(charCount);
            if (!groupMap.containsKey(key)) {
                groupMap.put(key, new ArrayList<>());
            }
            groupMap.get(key).add(s);
        }

        return new ArrayList<>(groupMap.values());
    }

    public static void main(String[] args) {
        String[] inputs = new String[]{"abbbbbb", "aaaaab"};
        _49GroupAnagrams t = new _49GroupAnagrams();
        System.out.println(t.groupAnagrams(inputs));
    }
}
