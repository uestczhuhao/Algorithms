package LeetCode;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */
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
