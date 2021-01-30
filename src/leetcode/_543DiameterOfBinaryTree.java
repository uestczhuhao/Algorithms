package leetcode;

/**
 * @author mizhu
 * @date 2020/7/11 22:15
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *  
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */
public class _543DiameterOfBinaryTree {
    int maxLength = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        computeHeight(root);
        return maxLength;
    }

    /**
     * 树高的定义：
     * 1. 叶子节点高度为1
     * 2. 任意节点的高度为其左右高度的更高值 +1
     *
     * 则根据定义，以某个节点node为根的路径长度为
     * 左子树的高度leftHeight + 右子树的高度rightHeight
     */
    private int computeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = computeHeight(root.left);
        int rightHeight = computeHeight(root.right);
        int length = leftHeight + rightHeight;
        if (length > maxLength) {
            maxLength = length;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root1=new TreeNode(1);
        TreeNode root2=new TreeNode(2);
        TreeNode root3=new TreeNode(3);
        TreeNode root4=new TreeNode(4);
        TreeNode root5=new TreeNode(5);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        _543DiameterOfBinaryTree t = new _543DiameterOfBinaryTree();
        System.out.println(t.diameterOfBinaryTree(root1));
    }
}
