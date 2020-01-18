package Others;

import java.util.LinkedList;

public class LCAByPath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left_1 = new TreeNode(1);
        TreeNode left_11 = new TreeNode(11);
        TreeNode left_12 = new TreeNode(12);
        TreeNode right_2 = new TreeNode(2);
        TreeNode right_21 = new TreeNode(21);
        TreeNode right_22 = new TreeNode(22);
        TreeNode right_221 = new TreeNode(221);
        TreeNode right_222 = new TreeNode(222);

        root.left = left_1;
        left_1.left = left_11;
        left_1.right = left_12;
        root.right = right_2;
        right_2.left = right_21;
        right_2.right = right_22;
        right_22.left = right_221;
        right_22.right = right_222;

        LinkedList<TreeNode> list = new LinkedList<>();
        findPath2Root(root, right_222, list);

        System.out.println(getLowestCommonAncestor(root, left_11, right_2).value);
    }

    static void printListTree(LinkedList<TreeNode> list) {
        if (!list.isEmpty()) {
            for (TreeNode n : list) {
                System.out.print(n.value + " ");
            }
            System.out.println();
        }
    }

    static TreeNode getLowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        LinkedList<TreeNode> path1 = new LinkedList<>();
        LinkedList<TreeNode> path2 = new LinkedList<>();
        findPath2Root(root, node1, path1);
        findPath2Root(root, node2, path2);
        printListTree(path1);
        printListTree(path2);
        if (path1.isEmpty() || path2.isEmpty()) {
            return null;
        }
        int len = Math.min(path1.size(), path2.size());
        TreeNode result = null;
        for (int i = 0; i < len; i++) {
            if (path1.get(i).value != path2.get(i).value) {
                break;
            }
            result = path1.get(i);
        }
        return result;
    }

    static boolean findPath2Root(TreeNode root, TreeNode node, LinkedList<TreeNode> path) {
        if (root == null || node == null) {
            return false;
        }

        path.add(root);
        if (root.value == node.value) {
            return true;
        }

        // 此处不能 return findPath2Root(root.left,node,path);
        // 因为此时root的右子树还未找过，不能直接返回
        if (findPath2Root(root.left, node, path)) {
            return true;
        }
        if (findPath2Root(root.right, node, path)) {
            return true;
        }
        path.removeLast();
        return false;
    }
}
