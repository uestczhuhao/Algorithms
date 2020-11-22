package LeetCode;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupList = new ArrayList<>();
        if (null == strs || strs.length == 0) {
            return groupList;
        }
        HashMap<Set<Character>, Integer> groupIndexMap = new HashMap<>();
        int index = 0;
        for (String str : strs) {
            Set<Character> charSet = new HashSet<>();
            char[] chars = str.toCharArray();
            for (Character c : chars) {
                charSet.add(c);
            }
            List<String> strList = new ArrayList<>();

            if (groupIndexMap.containsKey(charSet) && groupIndexMap.get(charSet) < groupList.size()) {
                strList = groupList.get(groupIndexMap.get(charSet));
            } else {
                if (!groupIndexMap.containsKey(charSet)) {
                    groupIndexMap.put(charSet, index++);
                }
                groupList.add(strList);
            }
            strList.add(str);
        }

        return groupList;

    }

    public static void main(String[] args) {
        String[] inputs = new String[]{"abbbbbb", "bbbbbba"};
        _49GroupAnagrams t = new _49GroupAnagrams();
        System.out.println(t.groupAnagrams(inputs));
    }
}
