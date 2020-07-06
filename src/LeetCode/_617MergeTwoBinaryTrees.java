package LeetCode;

/**
 * @author mizhu
 * @date 2020/7/6 22:06
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 *
 */
public class _617MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        doMerge(t1,t2);
        return t1;
    }

    private TreeNode doMerge(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            t1 = new TreeNode(t2.val);
        } else if (t2 == null) {
            return t1;
        } else {
            t1.val = t1.val + t2.val;
        }

        if (t1.left != null || t2.left != null) {
            t1.left = doMerge(t1.left, t2.left);
        }

        if (t1.right != null || t2.right != null) {
            t1.right = doMerge(t1.right, t2.right);
        }

        return t1;
    }
}
