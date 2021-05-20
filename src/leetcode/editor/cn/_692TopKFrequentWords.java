package leetcode.editor.cn;

//给一非空的单词列表，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。 
//
// 示例 1： 
//
// 
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 
// 输入的单词均由小写字母组成。 
// 
//
// 
//
// 扩展练习： 
//
// 
// 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// 
// Related Topics 堆 字典树 哈希表 
// 👍 268 👎 0


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _692TopKFrequentWords {
    public static void main(String[] args) {
        Solution t = new _692TopKFrequentWords().new Solution();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 1;
        System.out.println(t.topKFrequent(words, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<String> topKFrequent(String[] words, int k) {
            PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> {
                if (o1.getValue().equals(o2.getValue())) {
                    return o2.getKey().compareTo(o1.getKey());
                } else {
                    return o1.getValue() - o2.getValue();
                }
            });

            Map<String, Integer> wordFreMap = new HashMap<>();
            for (String w : words) {
                wordFreMap.put(w, wordFreMap.getOrDefault(w, 0) + 1);
            }

            for (Map.Entry<String, Integer> fre : wordFreMap.entrySet()) {
                queue.offer(fre);
                while (queue.size() > k) {
                    queue.poll();
                }
            }
            LinkedList<String> ans = new LinkedList<>();
            while (!queue.isEmpty()) {
                Map.Entry<String, Integer> wordFre = queue.poll();
                ans.add(wordFre.getKey());
            }
            Collections.reverse(ans);
            return ans;
        }

        /**
         * 用WordFrePair
         */
        public List<String> topKFrequent1(String[] words, int k) {
            PriorityQueue<WordFrePair> queue = new PriorityQueue<>((pair1, pair2) -> {
                if (pair1.freq.equals(pair2.freq)) {
                    return pair2.word.compareTo(pair1.word);
                } else {
                    return pair1.freq - pair2.freq;
                }
            });

            Arrays.sort(words);
            int count = 1;
            for (int i = 0; i < words.length; i++) {
                if (i == words.length - 1 || !words[i].equals(words[i + 1])) {
                    if (queue.isEmpty() || queue.size() < k || count > queue.peek().freq
                        || (count == queue.peek().freq && words[i].compareTo(queue.peek().word) > 0)) {
                        queue.offer(new WordFrePair(words[i], count));
                        while (queue.size() > k) {
                            queue.poll();
                        }
                    }
                    count = 1;
                } else {
                    count++;
                }
            }
            LinkedList<String> ans = new LinkedList<>();
            while (!queue.isEmpty()) {
                WordFrePair pair = queue.poll();
                ans.add(pair.word);
            }

            Collections.reverse(ans);
            return ans;
        }


        private class WordFrePair {
            private String word;
            private Integer freq;

            public WordFrePair(String word, int freq) {
                this.word = word;
                this.freq = freq;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}