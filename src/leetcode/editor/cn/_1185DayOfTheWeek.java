package leetcode.editor.cn;

//给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
//
// 输入为三个整数：day、month 和 year，分别表示日、月、年。
//
// 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
//"Friday", "Saturday"}。
//
//
//
// 示例 1：
//
// 输入：day = 31, month = 8, year = 2019
//输出："Saturday"
//
//
// 示例 2：
//
// 输入：day = 18, month = 7, year = 1999
//输出："Sunday"
//
//
// 示例 3：
//
// 输入：day = 15, month = 8, year = 1993
//输出："Sunday"
//
//
//
//
// 提示：
//
//
// 给出的日期一定是在 1971 到 2100 年之间的有效日期。
//
// Related Topics 数学 👍 106 👎 0


import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class _1185DayOfTheWeek {
    public static void main(String[] args) {
        Solution t = new _1185DayOfTheWeek().new Solution();
        System.out.println(t.dayOfTheWeek(31, 8, 2019));
        System.out.println(t.dayOfTheWeek(18, 7, 1999));
        System.out.println(t.dayOfTheWeek(15, 8, 1993));
        System.out.println(t.dayOfTheWeek(21, 12, 1980));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 考虑1971 - year-1 之间的闰年
         * 再考虑第 year 年是否为闰年，若是则当年2月为29天，否则为28天
         * 最后，1971.01.01为Friday，计算出当前时间与其差值，再与初始值相加，对7取余即可
         */
        public String dayOfTheWeek(int day, int month, int year) {
            int sumDays = 365 * (year - 1971);
            for (int i = 1971; i < year; i++) {
                if (isLeapYear(i)) {
                    sumDays += 1;
                }
            }

            int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            for (int i = 1; i < month; i++) {
                sumDays += monthDays[i];
                if (i == 2 && isLeapYear(year)) {
                    sumDays += 1;
                }
            }
            sumDays += day - 1;
            int ans = (sumDays % 7 + 5) % 7;
            String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            return weeks[ans];
        }

        private boolean isLeapYear(int year) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return year % 4 == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
