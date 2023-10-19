package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _290WordPattern {
    public static void main(String[] args) {
        Solution t = new _290WordPattern().new Solution();
        String pa = "abba";
        String s = "dog dog dog dog";
        System.out.println(t.wordPattern(pa, s));
        System.out.println(t.wordPattern(pa, "dog cat cat fish"));
        System.out.println(t.wordPattern(pa, "dog cat cat dog"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean wordPattern1(String pattern, String s) {
            String[] arr = s.split("\\s+");
            if (pattern.length() != arr.length) {
                return false;
            }
            Map<String, Character> strPatMap = new HashMap<>();
            Map<Character, String> patStrMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                Character curCh = pattern.charAt(i);
                String curStr = arr[i];
                strPatMap.putIfAbsent(curStr, curCh);
                patStrMap.putIfAbsent(curCh, curStr);
                if (!curStr.equals(patStrMap.get(curCh)) || !curCh.equals(strPatMap.get(curStr))) {
                    return false;
                }
            }
            return true;
        }

        public boolean wordPattern2(String pattern, String s) {
            String[] arr = s.split("\\s+");
            int n = arr.length;
            if (pattern.length() != arr.length) {
                return false;
            }
            String[] strs = new String[26];
            HashMap<String, Character> visited = new HashMap<>();
            for (int i = 0; i < n; i++) {
                char ch = pattern.charAt(i);
                if (strs[ch - 'a'] == null) {
                    strs[ch - 'a'] = arr[i];
                }
                if (!arr[i].equals(strs[ch - 'a'])) {
                    return false;
                }
                if (visited.containsKey(arr[i]) && ch != visited.get(arr[i])) {
                    return false;
                }
                visited.put(arr[i], ch);
            }
            return true;
        }

        /**
         * 思路：建立 字符（串） -> 位置的关系映射
         * 当遇到新字符或字符串时，首先看映射map中有没有记录，如果有，则返回其位置，然后比较二者位置是否一致
         * 若不一致则返回false，一致则把新的字符（串） -> 位置映射放入map
         */
        public boolean wordPattern(String pattern, String s) {

            String[] arr = s.split("\\s+");
            int n = arr.length;
            if (pattern.length() != arr.length) {
                return false;
            }
            Map<Character, Integer> chIndexMap = new HashMap<>();
            Map<String, Integer> strIndexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int chIndex = chIndexMap.getOrDefault(pattern.charAt(i), -1);
                int strIndex = strIndexMap.getOrDefault(arr[i], -1);
                if (chIndex != strIndex) {
                    return false;
                } else {
                    chIndexMap.put(pattern.charAt(i), i);
                    strIndexMap.put(arr[i], i);
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
