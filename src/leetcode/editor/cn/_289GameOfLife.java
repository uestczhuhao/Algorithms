package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _289GameOfLife {
    public static void main(String[] args) {
        Solution t = new _289GameOfLife().new Solution();
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
        t.gameOfLife(board);
        System.out.println();
        System.out.println();
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1. 把map改成另一个数组，能加快速度
         * 2. 用一个邻居数组int neighbors[3] = {0, 1, -1}; 对齐做全遍历，即可遍历九宫格
         * 进一步，用另外的状态来存储，可以省去1中另一个数组，只需要存放2种状态，即 0->1(-2) 和 1->0(2)存储即可
         */
        public void gameOfLife(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int liveNum = 0;
                    for (int k1 = -1; k1 <= 1; k1++) {
                        for (int k2 = -1; k2 <= 1; k2++) {
                            if (k1 == 0 && k2 == 0) {
                                continue;
                            }
                            int row = i + k1, col = j + k2;
                            if (row < 0 || row >= m || col < 0 || col >= n) {
                                continue;
                            }
                            liveNum += board[row][col] > 0 ? 1 : 0;
                        }
                    }
                    // 只需要存放2种状态，即 0->1(-2) 和 1->0(2)
                    if (board[i][j] == 1 && (liveNum < 2 || liveNum > 3)) {
                        board[i][j] = 2;
                    } else if (board[i][j] == 0 && liveNum == 3) {
                        board[i][j] = -2;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 2) {
                        board[i][j] = 0;
                    } else if (board[i][j] == -2) {
                        board[i][j] = 1;
                    }
                }
            }
        }

        public void gameOfLife1(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            Map<Integer, Integer> indexLiveMap = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = i * n + j;
                    indexLiveMap.put(index, -board[i][j]);
                    updateMap(board, indexLiveMap, i - 1, j, m, n, index);
                    updateMap(board, indexLiveMap, i, j, m, n, index);
                    updateMap(board, indexLiveMap, i + 1, j, m, n, index);
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = i * n + j;
                    int liveNum = indexLiveMap.get(index);
                    if (liveNum < 2 || liveNum > 3) {
                        board[i][j] = 0;
                    } else if (liveNum == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }

        private void updateMap(int[][] board, Map<Integer, Integer> indexLiveMap, int i, int j, int m, int n, int index) {
            if (i < 0 || i >= m) {
                return;
            }
            for (int k = -1; k <= 1; k++) {
                int col = j + k;
                if (col < 0 || col >= n) {
                    continue;
                }
                indexLiveMap.put(index, indexLiveMap.get(index) + board[i][col]);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
