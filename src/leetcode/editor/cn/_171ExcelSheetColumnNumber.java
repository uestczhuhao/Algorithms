package leetcode.editor.cn;

public class _171ExcelSheetColumnNumber {
    public static void main(String[] args) {
        Solution t = new _171ExcelSheetColumnNumber().new Solution();
        System.out.println(t.titleToNumber("AB"));
        System.out.println(t.titleToNumber("ZY"));
        System.out.println(t.titleToNumber("FXSHRXW"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int titleToNumber(String columnTitle) {
            int ans = 0;
            int factor = 1;
            for (int i = columnTitle.length() - 1; i >=0; i--) {
                ans += (columnTitle.charAt(i) - 'A' + 1) * factor;
                factor *= 26;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
