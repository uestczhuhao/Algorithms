package leetcode.editor.cn;

//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。 
//
// 示例 1: 
//
// 
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 
// 👍 308 👎 0


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class _451SortCharactersByFrequency {
    public static void main(String[] args) {
        Solution t = new _451SortCharactersByFrequency().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String frequencySort(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            Map<Character, Integer> charFreMap = new HashMap<>();
            PriorityQueue<CharFreq> queue = new PriorityQueue<>((a,b) -> b.freq - a.freq);
            for (int i=0;i<s.length();i++) {
                charFreMap.put(s.charAt(i), charFreMap.getOrDefault(s.charAt(i), 0) + 1);
            }

            for (Map.Entry<Character, Integer> entry : charFreMap.entrySet()) {
                queue.offer(new CharFreq(entry.getKey(), entry.getValue()));
            }

            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                CharFreq charFreq = queue.poll();
                for (int i = charFreq.freq; i>0;i--) {
                    sb.append(charFreq.ch);
                }
            }

            return sb.toString();
        }

        private class CharFreq {
            char ch;
            int freq;

            public CharFreq(char ch, int freq) {
                this.ch = ch;
                this.freq = freq;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}