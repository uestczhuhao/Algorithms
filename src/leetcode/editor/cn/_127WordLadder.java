package leetcode.editor.cn;

//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
//
//
// 序列中第一个单词是 beginWord 。
// 序列中最后一个单词是 endWord 。
// 每次转换只能改变一个字母。
// 转换过程中的中间单词必须是字典 wordList 中的单词。
//
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。
//
//
// 示例 1：
//
//
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
//
//
// 示例 2：
//
//
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。
//
//
//
// 提示：
//
//
// 1 <= beginWord.length <= 10
// endWord.length == beginWord.length
// 1 <= wordList.length <= 5000
// wordList[i].length == beginWord.length
// beginWord、endWord 和 wordList[i] 由小写英文字母组成
// beginWord != endWord
// wordList 中的所有字符串 互不相同
//
// Related Topics 广度优先搜索
// 👍 697 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _127WordLadder {
    public static void main(String[] args) {
        Solution t = new _127WordLadder().new Solution();
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));

        System.out.println(t.ladderLength(beginWord, endWord, wordList));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：画图 + bfs
         * 对当前字符的每个位置，在 a-z 中尝试替换之，若替换后的字符串在wordList中，则加到下一层遍历中
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || !wordList.contains(endWord)) {
                return 0;
            }

            if (beginWord.equals(endWord)) {
                return 1;
            }

            Set<String> wordSet = new HashSet<>(wordList);
            wordSet.remove(beginWord);
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);

            int level = 1;
            while (!queue.isEmpty()) {
                // 对每一层做bfs，即对每一层符合要求的字符串尝试进行下一步
                int curSize = queue.size();
                for (int i = 0; i < curSize; i++) {
                    String curStr = queue.poll();
                    char[] chars = curStr.toCharArray();
                    for (int index = 0; index < chars.length; index++) {
                        char origin = chars[index];
                        for (char k = 'a'; k <= 'z'; k++) {
                            // 跳过原有的字符串curStr
                            if (origin == k) {
                                continue;
                            }

                            chars[index] = k;
                            String replace = new String(chars);


                            if (wordSet.contains(replace) && !visited.contains(replace)) {
                                if (replace.equals(endWord)) {
                                    return level + 1;
                                }

                                queue.add(replace);
                                visited.add(replace);
                            }

                        }
                        // 当前位置替换为原来字符，后续继续进行替换
                        chars[index] = origin;
                    }
                }
                // bfs每一层完成后step + 1
                level++;
            }


            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
