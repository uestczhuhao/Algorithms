package leetcode;

import java.util.Arrays;

public class _10RegularExpMatch {
    // recursion
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }

        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //dp
    public boolean isMatch1(String text, String pattern) {
        // dp[i][j] means text[i:] match pattern[j:]
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true; // "" match "" true

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean firstMatch = (i < text.length()
                        && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

                /*
                dp[i][j] = dp[i][j+2] means * stands zero of the preceding element.
                firstMatch  means preceding element of text and pattern match,
                then check if text[i+1:] match pattern[j:]
                 */
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];
                } else {
                    // first element match and text[i+1:] pattern[j+1:]
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划，dp[i][j] 代表text的前i个字符与pattern的前j个字符是否匹配
     * 1. p[j] == '*'，必须向前追溯，考虑pattern[j-1]
     * p[j-1] != s[i]时
     * 只能匹配0次，则j和j-1的字符直接忽略，dp[i][j] = dp[i][j-2]
     * <p>
     * p[j-1] == s[i] 或 p[j-1] == '.'时
     * 匹配0次，则j和j-1的字符直接忽略，dp[i][j] = dp[i][j-2]
     * 匹配一次，则dp[i][j] = dp[i][j-1]
     * 匹配两次，则dp[i][j] = dp[i-1][j]，例如pattern[j-1] == 'a'，则text[i] 也必须为a，此时a* = aa*
     * 去掉pattern的一个a，同时text也去掉一个a，则问题变成判定text[i-1]和pattern[j]比较
     * 匹配三次，dp[i][j] = dp[i-2][j]
     * ....
     * <p>
     * 上诉结果去或，即满足一个即可
     * <p>
     * 2. p[j] == '.'，可以代表匹配text[i]处的任意字符
     * 此时dp[i][j] = dp[i-1][j-1]
     * <p>
     * 3. p[j] == s[i]，同情况2，dp[i][j] = dp[i-1][j-1]
     */
    public boolean isMatch2(String s, String p) {
        if (p.length() == 0) {
            return p.equals(s);
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        // 初始化第一行
        for (int j=2;j<=p.length();j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int j = 1; j <= p.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                // 处理为*的情况
                if (j > 1 && p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j] || dp[i][j - 2];
                    }
                } else if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[s.length()][p.length()];

    }

    public static void main(String[] args) {
        _10RegularExpMatch t = new _10RegularExpMatch();

//        System.out.println(t.isMatch2("aa", "a"));
//        System.out.println(t.isMatch2("aa", "a*"));
//        System.out.println(t.isMatch2("aab", "c*a*b*"));
//        System.out.println(t.isMatch1("aab", "c*a*b*"));
        String s ="f";
        String p ="f.*";
        System.out.println(t.isMatch1(s, p));
        System.out.println(t.isMatch2(s, p));
//        System.out.println(t.isMatch1("mississippi",".*"));

    }
}
