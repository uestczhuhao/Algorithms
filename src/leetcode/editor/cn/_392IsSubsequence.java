package leetcode.editor.cn;

public class _392IsSubsequence {
    public static void main(String[] args) {
        Solution t = new _392IsSubsequence().new Solution();
        String s = "";
        String t1 = "";
        System.out.println(t.isSubsequence(s, t1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSubsequence(String s, String t) {
            if (s.length() > t.length()) {
                return false;
            }
            int i = 0, j = 0;
            while (j < t.length()) {
                if (i < s.length() && s.charAt(i) == t.charAt(j)) {
                    i ++;
                }
                j++;
            }

            return i == s.length();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
