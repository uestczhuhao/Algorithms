package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2021/1/28 10:32
 */
public class _104MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> currLevel = new LinkedList<>();
        Deque<TreeNode> nexLevel = new LinkedList<>();
        currLevel.push(root);
        int depth = 0;
        while (!currLevel.isEmpty()) {
            depth++;
            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                if (node.left != null) {
                    nexLevel.push(node.left);
                }

                if (node.right != null) {
                    nexLevel.push(node.right);
                }
            }
            currLevel = nexLevel;
            nexLevel = new LinkedList<>();
        }

        return depth;
    }

    /**
     * dfs解法，空间复杂度O(1)
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }
}
