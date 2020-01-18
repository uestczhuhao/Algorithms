package Others;

public class IsSameOf2Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(11);
        TreeNode left12 = new TreeNode(11);
        TreeNode right2 = new TreeNode(2);
        TreeNode right21 = new TreeNode(21);
        TreeNode right22 = new TreeNode(21);
        TreeNode right221 = new TreeNode(221);

        root.left = left1;
        left1.left = left11;
        left1.right = left12;
        root.right = right2;
        right2.left = right21;
        right2.right = right22;
//        right22.left = right221;

        System.out.println(isSameOf2Tree(right2.left,right2.right));
    }

    //左右子树完全一样，则可判定二叉树相等，否则不相等
    static boolean isSameOf2Tree(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null || root1.value != root2.value) {
            return false;
        }

        return isSameOf2Tree(root1.left,root2.left) && isSameOf2Tree(root1.right,root2.right);
    }
}
