package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/7/12 15:17
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class _106ConstructBinaryTreeByInorderAndPostorder {
    Map<Integer, Integer> inorderValueIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
                || inorder.length == 0
                || inorder.length != postorder.length) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            inorderValueIndexMap.put(inorder[i], i);
        }

        return constructTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode constructTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int curRootValue = postorder[postEnd];
        int inorderMidIndex = inorderValueIndexMap.get(curRootValue);
        int leftLength = inorderMidIndex - inStart;
        TreeNode currentRoot = new TreeNode(curRootValue);
        currentRoot.left = constructTree(inorder, postorder, inStart, inStart + leftLength - 1, postStart, postStart + leftLength - 1);
        currentRoot.right = constructTree(inorder, postorder, inorderMidIndex + 1, inEnd, postStart + leftLength, postEnd - 1);
        return currentRoot;
    }

    public static void main(String[] args) {
        int[] in = {9,3,15,20,7};
        int[] post = {9,15,7,20,3};
        _106ConstructBinaryTreeByInorderAndPostorder t = new _106ConstructBinaryTreeByInorderAndPostorder();
        System.out.println(t.buildTree(in, post));
    }
}
