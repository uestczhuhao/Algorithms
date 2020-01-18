package Others;

/**
 * 二叉树的最大深度和最小深度
 * 深度：二叉树的根节点到最远/最近子节点的距离，空树则返回0
 */
public class TreeDepth {
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
//        right2.left = right21;
        right2.right = right22;

//        int res = maxDepth(root);
        int res = minDepth(root);
        System.out.println(res);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return left > right ? left + 1 : right + 1;
    }

    /**
     * 分以下几种情况：
     * 1. 若节点为叶子节点，则返回1
     * 2. 节点不为叶子节点，且其左子树为空，则遍历其右子树
     * 3. 节点不为叶子节点，且其右子树为空，则遍历其左子树
     *
     * 注意：2，3种情形易被当成叶子节点处理
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        //若为叶子节点，返回1
        if (root.right == null && root.left == null) {
            return 1;
        }

        // 左节点为空，递归遍历其右子树，
        if (root.left == null ) { return minDepth(root.right) + 1;}
        if (root.right == null ) { return minDepth(root.left) + 1;}

        return Math.min(minDepth(root.left) ,minDepth(root.right)) + 1;
    }

}
