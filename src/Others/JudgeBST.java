package Others;

public class JudgeBST {
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

        System.out.println(isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    /**
     * BST：二叉搜索树，满足以下三个条件
     * 1. 左子树的节点都小于此节点
     * 2. 右子树的节点都大于此节点
     * 3. 左右子树都是二叉搜索树
     *
     * 解法为O(n)方法，根据BST的特点，通过设置上下界的方法，
     * 每个节点只需要遍历一次，
     * @return
     */
    static boolean isValidBST(TreeNode root,int min, int max){
        if (root == null){
            return true;
        }

        if (root.value >= max || root.value <= min){
            return false;
        }

        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);
    }

    /**
     * 思路：一个二叉搜索树的中序遍历，正好是从小到大排列
     * 如果在遍历的过程中，后面的节点大于前面的节点，则返回false
     * @return
     */
    static int lastVisit = Integer.MIN_VALUE;
    static boolean isValidBSTByMidOrder(TreeNode root){
        if (root == null) {
            return true;
        }

        if (!isValidBSTByMidOrder(root.left)){
            return false;
        }

        if (root.value <= lastVisit){
            return false;
        }
        lastVisit = root.value;

        // 可以直接return isValidBSTByMidOrder(root.right)，为了可读性，保留这种写法
        if (!isValidBSTByMidOrder(root.right)){
            return false;
        }

        return true;
    }
}
