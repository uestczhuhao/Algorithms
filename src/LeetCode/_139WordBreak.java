package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author mizhu
 * @date 2021/1/28 15:27
 */
public class _139WordBreak {
    /**
     * 动态规划，
     * dp[i]代表前i个字符组成的字符串能否被分割
     * dp[i] = dp[j] && check(j..i)
     * 其中check函数代表分割点处到i的字符串是否包含在字典中
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null) {
            return false;
        }

        int len = s.length();
        HashSet<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    public static void main(String[] args) {
        _139WordBreak t = new _139WordBreak();
        List<String> words = new ArrayList<>();
        words.add("leet");
        words.add("code");
        boolean b = t.wordBreak("leetcode", words);
        System.out.println(b);

    }
}
