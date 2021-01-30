package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-7-2 下午4:51.
 * Description:
 *给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * 示例 2：
 *
 *
 *
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 *
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 *
 */
public class _968BinaryTreeCameras {
    /**
     * 贪心算法
     *
     * 如果一个节点有孩子节点且没有被摄像机覆盖，则我们需要放置一个摄像机在该节点。
     * 此外，如果一个节点没有父节点且没有被覆盖，则必须放置一个摄像机在该节点。
     */
    private int ans;
    private Set<TreeNode> covered;
    public int minCameraCover(TreeNode root) {
        ans = 0;
        covered = new HashSet<>();
        // 等效在每个叶子节点的下面放一个摄像机
        // 理由是叶子节点放摄像机一定不是最优的，可以在叶子的父节点放置，起码不会更多
        covered.add(null);

        dfs(root, null);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node == null) {
            return;
        }
        dfs(node.left, node);
        dfs(node.right, node);

        // node的左右节点有没有被覆盖的情况
        boolean hasChildUncovered = !covered.contains(node.left) || !covered.contains(node.right);
        // node作为根节点，没有被覆盖
        boolean uncoverRoot= par == null && !covered.contains(node);
        // 如果一个节点的子节点都被覆盖，即hasChildUncovered，但它自己没有被覆盖
        // 此时不能简单的放置（在其父节点放置会更优），除非它已经是根节点了
        if (uncoverRoot || hasChildUncovered) {
            ans ++;
            covered.add(node);
            covered.add(par);
            covered.add(node.left);
            covered.add(node.right);
        }
    }

    /**
     * 动态规划，节点有三种状态
     * 状态0，此节点未被覆盖
     * 状态1，此节点被覆盖，但部署放置摄像机的节点
     * 状态2，此节点放置摄像机
     */
    public int minCameraCover1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (computeAndDefineState(root) == 0) {
            ans ++;
        }
        return ans;
    }

    /**
     * 返回节点node的三种状态
     * @return 0,1或2
     */
    public int computeAndDefineState(TreeNode node) {
        // null默认已覆盖
        if (node == null) {
            return 1;
        }

        int leftChildState = computeAndDefineState(node.left);
        int rightChildState = computeAndDefineState(node.right);

        // 左右子节点都没有被覆盖，则在放置一个摄像头，结果+1
        // 包含00 01 02 10 20
        if (leftChildState == 0 || rightChildState == 0) {
            ans ++;
            return 2;
        } else if (leftChildState == 1 && rightChildState == 1) {
            // 左右子节点都被覆盖，当前不适合放摄像头，直接返回0
            // 只包含11
            return 0;
        } else {
            // 左右节点一个被覆盖，一个有摄像头
            // 包含12 20 21 22
            return 1;
        }
    }

}
