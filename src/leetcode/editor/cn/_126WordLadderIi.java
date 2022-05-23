package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * <p>按字典&nbsp;<code>wordList</code> 完成从单词 <code>beginWord</code> 到单词 <code>endWord</code> 转化，一个表示此过程的 <strong>转换序列</strong> 是形式上像 <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> 这样的单词序列，并满足：</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li>每对相邻的单词之间仅有单个字母不同。</li>
 * <li>转换过程中的每个单词 <code>s<sub>i</sub></code>（<code>1 &lt;= i &lt;= k</code>）必须是字典&nbsp;<code>wordList</code> 中的单词。注意，<code>beginWord</code> 不必是字典 <code>wordList</code> 中的单词。</li>
 * <li><code>s<sub>k</sub> == endWord</code></li>
 * </ul>
 *
 * <p>给你两个单词 <code>beginWord</code> 和 <code>endWord</code> ，以及一个字典 <code>wordList</code> 。请你找出并返回所有从 <code>beginWord</code> 到 <code>endWord</code> 的 <strong>最短转换序列</strong> ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表<em> </em><code>[beginWord, s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub>]</code> 的形式返回。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * <strong>输出：</strong>[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * <strong>解释：</strong>存在 2 种最短的转换序列：
 * "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog"
 * "hit" -&gt; "hot" -&gt; "lot" -&gt; "log" -&gt; "cog"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * <strong>输出：</strong>[]
 * <strong>解释：</strong>endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= beginWord.length &lt;= 5</code></li>
 * <li><code>endWord.length == beginWord.length</code></li>
 * <li><code>1 &lt;= wordList.length &lt;= 5000</code></li>
 * <li><code>wordList[i].length == beginWord.length</code></li>
 * <li><code>beginWord</code>、<code>endWord</code> 和 <code>wordList[i]</code> 由小写英文字母组成</li>
 * <li><code>beginWord != endWord</code></li>
 * <li><code>wordList</code> 中的所有单词 <strong>互不相同</strong></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>哈希表</li><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 572</li><li>👎 0</li></div>
 */

public class _126WordLadderIi {
    public static void main(String[] args) {
        Solution t = new _126WordLadderIi().new Solution();
        List<String> wl = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(t.findLadders("hit", "cog", wl));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> ans;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            ans = new ArrayList<>();
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) {
                return ans;
            }
            wordSet.remove(beginWord);


            // 记录word和该word在路径中出现的层数
            Map<String, Integer> steps = new HashMap<>();
            steps.put(beginWord, 0);
            // 记录word是从哪些words转换而来的
            Map<String, Set<String>> from = new HashMap<>();
            boolean find = bfs(beginWord, endWord, steps, from, wordSet);
            if (find) {
                Deque<String> path = new LinkedList<>();
                path.add(endWord);
                dfs(from, path, beginWord, endWord);
            }
            return ans;
        }

        /**
         * bfs判断是否有最短路径，同时填充from数组（注意，在最优解存在的情况下from数组是全的）
         */
        private boolean bfs(String beginWord, String endWord, Map<String, Integer> steps, Map<String, Set<String>> from, Set<String> wordSet) {
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            int step = 0;
            boolean find = false;
            while (!queue.isEmpty()) {
                step++;
                int size = queue.size();
                // bfs寻找是否有解
                for (int i = 0; i < size; i++) {
                    String curWord = queue.poll();
                    char[] curChs = curWord.toCharArray();
                    for (int j = 0; j < curWord.length(); j++) {
                        char originCh = curChs[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == originCh) {
                                continue;
                            }
                            curChs[j] = c;
                            String nextWord = new String(curChs);
                            if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                                from.get(nextWord).add(curWord);
                            }
                            if (!wordSet.contains(nextWord)) {
                                continue;
                            }
                            if (nextWord.equals(endWord)) {
                                find = true;
                            }
                            wordSet.remove(nextWord);
                            queue.offer(nextWord);
                            // 记录 nextWord 从 curWord 而来
                            from.putIfAbsent(nextWord, new HashSet<>());
                            from.get(nextWord).add(curWord);
                            steps.put(nextWord, step);

                        }
                        curChs[j] = originCh;
                    }
                }
                if (find) {
                    break;
                }
            }
            return find;
        }

        /**
         * 从后往前寻找路径，采用dfs回溯
         */
        private void dfs(Map<String, Set<String>> from, Deque<String> path, String beginWord, String endWord) {
            if (endWord.equals(beginWord)) {
                ans.add(new ArrayList<>(path));
                return;
            }

            for (String preWord : from.get(endWord)) {
                path.addFirst(preWord);
                dfs(from, path, beginWord, preWord);
                path.removeFirst();
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
