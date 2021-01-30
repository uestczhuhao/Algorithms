package leetcode;

/**
 * @author mizhu
 * @date 2020/7/21 23:58
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class _95UniqueBinarySearchTrees {
    /**
     * https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     */
    public int numTrees(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] treeNums = new int[n + 1];
        treeNums[0] = treeNums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                treeNums[i] += treeNums[j] * treeNums[i - j - 1];
            }
        }

        return treeNums[n];
    }

    public static void main(String[] args) {
        _95UniqueBinarySearchTrees t = new _95UniqueBinarySearchTrees();
        System.out.println(t.numTrees(10));
    }
}
