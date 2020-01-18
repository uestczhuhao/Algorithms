package Others;

public class IsBalenceTree {
    public static void main(String[] args){
        TreeNode root = new TreeNode(0);
        TreeNode left1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(11);
        TreeNode left12 = new TreeNode(12);
        TreeNode right2 = new TreeNode(2);
        TreeNode right21 = new TreeNode(21);
        TreeNode right22 = new TreeNode(22);
        TreeNode right221 = new TreeNode(221);

        root.left = left1;
//        left1.left = left11;
//        left1.right = left12;
        root.right = right2;
        right2.left = right21;
        right2.right = right22;
        right22.left = right221;

        System.out.println(isBalanceTree(root));
    }

    /**
     * 平衡二叉树定义：左右子树深度差距不大于1，且左右子树都为平衡二叉树
     * @param root
     * @return
     */
    static boolean isBalanceTree(TreeNode root){
        return maxDepth(root) != -1;
    }

    /**
     * 1. 若左子树或右子树不为平衡树，则该树不平衡
     * 2. 若左子树，右子树深度差别超过1，则树不平衡，
     *    判断标准为左子树的最大深度和右子树的最大深度差值不大于1
     *
     * @param root
     * @return
     */
    static int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        System.out.println("Root is " + root.value + "  left " + left + "  right " + right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        }

        return Math.max(left, right) +1;
    }

    protected void test(){
        System.out.println("base test protected.");
    }
}
