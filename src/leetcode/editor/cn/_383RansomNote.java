package leetcode.editor.cn;

public class _383RansomNote {
    public static void main(String[] args) {
        Solution t = new _383RansomNote().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            int m = ransomNote.length(), n = magazine.length();
            if (m > n) {
                return false;
            }

            int[] chNum = new int[26];
            for (int i = 0; i < n; i++) {
                chNum[magazine.charAt(i) - 'a'] ++;
            }

            for (int i =0;i<m;i++) {
                if (chNum[ransomNote.charAt(i) - 'a'] <= 0) {
                    return false;
                }
                chNum[ransomNote.charAt(i) - 'a'] --;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
