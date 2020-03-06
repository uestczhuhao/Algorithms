package LeetCode;

import java.util.LinkedList;
import java.util.Objects;

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
        TreeNode fRight = new TreeNode(3);
        TreeNode sLLeft = new TreeNode(3);
        TreeNode sLRight = new TreeNode(4);
        TreeNode sRLeft = new TreeNode(4);
        TreeNode sRRight = new TreeNode(3);
        root.left = fLeft;
        root.right = fRight;
//        fLeft.left = sLLeft;
//        fLeft.right = sLRight;
//        fRight.left = sRLeft;
//        fRight.right = sRRight;
        System.out.println(t.isSymmetric1(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }

        return recuSymmetric(root.left, root.right);
    }

    public boolean isSymmetric1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        LinkedList<TreeNode> leftValues = new LinkedList<>();
        LinkedList<TreeNode> rightValues = new LinkedList<>();
        TreeNode left = root.left;
        leftValues.add(left);
        TreeNode right = root.right;
        rightValues.add(right);

        while (!leftValues.isEmpty() && !rightValues.isEmpty()) {
            left = leftValues.removeFirst();
            right = rightValues.removeFirst();
            if (left == null && right == null) {
                continue;
            }
            // 左右两边一个是null，一个不是
            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            leftValues.add(left.left);
            leftValues.add(left.right);
            rightValues.add(right.right);
            rightValues.add(right.left);
        }

        return leftValues.size() == rightValues.size();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }
}