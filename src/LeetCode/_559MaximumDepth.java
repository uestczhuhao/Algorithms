package LeetCode;

import java.util.*;

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

    /**
     * 层次遍历，非递归解法
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> tmp;
        curNodeStack.offer(root);
        while (!curNodeStack.isEmpty() || !nextNodeStack.isEmpty()) {
            maxDepth ++;
            while (!curNodeStack.isEmpty()) {
                Node node = curNodeStack.poll();
                if (node.children != null) {
                    for (Node n:node.children) {
                        nextNodeStack.offer(n);
                    }
                }
            }
            tmp = curNodeStack;
            curNodeStack = nextNodeStack;
            nextNodeStack = tmp;
        }
        return maxDepth;
    }

    /**
     * 层次遍历，递归解法
     * @param root
     * @return
     */
    public int computeMaxDepth(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children == null || root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> depths = new ArrayList<>();
            for (Node node : root.children) {
                depths.add(computeMaxDepth(node));
            }
            // 本层非空，也算一层，因此+1
            return Collections.max(depths) + 1;
        }
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

    public static void main(String[] args) {
        _559MaximumDepth t = new _559MaximumDepth();
        Node node = t.new Node(1, null);
        Node c1 = t.new Node(2);
        Node c2 = t.new Node(3);
        Node c3 = t.new Node(4);
        List<Node> a = new ArrayList<>();
        List<Node> b = new ArrayList<>();
//        a.add(c1);
        b.add(c2);
        b.add(c3);
        node.children = a;
        c1.children = b;
//        System.out.println(t.computeMaxDepth(node));

        List<Integer> depths = new ArrayList<>();
        for (Node n: a) {
            depths.add(n.val);
        }

        System.out.println(Collections.max(depths));

    }
}
