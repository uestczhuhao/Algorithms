package leetcode.editor.cn;

public class _427ConstructQuadTree {
    public static void main(String[] args) {
        Solution t = new _427ConstructQuadTree().new Solution();
        int[][] grid =
            {{1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}};
        int[][] g1 = {{0, 1}, {1, 0}};
        int[][] g2 = {{1, 1, 0, 0}, {0, 0, 1, 1}, {1, 1, 0, 0}, {0, 0, 1, 1}};
//        System.out.println(t.construct(grid));
        System.out.println(t.construct(g2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    // Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    class Solution {
        /**
         * 思路：递归构建每个子数组（每个子节点），在子节点能够合并时合并
         * 合并条件：子节点都是叶子节点，且四个节点的值相同（都为0或都为1）
         * 优化：可以用二维前缀和来优化（参考前缀和），避免全是0和全是1的情况继续递归
         */
        public Node construct(int[][] grid) {
            int n = grid.length;
            return doConstruct(grid, 0, n - 1, 0, n - 1);
        }

        public Node doConstruct(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
            Node node = new Node();
            if (rowStart == rowEnd) {
                node.isLeaf = true;
                node.val = grid[rowStart][colStart] == 1;
                return node;
            }
            int rowMid = (rowStart + rowEnd) >> 1;
            int colMid = (colStart + colEnd) >> 1;

            node.topLeft = doConstruct(grid, rowStart, rowMid, colStart, colMid);
            node.topRight = doConstruct(grid, rowStart, rowMid, colMid + 1, colEnd);
            node.bottomLeft = doConstruct(grid, rowMid + 1, rowEnd, colStart, colMid);
            node.bottomRight = doConstruct(grid, rowMid + 1, rowEnd, colMid + 1, colEnd);
            if (node.topLeft.val == node.topRight.val &&
                node.topLeft.val == node.bottomLeft.val &&
                node.topLeft.val == node.bottomRight.val &&
                (node.topLeft.isLeaf & node.topRight.isLeaf & node.bottomLeft.isLeaf & node.bottomRight.isLeaf)) {
                node.isLeaf = true;
                node.val = node.topLeft.val;
                node.topLeft = null;
                node.topRight = null;
                node.bottomLeft = null;
                node.bottomRight = null;
            } else {
                node.isLeaf = false;
            }
            return node;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
