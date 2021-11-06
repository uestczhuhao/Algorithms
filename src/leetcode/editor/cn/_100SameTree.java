package leetcode.editor.cn;

//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 
//
// 示例 1： 
//
// 
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：p = [1,2], q = [1,null,2]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 两棵树上的节点数目都在范围 [0, 100] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 711 👎 0


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _100SameTree {
    public static void main(String[] args) {
        Solution t = new _100SameTree().new Solution();
        TreeNode root1 = new TreeNode(10);
        TreeNode root2 = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(15);
        root1.left = n1;
        root1.right = n2;
        root2.left = n3;
        root2.right = n4;
        t.isSameTree(root1,root2);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null) {
                return q == null;
            }

            if (q == null) {
                return false;
            }
            Deque<TreeNode> pStack = new LinkedList<>();
            Deque<TreeNode> qStack = new LinkedList<>();
            pStack.offer(p);
            qStack.offer(q);
            while (!pStack.isEmpty() && !qStack.isEmpty()) {
                p = pStack.poll();
                q = qStack.poll();
                if (p == null || q == null) {
                    return false;
                }
                if (p.val != q.val) {
                    return false;
                }
                if (p.left != null || q.left != null) {
                    pStack.offer(p.left);
                    qStack.offer(q.left);
                }
                if (p.right != null || q.right != null) {
                    pStack.offer(p.right);
                    qStack.offer(q.right);
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}