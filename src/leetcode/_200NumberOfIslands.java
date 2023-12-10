package leetcode;

/**
 * @author mizhu
 * @date 2021/2/1 10:35
 */
public class _200NumberOfIslands {
    public static void main(String[] args) {
        _200NumberOfIslands solution = new _200NumberOfIslands();
//        char[][] grid = {
//            {'1', '1', '1', '1', '1'},
//            {'1', '0', '1', '0', '1'},
//            {'1', '1', '1', '0', '1'}
////            {'0', '0', '0', '0', '0'}
//        };

        char[][] grid = {
            {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
            {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
            {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
            {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
            {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
            {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
            {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
            {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
            {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
        };
        char[][] grid1 = {
            {'1', '1', '1'},
            {'0', '1', '0'},
            {'1', '1', '1'}
        };
        int islands = solution.numIslands(grid);
//        int islands = test.numIslands(grid);
        System.out.println(islands);
    }


    /**
     * 岛屿问题的通用模板
     * https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/?envType=study-plan-v2&envId=top-100-liked
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }

    int count = 0;
    int[] rank;
    int[] parent;

    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;
        rank = new int[row * column];
        // 联通域的根结点
        parent = new int[row * column];
        // 初始化rank和root
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int pos = i * column + j;
                if (grid[i][j] != '1') {
                    parent[pos] = -1;
                    continue;
                }
                parent[pos] = pos;
                count++;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int pos = i * column + j;
                if (grid[i][j] != '1') {
                    continue;
                }

                // 标记已访问过的位置，避免重复合并
                grid[i][j] = '0';
                if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                    union(pos, (i - 1) * column + j);
                }
                if (i + 1 < row && grid[i + 1][j] == '1') {
                    union(pos, (i + 1) * column + j);
                }
                if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                    union(pos, i * column + j - 1);
                }
                if (j + 1 < column && grid[i][j + 1] == '1') {
                    union(pos, i * column + j + 1);
                }
            }
        }

        return count;
    }

    /**
     * 合并时更新秩
     * 注意只更新root的秩即可，一个并查集统一用它的根节点秩处理
     * 否则cover不全
     *
     * @param src
     * @param tgt
     */
    private void union(int src, int tgt) {
        int sRoot = findParent(src);
        int tRoot = findParent(tgt);
        if (sRoot == tRoot) {
            return;
        }
        if (rank[sRoot] > rank[tRoot]) {
            parent[tRoot] = sRoot;
        } else if (rank[sRoot] < rank[tRoot]) {
            parent[sRoot] = tRoot;
        } else {
            parent[tRoot] = sRoot;
            rank[sRoot]++;
        }
        count--;
    }

    private int findParent(int num) {
        if (parent[num] != num) {
            parent[num] = findParent(parent[num]);
        }
        return parent[num];
    }
}

