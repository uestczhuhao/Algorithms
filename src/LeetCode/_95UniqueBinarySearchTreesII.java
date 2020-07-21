package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/7/21 22:39
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 */
public class _95UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode current = new TreeNode(i);
                    current.left = left;
                    current.right = right;
                    trees.add(current);
                }
            }

        }
        return trees;
    }

    public static void main(String[] args) {
        _95UniqueBinarySearchTreesII t = new _95UniqueBinarySearchTreesII();
        List<TreeNode> nodes = t.generateTrees(3);
        System.out.println(nodes);
    }
}
