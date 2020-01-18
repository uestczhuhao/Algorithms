package Others;

public class RebuildTreeFromFrontAndMin {
    public static void main(String[] args) {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] midOrder = {4,7,2,1,5,3,8,6};
        TreeNode root = buildTreee(preOrder, midOrder);
        System.out.println(root.value);
        System.out.println(root.left.value);
        System.out.println(root.right.value);
        System.out.println(root.left.left.value);
        System.out.println(root.right.left.value);
        System.out.println(root.right.right.value);
    }

    static TreeNode buildTreee(int[] preOrder, int[] midOrder) {
        if (preOrder == null || midOrder == null || preOrder.length != midOrder.length){
            return null;
        }

        return doBuildTree(preOrder, midOrder, 0, preOrder.length-1, 0, midOrder.length - 1);
    }

    static TreeNode doBuildTree(int[] preOrder, int[] midOrder, int preStart,
                                int preEnd, int midStart, int midEnd) {
        TreeNode root = new TreeNode(preOrder[preStart]);
        root.left = root.right = null;
        if (preStart == preEnd && midStart == midEnd){
            return root;
        }

        int index;
        for (index = midStart;index <= midEnd; index++){
            // 找到本轮递归的根节点的位置
            if (preOrder[preStart] == midOrder[index]){
                break;
            }
        }

        // index位置即为本轮的根节点位置，计算其左右节点个数，准备下一次递归
        int leftNum = index - midStart;
        int rightNum = midEnd - index;
        if (leftNum > 0){
            root.left = doBuildTree(preOrder, midOrder, preStart +1 , preStart + leftNum, midStart, index-1);
        }
        if (rightNum > 0) {
            root.right = doBuildTree(preOrder, midOrder, preStart + 1 + leftNum, preEnd, index + 1,midEnd);
        }
        return root;
        
    }

}
