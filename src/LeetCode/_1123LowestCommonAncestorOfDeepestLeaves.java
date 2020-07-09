package LeetCode;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/07/09 19:43
 * 给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。
 * <p>
 * 回想一下：
 * <p>
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3]
 * 输出：[1,2,3]
 * 解释：
 * 最深的叶子是值为 2 和 3 的节点。
 * 这些叶子的最近共同祖先是值为 1 的节点。
 * 返回的答案为序列化的 TreeNode 对象（不是数组）"[1,2,3]" 。
 * 示例 2：
 * <p>
 * 输入：root = [1,2,3,4]
 * 输出：[4]
 * 示例 3：
 * <p>
 * 输入：root = [1,2,3,4,5]
 * 输出：[2,4,5]
 *  
 * <p>
 * 提示：
 * <p>
 * 给你的树中将有 1 到 1000 个节点。
 * 树中每个节点的值都在 1 到 1000 之间。
 */
public class _1123LowestCommonAncestorOfDeepestLeaves {
    int maxDepth = 0;
    int curDepth = 0;
    TreeNode firstLeaf = null;
    TreeNode secondLeaf = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }

        findDeepestLeaves(root);
        if (firstLeaf == null) {
            return secondLeaf;
        }

        if (secondLeaf == null) {
            return firstLeaf;
        }
        return lowestCommonAncestor(root, firstLeaf, secondLeaf);
    }

    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode firstLeaf, TreeNode secondLeaf) {
        if (root == null) {
            return null;
        }

        if (root == firstLeaf || root == secondLeaf) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, firstLeaf, secondLeaf);
        TreeNode right = lowestCommonAncestor(root.right, firstLeaf, secondLeaf);
        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }

    private void findDeepestLeaves(TreeNode root) {
        if (root == null) {
            return;
        }

        curDepth ++;
        if (root.left == null && root.right == null) {
            if (curDepth > maxDepth) {
                maxDepth = curDepth;
                firstLeaf = root;
                secondLeaf = null;
            } else if (curDepth == maxDepth) {
                secondLeaf = root;
            }
        }
        findDeepestLeaves(root.left);
        findDeepestLeaves(root.right);
        curDepth --;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
//        node2.left = node3;
//        node2.right = node4;
//        node4.left = node5;
        _1123LowestCommonAncestorOfDeepestLeaves t = new _1123LowestCommonAncestorOfDeepestLeaves();
        System.out.println(t.lcaDeepestLeaves(node1));
    }
}
