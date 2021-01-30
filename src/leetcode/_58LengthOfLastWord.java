package leetcode;

/**
 * @author mizhu
 * @date 20-5-31 下午5:05
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 *  
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 */
public class _58LengthOfLastWord {

    public static void main(String[] args) {
        _58LengthOfLastWord t = new _58LengthOfLastWord();
        System.out.println(t.lengthOfLastWord("hello world"));
    }

    public int lengthOfLastWord(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }

        int endIndex = s.length()-1;
        while (endIndex >=0 && s.charAt(endIndex) == ' ') {
            endIndex --;
        }

        int index = endIndex;
        int len = 0;
        while (index >= 0) {
            if (s.charAt(index) == ' ') {
                break;
            }
            len ++;
            index --;
        }

        return len;
    }
}
