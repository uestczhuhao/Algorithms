package leetcode.editor.cn;

//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。 
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。 
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。 
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。 
//
// 说明: 
//
// 
// 单词是指由非空格字符组成的字符序列。 
// 每个单词的长度大于 0，小于等于 maxWidth。 
// 输入单词数组 words 至少包含一个单词。 
// 
//
// 示例: 
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
// 
//
// 示例 2: 
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。       
//     第二行同样为左对齐，这是因为这行只包含一个单词。
// 
//
// 示例 3: 
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to","explain
//",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
// 
// Related Topics 字符串 
// 👍 122 👎 0


import java.util.LinkedList;
import java.util.List;

public class _68TextJustification {
    public static void main(String[] args) {
        Solution t = new _68TextJustification().new Solution();
        String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        System.out.println(t.fullJustify(words, 20));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：顺着题目思路
         * 1 假设当前行放置了m个单词，放第 m+1 时长度大于maxWidth，则不能放m+1个，注意每两个单词中间都有空格
         * 2 注意有多余的空格时，尽量往左边放
         * 3 最后一行要尽量靠左
         */
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> answer = new LinkedList<>();
            if (words == null || words.length == 0) {
                return answer;
            }

            int len = words.length;
            int index = 0;
            // 每次循环处理一行
            while (index < len) {
                // 记录当前行的最左单词下标
                int left = index;
                // 记录当前行空格的个数，其个数比行中单词数少1
                int spaceCount = 0;
                int curRowWordLen = words[index++].length();

                // 找到当前行最后一个元素，其下标为index - 1
                while (index < len && curRowWordLen + spaceCount < maxWidth) {
                    // 此处判断再加一个单词时是否越界，注意spaceCount也要+1
                    if (curRowWordLen + words[index].length() + spaceCount + 1 > maxWidth) {
                        break;
                    }
                    curRowWordLen += words[index++].length();
                    spaceCount++;
                }

                StringBuilder rowWords = new StringBuilder();
                // 处理最后一行
                if (index == len) {
                    for (int i = left; i < index - 1; i++) {
                        rowWords.append(words[i]).append(" ");
                    }
                } else { // 处理其他情况，即左边的空格数大于右边的情况
                    // 空格的个数，接下来需要把n个空格放到m个位置去，且左边大于右边
                    // 则每个位置放 n/m 个，剩余的 n%m 从左往右放即可
                    int spaceNum = maxWidth - curRowWordLen;
                    int aveSpace = 0;
                    int remainder = 0;
                    if (spaceCount != 0) {
                        aveSpace = spaceNum / spaceCount;
                        remainder = spaceNum % spaceCount;
                    }
                    // 预处理空格
                    StringBuilder spaceTemp = new StringBuilder();
                    for (int i = 0; i < aveSpace; i++) {
                        spaceTemp.append(" ");
                    }
                    for (int i = left; i < index - 1; i++) {
                        rowWords.append(words[i]).append(spaceTemp);
                        if (remainder > 0) {
                            rowWords.append(" ");
                            remainder--;
                        }
                    }
                }
                rowWords.append(words[index - 1]);
                // 在右侧补0，当当前行只有一个单词 / 最后一行时需要右侧补空格
                int rightZeroNum = maxWidth - rowWords.length();
                for (int i = 0; i < rightZeroNum; i++) {
                    rowWords.append(" ");
                }
                answer.add(rowWords.toString());
            }
            return answer;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}