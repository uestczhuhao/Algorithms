package Others;

public class TreeNodeCount {
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

        System.out.println(countKthNode(root,0));
//        System.out.println(countNoChildTreeNode(root));
    }

    public static int countNoChildTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null){
            return 1;
        }

        return countNoChildTreeNode(root.left) + countNoChildTreeNode(root.right);
    }

    public static int countTreeNode(TreeNode root) {
        if (root == null){
            return 0;
        }

        return countTreeNode(root.left) + countTreeNode(root.right) + 1;
    }

    public static int countKthNode(TreeNode root, int k) {
        if (root == null || k < 1){
            return 0;
        }

        if (k == 1){
            return 1;
        }
        return countKthNode(root.left, k-1 ) + countKthNode(root.right, k-1);
    }
}
