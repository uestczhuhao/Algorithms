package LeetCode;

public class _28ImplementStr {
    public static void main(String[] args) {
        String s = "aa";
        String ss = "bba";
        System.out.println(strStr(s,ss));
    }
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()){
            return 0;
        }
        char[] sources = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int i=0;
        while (i<sources.length){
            // 先找到第一个
            if (sources[i] == target[0]){
                int j = 1;
                for (; j<target.length; j++){
                    // 保证指针不越界
                    /**
                     * 原写法： if (i+j >= sources.length || sources[i+j] != target[j])  break;
                     * 原写法 180+ms，后10%
                     * 新写法：1ms，前1%
                     * 理由是当source剩余的长度小于target时，若还未找到，则不可能再找到了
                     */
                    if (i+j >= sources.length){
                        return -1;
                    }
                    if (sources[i+j] != target[j]){
                        break;
                    }
                }
                if (j == target.length){
                    return i ;
                }
            }
            i++;
        }

        return -1;
    }
}
