package leetcode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-7-3 下午4:46.
 * Description:
 * <p>
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 1
 * / \
 * 0   2
 * <p>
 * L = 1
 * R = 2
 * <p>
 * 输出:
 * 1
 * \
 * 2
 * 示例 2:
 * <p>
 * 输入:
 * 3
 * / \
 * 0   4
 * \
 * 2
 * /
 * 1
 * <p>
 * L = 1
 * R = 3
 * <p>
 * 输出:
 * 3
 * /
 * 2
 * /
 * 1
 */
public class _669TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (L > root.val) {
            return trimBST(root.right, L, R);
        }

        if (R < root.val) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    /**
     * 容易理解的版本
     */
    public TreeNode trimBST1(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        
        // 如果root已经太大，它的右子树会更大，全部裁掉
        if (root.val > R) {
            root = trimBST1(root.left, L, R);
        } else if (root.val < L) {
            // 反之，裁掉所有的左子树
            root = trimBST1(root.right, L, R);
        } else {
            // root节点在L和R之间，则尝试裁剪其左右子树
            root.left = trimBST1(root.left, L, R);
            root.right = trimBST1(root.right, L, R);
        }

        return root;
    }

}
