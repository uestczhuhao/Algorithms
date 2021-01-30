package leetcode;

/**
 * @author mizhu
 * @date 2020/7/2 22:59
 *
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 *
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 *
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 *
 *
 * 示例2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 *
 *
 *
 * 示例3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 *
 *
 *
 * 说明:
 *
 * 给定的二叉树最多有 100 个节点。
 * 每个节点的值只会为 0 或 1 。
 *
 */
public class _814BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        doPrune(root);
        return root;
    }

    public boolean doPrune(TreeNode node) {
        if (node == null) {
            return false;
        }

        boolean leftHas1 = doPrune(node.left);
        boolean rightHas1 = doPrune(node.right);
        if (!leftHas1) {
            node.left = null;
        }

        if (!rightHas1) {
            node.right = null;
        }

        return node.val == 1 || leftHas1 || rightHas1;
    }

    public static void main(String[] args) {
        _814BinaryTreePruning t = new _814BinaryTreePruning();
        TreeNode root = new TreeNode(0);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(0);
        root.left = root1;
        root.right = root2;
        System.out.println(t.pruneTree(root));
    }
}
