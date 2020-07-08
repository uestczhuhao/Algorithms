package LeetCode;

import java.util.*;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/3/6 22:59
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class _236LowestCommonAncestor {
    public static void main(String[] args) {

    }

    TreeNode lowestCommonTarget;

    /**
     * 递归解法，思路：
     * 条件：包含节点p或q
     * 若某节点n，其自身为p或q，左子树，右子树包含p或q，
     * 这三者有两个满足条件，则表明找到了p和q的最低公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonTarget = root;
        if (null == root) {
            return null;
        }

        doFindLowestCommonAncestor(root, p, q);
        return lowestCommonTarget;
    }

    private boolean doFindLowestCommonAncestor(TreeNode currentNode, TreeNode p, TreeNode q) {
        if (null == currentNode) {
            return false;
        }


        // 当前节点是否为p或q
        int cur = currentNode.val == p.val || currentNode.val == q.val ? 1 : 0;

        // 左右两边是否有p或q
        int left = doFindLowestCommonAncestor(currentNode.left, p, q) ? 1 : 0;
        int right = doFindLowestCommonAncestor(currentNode.right, p, q) ? 1 : 0;

        if (left + cur + right >= 2) {
            lowestCommonTarget = currentNode;
        }
        // 本轮是否找到了p或q
        return cur + left + right > 0;
    }

    /**
     * 思路：借助父节点的方式，从p和q逆向寻找
     * 1. 从根节点开始遍历树。
     * 2. 在找到 p 和 q 之前，将父指针存储在字典中。
     * 3. 一旦我们找到了 p 和 q，我们就可以使用父亲字典获得 p 的所有祖先，并添加到一个称为祖先的集合中。
     * 4. 同样，我们遍历节点 q 的祖先。如果祖先存在于为 p 设置的祖先中，这意味着这是 p 和 q 之间的第一个共同祖先（同时向上遍历），因此这是 LCA 节点。
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }

        Map<TreeNode, TreeNode> nodeParentMap = new HashMap<>();
        Deque<TreeNode> stack = new LinkedList<>();
        nodeParentMap.put(root, null);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.poll();
            if (curNode.left != null) {
                nodeParentMap.put(curNode.left, curNode);
                stack.push(curNode.left);
            }

            if (curNode.right != null) {
                nodeParentMap.put(curNode.right, curNode);
                stack.push(curNode.right);
            }
        }
        Set<TreeNode> parentSet = new HashSet<>();
        // p先遍历到根节点，把路径记录下来
        while (p != null) {
            parentSet.add(p);
            p = nodeParentMap.get(p);
        }

        // q再遍历，当此路径和p的遍历路径第一次相交时，交点即为最低公共祖先
        while (!parentSet.contains(q)) {
            q = nodeParentMap.get(q);
        }

        return q;
    }

    /**
     * 思路：dfs，从下往上走，一旦找到p和q在其左右的节点x，就返回之
     * 如果x还有父节点，祖父节点怎么办？
     * 那么其父节点和祖父节点都返回x
     * 因此x的下面的节点都会被x覆盖，x上面的节点都会返回x
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // 找到p和q，直接返回
        if (root == p || root == q) {
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor2(root.right, p, q);

        // 找到了x
        if (leftNode != null && rightNode != null) {
            return root;
        } else if (leftNode == null) {
            // 左边节点没找到，适用于
            // 1. 此节点是x的父（祖先）节点，且x在其右子树中，左边当然找不到
            // 2. 这个子树更大的子树满足1
            // 对1，其rightNode就是x；对2，rightNode为null，最终被淘汰
            return rightNode;
        } else {
            // 同上
            return leftNode;
        }
    }


    }
