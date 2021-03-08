package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/8/16 11:50
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class _145BinaryTreePostorderTraversal {
    List<Integer> traversalResult = new LinkedList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return traversalResult;
        }

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        traversalResult.add(root.val);

        return traversalResult;
    }

    private class Solution {
        /**
         * 从后序遍历的定义出发：左右中，当前节点在其左右子树都被访问过之后，才会被访问
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Deque<TreeNode> stack = new LinkedList<>();
            List<Integer> resultValues = new LinkedList<>();
            stack.push(root);
            TreeNode previousNode = root;
            while (!stack.isEmpty()) {
                TreeNode currentNode = stack.peek();
                boolean emptyChild = currentNode.left == null && currentNode.right == null;
                boolean visitedAfterChild = previousNode == currentNode.left || previousNode == currentNode.right;
                // 1 当前节点没有子节点，直接被访问
                // 2 由于入栈顺序的保证，当前节点的左右子树都先弹出
                //   因此若前一个访问的是子节点（左或右），即可认为子节点均访问过了
                if (emptyChild || visitedAfterChild) {
                    resultValues.add(stack.pop().val);
                    previousNode = currentNode;
                } else {
                    if (currentNode.right != null) {
                        stack.push(currentNode.right);
                    }

                    if (currentNode.left != null) {
                        stack.push(currentNode.left);
                    }
                }
            }

            return resultValues;
        }

        /**
         * 参考前序遍历的 中左右，后续为 左右中
         * 因此将前序倒转，为 右左中，再将前序的 左右 倒转，即为结果
         */
        public List<Integer> postOrderTraversal(TreeNode root) {
            LinkedList<Integer> ans = new LinkedList<>();
            if(root == null) {
                return ans;
            }

            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curNode = stack.pop();
                ans.addFirst(curNode.val);
                if (curNode.left != null) {
                    stack.push(curNode.left);
                }

                if (curNode.right != null) {
                    stack.push(curNode.right);
                }
            }

            return ans;
        }
    }


}
