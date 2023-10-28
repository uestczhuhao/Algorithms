package leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _909SnakesAndLadders {
    public static void main(String[] args) {
        Solution t = new _909SnakesAndLadders().new Solution();
        int[][] board = {{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};
        System.out.println(t.snakesAndLadders(board));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将二维的矩阵「扁平化」为一维的矩阵，然后再按照规则进行 BFS
         * 一般情况下，求是否能到达，用DFS；求最短路径，用BFS
         */
        public int snakesAndLadders(int[][] board) {
            int n = board.length;
            int[] nums = new int[n * n + 1];
            boolean right = true;
            int index = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (right) {
                    for (int j = 0; j < n; j++) {
                        nums[index++] = board[i][j];
                    }
                } else {
                    for (int j = n - 1; j >= 0; j--) {
                        nums[index++] = board[i][j];
                    }
                }
                right = !right;
            }
            // bfs算法
            Deque<Integer> deque = new LinkedList<>();
            // 存放到当前的位置的最小步数
            Map<Integer, Integer> numStepMap = new HashMap<>();
            deque.addLast(1);
            numStepMap.put(1, 0);
            while (!deque.isEmpty()) {
                int curNum = deque.pollFirst();
                if (curNum == n * n) {
                    return numStepMap.get(curNum);
                }
                int step = numStepMap.get(curNum);
                for (int i = 1; i <= 6 && curNum + i <= n * n; i++) {
                    int nextPos = curNum + i;
                    int next = nums[nextPos] == -1 ? nextPos : nums[nextPos];
                    if (numStepMap.containsKey(next)) {
                        continue;
                    }
                    numStepMap.put(next, step + 1);
                    deque.offerLast(next);
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
