package leetcode.editor.cn;

//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 517 👎 0


public class _208ImplementTriePrefixTree {
    public static void main(String[] args) {
//        Solution solution = new ImplementTriePrefixTree().new Solution();
        Trie trie = new _208ImplementTriePrefixTree().new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
    }

    /**
     * 思路：多节点链表，每个链表为26个节点合成的集合
     * 每个单词在前缀树中的路径相同（或直接不存在）
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        /**
         * 所有小写字母
         */
        final int CAPACITY = 26;
        /**
         * 存放下一个节点，默认this为根
         */
        Trie[] next = new Trie[CAPACITY];

        /**
         * 是否为某个单词的结尾
         */
        boolean isEnd = false;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            Trie node = this;

            for (int i = 0; i < word.length(); i++) {
                char curCh = word.charAt(i);
                if (node.next[curCh - 'a'] == null) {
                    node.next[curCh - 'a'] = new Trie();
                }
                node = node.next[curCh - 'a'];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie tgtNode = doSearch(word);

            return tgtNode != null && tgtNode.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie tgtNode = doSearch(prefix);

            return tgtNode != null;
        }

        private Trie doSearch(String word) {
            if (word == null || word.length() == 0) {
                return null;
            }

            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node.next[word.charAt(i) - 'a'] == null) {
                    return null;
                }
                node = node.next[word.charAt(i) - 'a'];
            }

            return node;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}