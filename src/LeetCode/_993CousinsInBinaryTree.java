package LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mizhu
 * @date 2020/7/3 22:04
 * <p>
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 */
public class _993CousinsInBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> curLevel = new LinkedList<>();
        curLevel.offer(root);

        while (!curLevel.isEmpty()) {

            int size = curLevel.size();
            int matchCnt = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = curLevel.poll();

                boolean leftMatch = false;
                boolean rightMatch = false;
                if (node.left != null) {
                    curLevel.offer(node.left);
                    int left = node.left.val;
                    if (left == x || left == y) {
                        leftMatch = true;
                        matchCnt++;
                    }

                }

                if (node.right != null) {
                    curLevel.offer(node.right);
                    int right = node.right.val;
                    if (right == x || right == y) {
                        rightMatch = true;
                        matchCnt++;
                    }
                }

                // 同一个父节点，返回false
                if (leftMatch && rightMatch) {
                    return false;
                }
            }

            // 一层遍历完，刚好俩都找到，且不是同一个父节点
            if (matchCnt == 2) {
                return true;
            }

            // 一层遍历完，只找到一个，肯定不符合要求
            if (matchCnt == 1) {
                return false;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        _993CousinsInBinaryTree t = new _993CousinsInBinaryTree();
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        root.left = root1;
        root.right = root2;
        root1.right = root3;
        System.out.println(t.isCousins(root, 3, 4));
    }
}
