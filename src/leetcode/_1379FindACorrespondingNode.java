package leetcode;

/**
 * @author mizhu
 * @date 2020/7/1 23:34
 * <p>
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * <p>
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * <p>
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * <p>
 *  
 * <p>
 * 注意：
 * <p>
 * 你 不能 对两棵二叉树，以及 target 节点进行更改。
 * 只能 返回对克隆树 cloned 中已有的节点的引用。
 * 进阶：如果树中允许出现值相同的节点，你将如何解答？
 */
public class _1379FindACorrespondingNode {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null || target == null) {
            return null;
        }

        return findTarget(original, cloned, target);
    }

    public TreeNode findTarget(TreeNode origin, TreeNode clone, TreeNode target) {
        if (origin == target) {
            return clone;
        }

        TreeNode res = null;
        if (origin.left != null) {
            res = findTarget(origin.left, clone.left, target);
        }

        // 提前返回，节省一半时间
        // 因为若在左子树中已经找到了，就不需要在右边找了
        if (res != null) {
            return res;
        }

        if (origin.right != null) {
            res = findTarget(origin.right, clone.right, target);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
