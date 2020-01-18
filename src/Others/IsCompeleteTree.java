package Others;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 完全二叉树：与满二叉树（每层有2^n-1个节点）编号相同的二叉树
 * 即所有的叶子节点在n或n-1层
 * 只有第n层可以不满，前n-1层必须都为满树
 * 不存在只有右子树没有左子树的节点
 */
public class IsCompeleteTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(11);
        TreeNode left12 = new TreeNode(12);
        TreeNode right2 = new TreeNode(2);
        TreeNode right21 = new TreeNode(21);
        TreeNode right22 = new TreeNode(22);
        TreeNode right221 = new TreeNode(221);

        root.left = left1;
        left1.left = left11;
        left1.right = left12;
        root.right = right2;
        right2.left = right21;
        right2.right = right22;
        right22.left = right221;

        System.out.println(isCompeleteTree(root));
        System.out.println(isCompeleteTreeWithFlag(root));
    }

    /**
     * 用LinkedList充当队列（不能使用ArrayDeque，不能add 空值），
     * 将树从上至下按层输入队列，叶子节点输入两个null，弹出到null为止
     * 最后观察剩余队列，若全为空，则说明为完全二叉树
     * 参考：https://blog.csdn.net/number_0_0/article/details/76177479
     *
     *
     * @param root
     * @return
     */
    static boolean isCompeleteTree(TreeNode root) {
        if (root == null){
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node = queue.poll();

        while (node != null) {
            queue.offer(node.left);
            queue.offer(node.right);
//            printQueue(queue);
//            System.out.println(queue.size());
            node = queue.poll();
        }

        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node != null){
                return false;
            }
        }

        return true;
    }


    static private void printQueue(LinkedList<TreeNode> queue) {
        for (TreeNode node : queue){
            if (node == null) {
                System.out.print(" null");
            } else {
                System.out.print(" " + node.value);
            }
        }
        System.out.println();
    }

    /**
     * 标记两次，第一次在节点只有一个左子树时（只有右子树直接判否）
     * @param root
     * @return
     */
    static boolean isCompeleteTreeWithFlag(TreeNode root) {
        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        boolean flag = false;

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (flag && (node.left!=null || node.right != null)){
                return false;
            }
            /**
             * 分为四种情况：
             * 1. 左子树不为空，右子树为空，此时flag = false，全树唯一一个
             * 2. 左子树为空，右子树不为空，不满足完全二叉树条件，直接返回false
             * 3. 左右子树俊不为空，依次加入队列（这种情形最多）
             * 4. 左右子树均为空，遍历至叶子节点，后序必须全为叶子节点，
             *    第一次进入为n层或n-1层最左侧的叶子节点
             */
            if (node.left != null && node.right == null){
                queue.offer(node.left);
                flag = true;
            } else if (node.left == null && node.right != null){
                return false;
            } else if (node.left != null){
                queue.offer(node.left);
                queue.offer(node.right);
            }else {
                // 此时节点为叶子节点，flag置为true
                // 因为从第一个叶子节点起，后序的节点必须全为叶子节点，否则不满足完全树定义
                flag =true;
            }
        }

        return true;
    }
}
