package leetcode.editor.cn;

//给你一个仅由数字组成的字符串 s 。 
//
// 请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。 
//
// 
// 例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1
// ，这种拆分方法可行。 
// 另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些
//拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。 
// 
//
// 如果可以按要求拆分 s ，返回 true ；否则，返回 false 。 
//
// 子字符串 是字符串中的一个连续字符序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1234"
//输出：false
//解释：不存在拆分 s 的可行方法。
// 
//
// 示例 2： 
//
// 
//输入：s = "050043"
//输出：true
//解释：s 可以拆分为 ["05", "004", "3"] ，对应数值为 [5,4,3] 。
//满足按降序排列，且相邻值相差 1 。
// 
//
// 示例 3： 
//
// 
//输入：s = "9080701"
//输出：false
//解释：不存在拆分 s 的可行方法。
// 
//
// 示例 4： 
//
// 
//输入：s = "10009998"
//输出：true
//解释：s 可以拆分为 ["100", "099", "98"] ，对应数值为 [100,99,98] 。
//满足按降序排列，且相邻值相差 1 。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
// Related Topics 递归 字符串 回溯算法 
// 👍 13 👎 0


import java.util.ArrayList;
import java.util.List;

public class _1849SplittingAStringIntoDescendingConsecutiveValues {
    public static void main(String[] args) {
        Solution t = new _1849SplittingAStringIntoDescendingConsecutiveValues().new Solution();
        String s = "14321";
        String s1 = "3202872336";
        System.out.println(t.splitString(s));
//        System.out.println(t.splitString(s1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean splitString(String s) {
            int index = 0;
            for (; index < s.length(); index++) {
                if (s.charAt(index) != '0') {
                    break;
                }
            }

            // 注意这里，因为至少要划分两个子串，因此i到n-2即可
            for (int i = index; i < s.length() - 1; i++) {
                long cur = Long.parseLong(s.substring(index, i + 1));
                // s一共20位，至少分成两个数
                // 第一个数的位数大于10位时，第二位最多9位，二者不可能只差1
                if (cur > Math.pow(10, 10)) {
                    return false;
                }

                if (dfs(s, i + 1, cur)) {
                    return true;
                }
            }

            return false;
        }

        private boolean dfs(String s, int start, long pre) {
            if (start == s.length()) {
                return true;
            }

            for (int i = start; i < s.length(); i++) {
                long cur = Long.parseLong(s.substring(start, i + 1));
                if (cur >= pre) {
                    break;
                }

                if (cur > Math.pow(10, 10)) {
                    return false;
                }
                if (cur + 1 == pre && dfs(s, i + 1, cur)) {
                    return true;
                }
            }

            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}