package LeetCode;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/7/5 14:35
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class _105ConstructBinaryTree {

    Map<Integer, Integer> inIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null
                || inorder == null
                || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }

        for (int i = 0; i < preorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return doBuildTree(preorder, 0, 0, preorder.length - 1);
    }

    public TreeNode doBuildTree(int[] preOrder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preStart]);
        int minIndex = inIndexMap.get(preOrder[preStart]);
        int leftSubTreeNum = minIndex - inStart;
        root.left = doBuildTree(preOrder, preStart + 1, inStart, minIndex - 1);
        root.right = doBuildTree(preOrder, preStart + leftSubTreeNum + 1, minIndex + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
//        int[] pre = {4,1,2,3};
        int[] pre = {3, 9, 20, 15, 7};
//        int[] in = {1,2,3,4};
        int[] in = {9, 3, 15, 20, 7};
        _105ConstructBinaryTree t = new _105ConstructBinaryTree();
        TreeNode node = t.buildTree(pre, in);
        System.out.println(node);
    }
}
