package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2020/7/5 15:52
 * <p>
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * <p>
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * <p>
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * <p>
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 * <p>
 * 提示：
 * <p>
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 */
public class _1028RecoverTreeFromPreorderTraversal {
    /**
     * 思路
     * 前序遍历的前提（基于尽量左子树，即不存在只有右子树的情况）
     * 如果一个节点有子节点，则遍历时一定紧随该节点
     * 其兄弟节点一定排在所有的子节点后
     */
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        int index = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        while (index < S.length()) {
            int level = 0;
            while (S.charAt(index) == '-') {
                level++;
                index++;
            }

            int value = 0;
            while (index < S.length() && Character.isDigit(S.charAt(index))) {
                value = 10 * value + S.charAt(index) - '0';
                index++;
            }

            TreeNode node = new TreeNode(value);

            if (!stack.isEmpty()) {
                // 此时stack的顶部为当前节点的父节点
                // 且当前node为其左子树
                if (level == stack.size()) {
                    stack.peek().left = node;
                } else {
                    // 依据前提，此时已经遍历到兄弟节点
                    // 其左边的兄弟子树已经全部构建完成，可以愉快的删除之
                    while (level < stack.size()) {
                        stack.pop();
                    }

                    // 删除的stack中只剩下了当前节点的父节点和祖先节点
                    // stack的顶部即为父节点，且左子树已经构建完了
                    if (!stack.isEmpty()) {
                        stack.peek().right = node;
                    }
                }
            }
            stack.push(node);
        }

//        while (stack.size() > 1) {
//            stack.pop();
//        }

        return stack.pollLast();
    }

    public static void main(String[] args) {
//        System.out.println(Character.digit('9', 10));
        _1028RecoverTreeFromPreorderTraversal t = new _1028RecoverTreeFromPreorderTraversal();
        TreeNode node = t.recoverFromPreorder("1-2--3---4-5--6---7");
        System.out.println(node.val);
    }
}
