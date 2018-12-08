package LeetCode;

import java.util.Arrays;

public class _10RegularExpMatch {
    // recursion
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }

        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) =='.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return isMatch(text,pattern.substring(2)) || (first_match && isMatch(text.substring(1),pattern));
        } else {
            return first_match && isMatch(text.substring(1),pattern.substring(1));
        }
    }

    //dp
    public boolean isMatch1(String text, String pattern) {
        // dp[i][j] means text[i:] match pattern[j:]
        boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];
        dp[text.length()][pattern.length()] = true; // "" match "" true

        for (int i=text.length() ;i>=0;i--){
            for (int j= pattern.length()-1; j >=0; j--){
                boolean first_match = (i<text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) =='.'));

                /*
                dp[i][j] = dp[i][j+2] means * stands zero of the preceding element.
                first_match  means preceding element of text and pattern match,
                then check if text[i+1:] match pattern[j:]
                 */
                if (j+1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    // first element match and text[i+1:] pattern[j+1:]
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        _10RegularExpMatch t = new _10RegularExpMatch();

//        System.out.println(t.isMatch1("aa","a"));
//        System.out.println(t.isMatch1("aa","a*"));
        System.out.println(t.isMatch1("aab","c*a*b*"));
//        System.out.println(t.isMatch1("mississippi",".*"));

    }
}
