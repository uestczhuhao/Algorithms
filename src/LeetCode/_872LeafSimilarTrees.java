package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/7/6 21:49
 *
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 *
 *
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *  
 *
 * 提示：
 *
 * 给定的两颗树可能会有 1 到 200 个结点。
 * 给定的两颗树上的值介于 0 到 200 之间。
 *
 */
public class _872LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        }

        if (root2 == null) {
            return false;
        }

        List<Integer> leafs1 = new ArrayList<>();
        List<Integer> leafs2 = new ArrayList<>();
        getLeafList(root1,leafs1);
        getLeafList(root2,leafs2);

        return isSimilar(leafs1, leafs2);
    }

    private boolean isSimilar(List<Integer> leafs1, List<Integer> leafs2) {
        if (leafs1.size() != leafs2.size()) {
            return false;
        }

        for (int i=0;i<leafs1.size();i++) {
            if (!leafs1.get(i).equals( leafs2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public void getLeafList(TreeNode root, List<Integer> ans) {
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return;
        }

        if (root.left != null) {
            getLeafList(root.left, ans);
        }

        if (root.right != null) {
            getLeafList(root.right, ans);
        }
    }
}
