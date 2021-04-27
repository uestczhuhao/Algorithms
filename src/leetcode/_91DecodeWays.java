package leetcode;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/3/10 20:39
 * <p>
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class _91DecodeWays {
    public static void main(String[] args) {
        String s = "1";
        _91DecodeWays t = new _91DecodeWays();
        System.out.println(t.numDecodings1(s));
    }

    /**
     * 动态规划，当遍历到第k个字符时，有以下三种情况
     * 1 第k个字符在1-9之间，则可以用A-I编码
     * 2 第k-1个字符为1，则第k-1和第k个字符可以组合编码，即11-19（J-S）
     * 3 第k-2个字符为2，则第k-1和第k个字符可以组合编码，即20-26（T-Z）
     * <p>
     * 参考斐波那契数列，找出递推公式，求解即可
     */
    public int numDecodings(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] numDecodingArr = new int[len + 1];
        // 没有字符也算是一种可能，用于哨兵
        numDecodingArr[0] = 1;
        char firstChar = s.charAt(0);
        if (firstChar >= '1' && firstChar <= '9') {
            numDecodingArr[1] = 1;
        } else {
            numDecodingArr[1] = 0;
        }
        // 从第二个字符以后开始统一处理
        for (int i = 2; i <= s.length(); i++) {
            char ithChar = s.charAt(i - 1);
            if (ithChar >= '0' && ithChar <= '9') {
                if (ithChar != '0') {
                    numDecodingArr[i] = numDecodingArr[i - 1];
                }
                char beforeIthChar = s.charAt(i - 2);
                if (beforeIthChar == '1') {
                    numDecodingArr[i] += numDecodingArr[i - 2];
                }
                if (beforeIthChar == '2' && ithChar <= '6') {
                    numDecodingArr[i] += numDecodingArr[i - 2];
                }
            }

        }

        return numDecodingArr[len];
    }

    /**
     * 空间复杂度降为O(1)
     */
    public int numDecodings1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int previous = 0;
        // 没有字符也算是一种可能，用于哨兵
        int prePrevious = 1;
        char firstChar = s.charAt(0);
        if (firstChar >= '1' && firstChar <= '9') {
            previous = 1;
        }

        int result = previous;
        // 从第二个字符以后开始统一处理
        for (int i = 2; i <= s.length(); i++) {
            result = 0;
            char ithChar = s.charAt(i - 1);
            if (ithChar >= '0' && ithChar <= '9') {
                if (ithChar != '0') {
                    result += previous;
                }
                char beforeIthChar = s.charAt(i - 2);
                boolean mergeCoding = beforeIthChar == '1' || (beforeIthChar == '2' && ithChar <= '6');
                if (mergeCoding) {
                    result += prePrevious;
                }
            }
            prePrevious = previous;
            previous = result;
        }

        return result;
    }
}
