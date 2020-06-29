package LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-29 下午2:33.
 * Description:
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *  
 *
 * 我们应返回其最大深度，3。
 *
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class _559MaximumDepth {
    int maxDepth = 0;
    Queue<Node> curNodeStack = new LinkedList<>();
    Queue<Node> nextNodeStack = new LinkedList<>();

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> tmp;
        curNodeStack.offer(root);
        while (!curNodeStack.isEmpty() && !nextNodeStack.isEmpty()) {
            while (!nextNodeStack.isEmpty()) {

                Node node = nextNodeStack.poll();
                if (node.children != null) {
                    for (Node n:node.children) {
                        curNodeStack.offer(n);
                    }
                }
            }
            tmp = curNodeStack;
            curNodeStack = nextNodeStack;
            nextNodeStack = tmp;
        }
        return maxDepth;
    }

    public void computeDepth(Node root) {

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
    };
}
