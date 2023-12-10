package leetcode.editor.cn;

public class _463IslandPerimeter {
    public static void main(String[] args) {
        Solution t = new _463IslandPerimeter().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int islandPerimeter(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // 在陆地处开始计算，且只算一次即可
                    if (grid[i][j] == 1) {
                        return countPer(grid, i, j);
                    }
                }
            }
            return 0;
        }

        public int countPer(int[][] grid, int i, int j) {
            // 越界，贡献一条边
            if (!inArea(grid, i, j)) {
                return 1;
            }
            // 海水，贡献一条边
            if (grid[i][j] == 0) {
                return 1;
            }
            // 已经访问过，直接跳过，不贡献边
            if (grid[i][j] == 2) {
                return 0;
            }
            // 当前位置一定为1，迭代上下左右，并把当前位置置为已访问（=2表示已访问）
            grid[i][j] = 2;
            return countPer(grid, i - 1, j) + countPer(grid, i + 1, j)
                + countPer(grid, i, j - 1) + countPer(grid, i, j + 1);
        }

        public boolean inArea(int[][] grid, int i, int j) {
            return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
