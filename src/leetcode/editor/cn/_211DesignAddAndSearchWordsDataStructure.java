package leetcode.editor.cn;

public class _211DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
//        Solution t = new _211DesignAddAndSearchWordsDataStructure().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class WordDictionary {
        Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        public void addWord(String word) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            node.end = true;
        }

        public boolean search(String word) {
            return doSearch(word, 0, root);
        }

        public boolean doSearch1(String word, int start, Trie node) {
            for (int i = start; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (node.children[j] != null && doSearch(word, i + 1, node.children[j])) {
                            return true;
                        }
                    }
                    // 所有的可能性都试过了，都不满足，那就不可能找得到了，直接返回false
                    return false;
                } else {
                    if (node.children[ch - 'a'] == null) {
                        return false;
                    }
                    node = node.children[ch - 'a'];
                }
            }
            return node.end;
        }

        // 更dfs的方式，不加循环
        public boolean doSearch(String word, int index, Trie node) {
            if (index == word.length()) {
                return node.end;
            }
            char ch = word.charAt(index);
            // 处理当前位置的字符，分2种情况讨论
            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null && doSearch(word, index + 1, node.children[j])) {
                        return true;
                    }
                }
                // 所有的可能性都试过了，都不满足，那就不可能找得到了，直接返回false
                return false;
            } else {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                return doSearch(word, index + 1, node.children[ch - 'a']);
            }
        }

        //字典树实现
        private class Trie {
            // 当前位置是否是word的最后一个字符
            boolean end;

            /**
             * 下一个字符的可能性，对于小写字母，共有26种可能性
             * 隐含的信息：当前字符的值，即 Character currentCh; 转而用children的下标代替
             * 如当前字符为a，则Trie数组的第0项有值（Trie对象）
             * 这么做的好处：Trie一个对象可以表示当前字符有可能的26个值，而不需要创建26个对象
             */

            Trie[] children = new Trie[26];

            public Trie() {
            }

        }
    }


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
