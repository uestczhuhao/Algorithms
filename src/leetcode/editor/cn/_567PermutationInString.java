package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class _567PermutationInString {
    public static void main(String[] args) {
        Solution t = new _567PermutationInString().new Solution();
        System.out.println(t.checkInclusion("ab", "eidboaoo"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int[] tgtCharNum = new int[26];
            int[] srcCharNum = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                int curIndex = s1.charAt(i) - 'a';
                tgtCharNum[curIndex]++;
            }

            int left = 0;
            for (int right = 0; right < s2.length(); right++) {
                int curRightIndex = s2.charAt(right) - 'a';
                srcCharNum[curRightIndex]++;
                while (srcCharNum[curRightIndex] > tgtCharNum[curRightIndex]) {
                    char curLeftChar = s2.charAt(left++);
                    srcCharNum[curLeftChar - 'a']--;
                }

                if (right - left + 1 == s1.length()) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
