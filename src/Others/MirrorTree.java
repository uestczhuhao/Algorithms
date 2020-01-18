package Others;

import javax.transaction.TransactionRequiredException;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class MirrorTree {
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
//        right_22.left = right_221;
//        right_22.right = right_222;

//        System.out.println(isMirror(root.left, root.right));
        printTree(root);
        revertTree(root);
//        buildMirror(root);
        printTree(root);
    }

    static boolean isMirror(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null){
            return root1 == root2;
        }
        if (root1.value != root2.value){
            return false;
        }

        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    /**
     * 构造镜像二叉树，非递归方法，类比于按层打印，只多了一个交换左右子树的过程
     * @param root
     * @return
     */
    static TreeNode buildMirror(TreeNode root){
        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode tmpNode = node.left;
            node.left = node.right;
            node.right = tmpNode;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 递归版本，从下往上一层层的左右交换，最后交换根节点的左右子树
     * @param root
     * @return
     */
    static TreeNode revertTree(TreeNode root){
        if (root == null){
            return null;
        }

        TreeNode right = revertTree(root.right);
        TreeNode left = revertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }


    /**
     * 按照层打印二叉树，利用队列先进先出特点
     * 先将某一层的节点从左至右放进队列，再输出队列即可
     * @param root 根节点
     */
    public static void printTree(TreeNode root){
        if (root == null){
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        System.out.println();
    }
}
