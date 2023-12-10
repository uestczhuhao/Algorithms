package leetcode.editor.cn;

public class _695MaxAreaOfIsland {
    public static void main(String[] args) {
        Solution t = new _695MaxAreaOfIsland().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int max = 0;

        public int maxAreaOfIsland(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j);
                    }
                }
            }
            return max;
        }

        private int dfs(int[][] grid, int i, int j) {
            // 越界，跳过
            if (!inArea(grid, i, j)) {
                return 0;
            }
            // 已经访问过或是海水，跳过
            if (grid[i][j] != 1) {
                return 0;
            }
            // 当前位置一定为1，迭代上下左右，并把当前位置置为已访问（=2表示已访问）
            grid[i][j] = 2;
            int curArea = 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
            max = Math.max(max, curArea);
            return curArea;
        }

        private boolean inArea(int[][] grid, int i, int j) {
            return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
