package Others;

import com.sun.jmx.remote.internal.ArrayQueue;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Method2TraverseTrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(11);
        TreeNode left12 = new TreeNode(12);
        TreeNode right2 = new TreeNode(2);
        TreeNode right21 = new TreeNode(21);
        TreeNode right22 = new TreeNode(22);
        root.left = left1;
        left1.left = left11;
        left1.right = left12;
        root.right = right2;
        right2.left = right21;
        right2.right = right22;
        ArrayList<TreeNode> res =  new ArrayList<TreeNode>();
        res = posOrderNoRecur(root);
        printTreeNodeList(res);
//        printTree(root);
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
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
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
    }

    private static void printTreeNodeList(List<TreeNode> list){
        for (TreeNode node : list) {
            System.out.print(node.value + " ");
        }
    }

    public static void preOrderRecur(TreeNode root, ArrayList<TreeNode> list){
        if (root == null){
            return ;
        }
        list.add(root);
        preOrderRecur(root.left, list);
        preOrderRecur(root.right, list);
    }

    public static void midOrderRecur(TreeNode root, ArrayList<TreeNode> list){
        if (root == null) {
            return;
        }
        midOrderRecur(root.left, list);
        list.add(root);
        midOrderRecur(root.right, list);
    }

    public static void posOrderRecur(TreeNode root, ArrayList<TreeNode> list){
        if (root == null) {
            return;
        }
        posOrderRecur(root.left, list);
        posOrderRecur(root.right, list);
        list.add(root);
    }

    public static ArrayList<TreeNode> preOrderNoRecur(TreeNode root){
        ArrayList<TreeNode> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }

        }
        return list;
    }

    public static ArrayList<TreeNode> midOrderNoRecur(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){

            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node);
            node = node.right;
        }

        return list;
    }

    /**
     * 直接法：先遍历至树的最左端，并入栈
     * 对栈顶元素判断，若其右子树不为空且未被访问过，
     * 则右子树入栈，入栈方式依然是遍历至其最左端
     *
     * 否则栈顶元素出栈，并记录
     * @param root
     * @return
     */
    public static ArrayList<TreeNode> posOrderNoRecur(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();

        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        // 上次访问的节点，主要用于记录根节点弹出之前，其右子树是否已经弹出
        // 如果弹出，则证明其右子树都已输出
        TreeNode lastVisit = null;

        // 当前节点置于最左子树的最下方
        while (curNode != null){
            stack.push(curNode);
            curNode = curNode.left;
        }

        while (!stack.isEmpty()){
            curNode = stack.peek();
            if (curNode.right != null && curNode.right != lastVisit){

                curNode = curNode.right;
                while (curNode != null) {
                    stack.push(curNode);
                    curNode = curNode.left;
                }
            } else {
                stack.pop();
                list.add(curNode);
                lastVisit = curNode;
            }
        }

        return list;
    }

}

class TreeNode {
    TreeNode(int val) {
        this.value = val;
    }
    int value;
    TreeNode left;
    TreeNode right;

}
