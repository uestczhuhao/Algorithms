package leetcode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-7-3 下午4:23.
 * Description:
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class _108ConvertSortedArray2BinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return buildBST(nums, 0, nums.length - 1);
    }

    public TreeNode buildBST(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + ((high - low) >> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, low, mid - 1);
        node.right = buildBST(nums, mid + 1, high);
        return node;
    }

    public static void main(String[] args) {
        _108ConvertSortedArray2BinarySearchTree t= new _108ConvertSortedArray2BinarySearchTree();
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = t.sortedArrayToBST(nums);
        System.out.println(root);
    }
}
