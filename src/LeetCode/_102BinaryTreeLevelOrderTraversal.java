package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author mizhu
 * @date 20-3-3 下午8:42
 * <p>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _102BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        _102BinaryTreeLevelOrderTraversal t = new _102BinaryTreeLevelOrderTraversal();
        TreeNode root = new TreeNode(1);
        TreeNode fLeft = new TreeNode(2);
        TreeNode fRight = new TreeNode(3);
        TreeNode sLLeft = new TreeNode(3);
        TreeNode sLRight = new TreeNode(4);
        TreeNode sRLeft = new TreeNode(4);
        TreeNode sRRight = new TreeNode(3);
        root.left = fLeft;
        root.right = fRight;
        fLeft.left = sLLeft;
        fLeft.right = sLRight;
        fRight.left = sRLeft;
        fRight.right = sRRight;

        t.levelOrder1(root);
        System.out.println(t.levels);
    }

    private List<List<Integer>> levels = new ArrayList<>();

    /**
     * 递归版本
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return levels;
        }

        doLevelOrder(root, 0);
        return levels;
    }

    public void doLevelOrder(TreeNode node, int level) {
        if (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.val);

        if (node.left != null) {
            doLevelOrder(node.left, level + 1);
        }

        if (node.right != null) {
            doLevelOrder(node.right, level + 1);
        }
    }

    /**
     * 非递归版本
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        if (root == null) {
            return levels;
        }
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            int levelLen = nodeQueue.size();
            List<Integer> levelNums = new ArrayList<>();
            for (int i=0;i<levelLen; i++) {
                TreeNode node = nodeQueue.poll();
                levelNums.add(node.val);
                if (node.left !=null) {
                    nodeQueue.add(node.left);
                }

                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            levels.add(levelNums);
        }

        return levels;
    }
}
