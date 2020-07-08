package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/07/08 20:13
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 *  
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 */
public class _429NAryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<Node> curLevel = new LinkedList<>();
        curLevel.offer(root);
        List<List<Integer>> levelOrderTraversal = new ArrayList<>();
        while (!curLevel.isEmpty()) {

            List<Integer> curValues = new ArrayList<>();
            int size = curLevel.size();
            for (int i=0;i<size;i ++){
                Node node = curLevel.poll();
                curValues.add(node.val);
                if (node.children != null && !node.children.isEmpty()) {
                    curLevel.addAll(node.children);
                }
            }
            if (!curValues.isEmpty()) {
                levelOrderTraversal.add(curValues);
            }
        }

        return levelOrderTraversal;
    }

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
