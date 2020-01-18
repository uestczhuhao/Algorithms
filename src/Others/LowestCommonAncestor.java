package Others;

public class LowestCommonAncestor {

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
        right_22.left = right_221;
        right_22.right = right_222;

        System.out.println(LCAUp2Top(root,left_11,left_12).value);
//        System.out.println(hasNode(right_2,right_221));
    }

    /**
     * 自顶向下遍历，时间复杂度O(n^2)，因为每个节点都调用hasNode，其复杂度为O(n)
     * 1. 若node1，node2 分属于root的左右子树，则root即为所求
     * 2. 若node1，node2 都属于root左子树，则LCA在root左子树中
     * 3. 若node1，node2 都属于root右子树，则LCA在root又子树中
     * 4. 若node1或node2 不属于root，则进入else分支，返回null
     * @return LCA 或 null（未找到）
     */
    static TreeNode LCADown2Bottom(TreeNode root, TreeNode node1, TreeNode node2){
        if (root == null){
            return null;
        }

        if (hasNode(root.left,node1) && hasNode(root.right,node2)){
            return root;
        } else if(hasNode(root.left,node1) && hasNode(root.left,node2)){
            return LCADown2Bottom(root.left,node1,node2);
        } else if(hasNode(root.right,node1) && hasNode(root.right,node2)){
            return LCADown2Bottom(root.right,node1,node2);
        } else {
            return null;
        }
    }

    private static boolean hasNode (TreeNode root, TreeNode node){
        if (root == null){
            return false;
        }

        if (root.value == node.value){
            return true;
        }

        return hasNode(root.left,node) || hasNode(root.right,node);
    }


    /**
     * 自底向上算法，从root开始向下，遇到找到node1和node2时向上传递
     * 此时root会判断其左右子树是否都包含一个节点，
     * 若是，则root为LCA
     * 若只有左子树或右子树包含其中一个节点，另外一个子树不包含（即：left ！= null && right == null或相反）
     * 则继续向上传递，知道找到LCA或返回null
     *
     * 由于所有节点只遍历一次，因此其时间复杂度为O(n)，比自顶向下方法好
     */
    static TreeNode LCAUp2Top(TreeNode root, TreeNode node1, TreeNode node2){
        if (root == null){
            return null;
        }

        // 找到的值会向上传递，直到LCA被找到为止
        if (root.value == node1.value || root.value == node2.value){
            return root;
        }

        TreeNode left = LCAUp2Top(root.left, node1, node2);
        TreeNode right = LCAUp2Top(root.right, node1, node2);
        if (left != null && right !=null) {
            return root;
        }
        return left == null ? right : left;
    }
}
