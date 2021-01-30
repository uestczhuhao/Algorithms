package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/07/14 20:35
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 */
public class _103ZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> allValues = new LinkedList<>();
        Deque<TreeNode> curLevel = new LinkedList<>();
        Deque<TreeNode> nextLevel = new LinkedList<>();
        boolean oddLevel = false;
        curLevel.push(root);
        while (!curLevel.isEmpty()) {
            oddLevel = !oddLevel;
            int size = curLevel.size();
            LinkedList<Integer> curLevelValues = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = curLevel.poll();

                if (node != null) {
                    if (node.left != null) {
                        nextLevel.offer(node.left);
                    }

                    if (node.right != null) {
                        nextLevel.offer(node.right);
                    }

                    if (oddLevel) {
                        curLevelValues.addLast(node.val);
                    } else {
                        curLevelValues.addFirst(node.val);
                    }
                }
            }
            allValues.add(curLevelValues);
            Deque<TreeNode> tmp = curLevel;
            curLevel = nextLevel;
            nextLevel = tmp;
        }
        return allValues;
    }

    public static void main(String[] args) {
        _103ZigzagLevelOrderTraversal t = new _103ZigzagLevelOrderTraversal();
        TreeNode root1 = new TreeNode(0);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(1);
        TreeNode root5 = new TreeNode(3);
        TreeNode root6 = new TreeNode(-1);
        TreeNode root7 = new TreeNode(5);
        TreeNode root8 = new TreeNode(1);
        TreeNode root9 = new TreeNode(6);
        TreeNode root10 = new TreeNode(8);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root3.left = root5;
        root3.right = root6;
        root4.left = root7;
        root4.right = root8;
        root5.right = root9;
        root6.left = root10;
        System.out.println(t.zigzagLevelOrder(root1));
//        Deque<Integer> deque = new LinkedList<>();
//        deque.push(12);
//        deque.push(123);
//        deque.push(4535);
//        System.out.println(deque);
//        deque.poll();
//        deque.pop();
//        System.out.println(deque);
    }
}
