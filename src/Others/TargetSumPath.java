package Others;

import java.util.LinkedList;

public class TargetSumPath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left_1 = new TreeNode(1);
        TreeNode left_12 = new TreeNode(12);
        TreeNode left_11 = new TreeNode(2);
        TreeNode left_119 = new TreeNode(10);
        TreeNode right_2 = new TreeNode(2);
        TreeNode right_21 = new TreeNode(11);
        TreeNode right_22 = new TreeNode(22);
        TreeNode right_221 = new TreeNode(221);
        TreeNode right_222 = new TreeNode(222);

        root.left = left_1;
        left_1.left = left_11;
        left_1.right = left_12;
        left_11.left = left_119;
        root.right = right_2;
        right_2.left = right_21;
        right_2.right = right_22;
        right_22.left = right_221;
        right_22.right = right_222;

        LinkedList<LinkedList<TreeNode>> paths = new LinkedList<>();
        LinkedList<TreeNode> curPaths = new LinkedList<>();
        findPathSumEqualTarget(root, 13, paths, curPaths, 0);
        if (!paths.isEmpty()) {
            for (LinkedList<TreeNode> pa : paths) {
                LCAByPath.printListTree(pa);
            }
        }
//        System.out.println(paths.size());
    }

    static void findPathSumEqualTarget(TreeNode root, int target, LinkedList<LinkedList<TreeNode>> paths, LinkedList<TreeNode> curPath, int curSum) {
        if (root == null) {
            return;
        }

        curPath.add(root);
        curSum += root.value;
        if (root.right == null && root.left == null && curSum == target) {
            // 此处必须 new 一个队列，因为原队列还在时刻改变,而我们需要的是 curPath 的快照
            paths.add(new LinkedList<TreeNode>(curPath));
        }

        findPathSumEqualTarget(root.left, target, paths, curPath, curSum);
        findPathSumEqualTarget(root.right, target, paths, curPath, curSum);

        TreeNode rem = curPath.removeLast();
        curSum -= rem.value;
    }
}
