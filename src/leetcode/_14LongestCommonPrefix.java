package leetcode;

public class _14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }

        String minLenStr = strs[0];
        int i;
        for (i=1; i<strs.length; i++){
            if (strs[i].length() < minLenStr.length())
                minLenStr = strs[i];
        }

        char[] chs = minLenStr.toCharArray();

        for (i=0; i<chs.length;i++){
            for (String str : strs) {
                if (chs[i] != str.charAt(i)) {
                    return minLenStr.substring(0, i);
                }
            }
        }

        return minLenStr;
    }

    public static void main(String[] args) {
        _14LongestCommonPrefix t = new _14LongestCommonPrefix();
        System.out.println(t.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(t.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(t.longestCommonPrefix(new String[]{"dog","doggg","dogca"}));
    }
}
