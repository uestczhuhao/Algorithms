package leetcode.editor.cn;

//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 330 👎 0


public class _125ValidPalindrome {
    public static void main(String[] args) {
        Solution t = new _125ValidPalindrome().new Solution();
        String s = "0P";
        System.out.println(t.isPalindrome(s));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(String s) {
            if (s == null) {
                return false;
            }

            int low = 0, high = s.length() - 1;
            while (low < high) {
                char lowCh = s.charAt(low);
                char highCh = s.charAt(high);

                if (!Character.isLetter(lowCh) && !Character.isDigit(lowCh)) {
                    low++;
                    continue;
                }

                if (!Character.isLetter(highCh) && !Character.isDigit(highCh)) {
                    high--;
                    continue;
                }

                if (Character.toLowerCase(lowCh) != Character.toLowerCase(highCh)) {
                    return false;
                }
                low++;
                high--;
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}