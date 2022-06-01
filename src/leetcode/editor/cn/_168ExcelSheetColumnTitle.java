package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <p>给你一个整数 <code>columnNumber</code> ，返回它在 Excel 表中相对应的列名称。</p>
 *
 * <p>例如：</p>
 *
 * <pre>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 1
 * <strong>输出：</strong>"A"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 28
 * <strong>输出：</strong>"AB"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 701
 * <strong>输出：</strong>"ZY"
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 2147483647
 * <strong>输出：</strong>"FXSHRXW"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= columnNumber <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>字符串</li></div></div><br><div><li>👍 537</li><li>👎 0</li></div>
 */

public class _168ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution t = new _168ExcelSheetColumnTitle().new Solution();
        System.out.println(t.convertToTitle(17577));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public String convertToTitle(int columnNumber) {
            StringBuilder sb = new StringBuilder();
            while (columnNumber > 0) {
                // 使用-1 熨平取模，假如值为26（Z），则减1后取25，A+25=Z
                columnNumber --;
                sb.append((char) ('A' + columnNumber % 26));
                columnNumber /= 26;
            }
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
