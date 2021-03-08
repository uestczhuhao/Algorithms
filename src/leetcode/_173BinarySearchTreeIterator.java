package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2020/10/26 22:25
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class _173BinarySearchTreeIterator {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(15);
        TreeNode right1 = new TreeNode(9);
        TreeNode right2 = new TreeNode(20);
        root.left = left;
        root.right = right;
        right.left = right1;
        right.right = right2;
        BSTIterator bstIterator = new BSTIterator(root);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }

    /**
     * 受控递归，在next调用时将其右孩子的左子数加入栈
     */
    private static class BSTIterator {
        // 存放二叉搜索树的左分支的值，用于求最小值
        Deque<TreeNode> stack = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            if (root == null) {
                return;
            }
            addNodeAndAllLeftChild(root);
        }

        private void addNodeAndAllLeftChild(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * 可控制的递归，平均复杂度为O(1)
         * 控制每次都只存右节点的左子树，保证时间复杂度不超过O(h)
         * @return the next smallest number
         */
        public int next() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Tree is empty");
            }

            TreeNode nextNode = stack.pop();
            // 不是每一次操作都需要循环的，循环的次数加上初始化的循环总共会有O(n)次操作
            if (nextNode.right != null) {
                addNodeAndAllLeftChild(nextNode.right);
            }
            return nextNode.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
