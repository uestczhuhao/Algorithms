package leetcode;

/**
 * Created by mizhu on 19-6-15 下午4:21
 */
public class _38CountAndSay {

    public static void main(String[] args) {
        String s = "12234";
        System.out.println(build(s));
        System.out.println(countAndSay(4));
    }

    public static String countAndSay(int n) {
        if (n <= 1) {
            return "1";
        }

        String before = countAndSay(n - 1);
        return build(before);
    }

    public static String build(String str) {
        StringBuilder sb = new StringBuilder();

        char[] chs = str.toCharArray();
        for (int i = 0; i < str.length(); ) {
            int dupSum = 0;
            char ch = chs[i];
            while (i < str.length() && chs[i] == ch) {
                dupSum++;
                i++;
            }
            sb.append(dupSum).append(ch);
        }
        return sb.toString();
    }

}
