package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2020/11/7 23:21
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class _230KthSmallestInBST {
    /**
     * 中序（inOrder）遍历，到k终止
     */
    public int kthSmallest(TreeNode root, int k) {
        if (k <=0) {
            throw  new RuntimeException("Input illegal " + k);
        }

        Deque<TreeNode> inOrder = new LinkedList<>();
        TreeNode curNode = root;
        // curNode != null  进入右子树
        // !inOrder.isEmpty()   某个分支已无右子树，但树还没遍历完成
        while (curNode != null || !inOrder.isEmpty()) {
            while (curNode != null) {
                inOrder.push(curNode);
                curNode = curNode.left;
            }

            curNode = inOrder.pop();
            if (--k == 0) {
                return curNode.val;
            }
            curNode = curNode.right;
        }

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        _230KthSmallestInBST t = new _230KthSmallestInBST();

    }
}
