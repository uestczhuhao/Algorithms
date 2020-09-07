package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/9/7 10:16
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class _437PathSumIII {
    /**
     * 前提：dfs的思路是从上往下，因此下面的思路是单调向下的
     * 思路：dfs + 回溯 + 保留前缀和（存放前缀和 -> 出现次数）
     * 1. 前缀和代表从root到当前节点到和，因为任意一个节点，从root下来只有一条路
     * 2. 如果两个节点A B，前缀和分别为sum1 sum2，则两个节点之间到路径和为sum2-sum1
     * 3. 通过2，若sum2-sum1 = target，则结果+1
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        // 用于计算从根节点开始的路径，否则会漏掉路径中包含根节点的记录
        prefixSumFreq.put(0, 1);
        calculatePath(root, prefixSumFreq, sum, 0);
        return pathNum;

    }

    private int pathNum = 0;

    private void calculatePath(TreeNode node, Map<Integer, Integer> prefixSumFreq, int target, int currentSum) {
        if (node == null) {
            return;
        }

        currentSum += node.val;
        // 先统计，再把currentNum加入map，
        // 否则对target = 0的情况，当前节点被错误统计（因为刚把当前值加进去就做统计，相当于当前节点到当前节点的距离，但显然不合法）
        pathNum += prefixSumFreq.getOrDefault(currentSum - target, 0);
        prefixSumFreq.put(currentSum, prefixSumFreq.getOrDefault(currentSum, 0) + 1);

        calculatePath(node.left, prefixSumFreq, target, currentSum);
        calculatePath(node.right, prefixSumFreq, target, currentSum);

        prefixSumFreq.put(currentSum, prefixSumFreq.getOrDefault(currentSum, 1) - 1);
//        prefixSumFreq.put(currentSum, prefixSumFreq.get(currentSum) - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(10);
        TreeNode left11 = new TreeNode(5);
        TreeNode left111 = new TreeNode(-5);
//        TreeNode left112 = new TreeNode(-2);
//        TreeNode left12 = new TreeNode(2);
//        TreeNode left122 = new TreeNode(1);
//        TreeNode right2 = new TreeNode(-3);
//        TreeNode right22 = new TreeNode(11);

        root.left = left1;
        left1.left = left11;
//        left1.right = left12;
//        left12.right = left122;
        left11.left = left111;
//        left11.right = left112;
//        root.right = right2;
//        right2.right = right22;
        _437PathSumIII t1 = new _437PathSumIII();

        System.out.println(t1.pathSum(root, 20));
    }


}
