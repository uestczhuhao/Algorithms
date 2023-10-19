package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class _205IsomorphicStrings {
    public static void main(String[] args) {
        Solution t = new _205IsomorphicStrings().new Solution();
        System.out.println(t.isIsomorphic("egg", "add"));
        System.out.println(t.isIsomorphic("foo", "bar"));
        System.out.println(t.isIsomorphic("paper", "title"));
        System.out.println(t.isIsomorphic("aaa", "bbc"));
        System.out.println(t.isIsomorphic("badc", "baba"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isIsomorphic(String s, String t) {
            // ASCII码一共128个值
            int[] sIndex = new int[128];
            int[] tIndex = new int[128];
            int n = s.length();
            for (int i = 0; i < n; i++) {
                int i1 = sIndex[s.charAt(i)];
                int i2 = tIndex[t.charAt(i)];
                if (i1 != i2) {
                    return false;
                }
                // int数组初始化为0，因此映射时应该避开0（0的含义是从未出现过）
                sIndex[s.charAt(i)] = i + 1;
                tIndex[t.charAt(i)] = i + 1;
            }
            return true;
        }

        // 在长时间（1 <= s.length <= 5 * 10^4）的执行中，hashmap比数组慢约3倍
        public boolean isIsomorphic1(String s, String t) {
            // 存放字符-位置，对应的字符如果不在相同的位置，则返回false
            Map<Character, Integer> sIndex = new HashMap<>();
            Map<Character, Integer> tIndex = new HashMap<>();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                int i1 = sIndex.getOrDefault(s.charAt(i), -1);
                int i2 = tIndex.getOrDefault(t.charAt(i), -1);
                if (i1 != i2) {
                    return false;
                }
                sIndex.put(s.charAt(i), i);
                tIndex.put(t.charAt(i), i);
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
