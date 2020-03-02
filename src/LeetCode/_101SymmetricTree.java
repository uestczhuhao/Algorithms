package LeetCode;

/**
 * @author mizhu
 * @date 20-3-2 下午9:59
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 说明:
 * <p>
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */
public class _101SymmetricTree {
    public static void main(String[] args) {
        _101SymmetricTree t = new _101SymmetricTree();
        TreeNode root = new TreeNode(1);
        TreeNode fLeft = new TreeNode(2);
        TreeNode fRight = new TreeNode(2);
//        TreeNode sLLeft = new TreeNode(3);
        TreeNode sLRight = new TreeNode(4);
        TreeNode sRLeft = new TreeNode(4);
//        TreeNode sRRight = new TreeNode(3);
        root.left = fLeft;
        root.right = fRight;
//        fLeft.left = sLLeft;
        fLeft.right = sLRight;
        fRight.left = sRLeft;
//        fRight.right = sRRight;
        System.out.println(t.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }

        return recuSymmetric(root.left, root.right);
    }

    public boolean recuSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        boolean leftLegal = recuSymmetric(left.left, right.right);
        boolean rightLegal = recuSymmetric(right.left, left.right);

        return leftLegal && rightLegal;
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}