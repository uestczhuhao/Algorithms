package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-28 下午7:06.
 * Description:
 *
 * 给出一个满足下述规则的二叉树：
 *
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 *
 * 请你先还原二叉树，然后实现 FindElements 类：
 *
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：
 * ["FindElements","find","find"]
 * [[[-1,null,-1]],[1],[2]]
 * 输出：
 * [null,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1]);
 * findElements.find(1); // return False
 * findElements.find(2); // return True
 * 示例 2：
 *
 *
 *
 * 输入：
 * ["FindElements","find","find","find"]
 * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
 * 输出：
 * [null,true,true,false]
 * 解释：
 * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True
 * findElements.find(3); // return True
 * findElements.find(5); // return False
 * 示例 3：
 *
 *
 *
 * 输入：
 * ["FindElements","find","find","find","find"]
 * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
 * 输出：
 * [null,true,false,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
 * findElements.find(2); // return True
 * findElements.find(3); // return False
 * findElements.find(4); // return False
 * findElements.find(5); // return True
 *  
 *
 * 提示：
 *
 * TreeNode.val == -1
 * 二叉树的高度不超过 20
 * 节点的总数在 [1, 10^4] 之间
 * 调用 find() 的总次数在 [1, 10^4] 之间
 * 0 <= target <= 10^6
 */
public class _1261FindElements {
    public _1261FindElements(TreeNode root) {
        if (root == null || root.val == 0) {
            return;
        }
        recover(root);
    }

    private Set<Integer> values = new HashSet<>();
    private Queue<TreeNode> queue = new LinkedList<>();

    /**
     * 层次遍历，先访问父节点（通过队列即可），再访问其左右子节点，同时置node的左右孩子的值；
     * 维护一个set，记录出现过的值，find函数可以直接返回set是否包含某值即可
     */
    public void recover(TreeNode root) {
        root.val = 0;
        values.add(0);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                values.add(node.val * 2 + 1);
                queue.offer(node.left);
            }

            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                values.add(node.val * 2 + 2);
                queue.offer(node.right);
            }
        }
    }

    public boolean find(int target) {
        return values.contains(target);
    }
}
