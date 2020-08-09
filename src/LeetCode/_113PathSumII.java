package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/8/9 17:20
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */
public class _113PathSumII {
    List<List<Integer>> pathList = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) {
            return pathList;
        }

        findTargetPath(root, new LinkedList<>(), sum);
        return pathList;
    }

    /**
     * dfs + 回溯
     */
    public void findTargetPath(TreeNode root, LinkedList<Integer> path, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // 叶子节点时，只对满足条件的做加到path操作，否则不加入
            if (sum == root.val) {
                path.add(root.val);
                pathList.add(new ArrayList<>(path));
                path.removeLast();
            }
            return;
        }
        path.add(root.val);
        findTargetPath(root.left, path, sum - root.val);
        findTargetPath(root.right, path, sum - root.val);
        path.removeLast();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        TreeNode leftLeft = new TreeNode(11);
        TreeNode leftRight = new TreeNode(13);
        TreeNode rightLeft = new TreeNode(4);
        TreeNode rightRight = new TreeNode(7);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;
        rightLeft.left = node1;
        rightLeft.right = node2;
        leftLeft.left = node3;
        _113PathSumII t = new _113PathSumII();
        List<List<Integer>> lists = t.pathSum(root, 22);
        System.out.println(lists);
    }

}
