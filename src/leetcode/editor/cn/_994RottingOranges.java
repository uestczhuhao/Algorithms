package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _994RottingOranges {
    public static void main(String[] args) {
        Solution t = new _994RottingOranges().new Solution();
        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int[][] grid1 = {{0}};
        System.out.println(t.orangesRotting(grid1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 也可以用两个数组来模拟上下左右的位置
         * int[] dr = new int[]{-1, 0, 1, 0};
         * int[] dc = new int[]{0, -1, 0, 1};
         * 每次加上dr和dc的第i个元素，判断操作后的位置是否在数组内
         */

        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int fresh = 0, minutes = 0;
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> start = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) {
                        start.offer(new int[] {i, j});
                    } else if (grid[i][j] == 1) {
                        fresh++;
                    }
                }
            }
            while (!start.isEmpty()) {

                int size = start.size();
                boolean curLoopRot = false;
                for (int i = 0; i < size; i++) {
                    int[] bad = start.poll();
                    int row = bad[0];
                    int col = bad[1];
                    // 上
                    if (row > 0 && grid[row - 1][col] == 1 && !visited[row - 1][col]) {
                        curLoopRot = true;
                        fresh--;
                        start.offer(new int[] {row - 1, col});
                        visited[row - 1][col] = true;
                    }
                    // 下
                    if (row < m - 1 && grid[row + 1][col] == 1 && !visited[row + 1][col]) {
                        curLoopRot = true;
                        fresh--;
                        start.offer(new int[] {row + 1, col});
                        visited[row + 1][col] = true;
                    }
                    // 左
                    if (col > 0 && grid[row][col - 1] == 1 && !visited[row][col - 1]) {
                        curLoopRot = true;
                        fresh--;
                        start.offer(new int[] {row, col - 1});
                        visited[row][col - 1] = true;
                    }
                    // 右
                    if (col < n - 1 && grid[row][col + 1] == 1 && !visited[row][col + 1]) {
                        curLoopRot = true;
                        fresh--;
                        start.offer(new int[] {row, col + 1});
                        visited[row][col + 1] = true;
                    }
                }
                if (curLoopRot) {
                    minutes++;
                }
            }
            return fresh == 0 ? minutes : -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
