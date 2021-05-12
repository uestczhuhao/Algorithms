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
//        String s = "14321";
        String s1 = "3202872336";
//        System.out.println(t.splitString(s));
//        System.out.println(t.splitString(s1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean splitString(String s) {
            long t = 0;     //枚举第一个数字的值，因为s长度为20，所以会超过int，要用long类型
            for (int i = 0; i < s.length() - 1; i++) {  //因为必须要分割成两个子串，所以最后一个字符不可能是组成第一个数字的字符，我们这里也是为了防止刚好20位导致long也会溢出的情况
                t = t * 10 + s.charAt(i) - '0'; //把当前字符加入到组成第一个数字的字符集中
                if(t > 10000000000L)    //如果t大于10^10那么后面最多还有9位数，所以不可能组成递减的连续值
                    return false;
                if (dfs(s, t, i + 1))   //把t当作第一个数字，找寻后面递减的数
                    return true;
            }
            return false;
        }
        //s要分割的字符串；pre前面一个数的值；k当前字符串已经用到了哪个位置
        private boolean dfs(String s, long pre, int k) {
            if (k == s.length())    //代表能组成递减的连续值
                return true;
            long t = 0;     //枚举pre后面的一个数字的值
            for (int i = k; i < s.length(); i++) {  //从第k个字符开始组成数字
                t = t * 10 + s.charAt(i) -'0';
                if(t > 10000000000L)
                    return false;
                if (pre - 1 == t && dfs(s, t, i + 1))   //如果前面一个数字和当前数组相差为1，则继续往下面寻找满足条件的数组
                    return true;
                if (t >= pre)   //当前组成的数大于前面的数表示不符合要求，直接返回false
                    return false;
            }
            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}