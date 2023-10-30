package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _212WordSearchIi {
    public static void main(String[] args) {
        Solution t = new _212WordSearchIi().new Solution();
//        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        char[][] board = {{'a'}, {'a'}};
        String[] words = {"aa"};
        System.out.println(t.findWords(board, words));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<String> ansSet = new HashSet<>();
        int row, col;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /**
         * 题解：https://leetcode.cn/problems/word-search-ii/solutions/1000172/dan-ci-sou-suo-ii-by-leetcode-solution-7494/?envType=study-plan-v2&envId=top-interview-150
         * 字典树+dfs遍历
         * 时间复杂度O(m * n * 4 ^l)，其中m和n为board的长和宽，l位words中的单词最大长度
         * 因为需要遍历board的每一个位置，每个位置最坏要遍历4个方向，每个方向不超过l（本题中为10），因此未4^l（向上放大，不考虑重复路径）
         */
        public List<String> findWords(char[][] board, String[] words) {
            row = board.length;
            col = board[0].length;
            Trie root = new Trie();
            for (String w : words) {
                root.insert(w);
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    dfs(board, root, i, j);
                }
            }
            return new ArrayList<>(ansSet);
        }

        public void dfs(char[][] board, Trie node, int i, int j) {
            // 剪枝，当前的字符串前缀不在字典树中
            if (node.children[board[i][j] - 'a'] == null) {
                return;
            }
            char ch = board[i][j];
            Trie next = node.children[ch - 'a'];
            // 递归基，当找到word之后，直接塞到结果数组中
            if (!next.word.equals("")) {
                ansSet.add(next.word);
                // 剪枝2，找到next.word后，把next.word设置为空串，防止 a aa aaa aaaa类型的示例中，找到大量的重复字符串
                next.word = "";
            }

            // 用赋值的方式标记board[i][j]，避免经过的字符被重复访问
            board[i][j] = '#';
            for (int[] dir : dirs) {
                // 方向越界或被访问过，则直接跳过
                if (i + dir[0] < 0 || i + dir[0] >= row || j + dir[1] < 0 || j + dir[1] >= col
                    || board[i + dir[0]][j + dir[1]] == '#') {
                    continue;
                }
                dfs(board, node.children[ch - 'a'], i + dir[0], j + dir[1]);
            }
            board[i][j] = ch;
        }
    }

    class Trie {
        Trie[] children = new Trie[26];
        // 本来应该是布尔值的end，由于本题中直接要输出答案，因此用word代替
        String word = "";

        public Trie() {
        }

        public void insert(String str) {
            Trie node = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.word = str;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
