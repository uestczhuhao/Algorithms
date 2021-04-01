package leetcode;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/4/7 20:38
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
public class _297SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode rightL = new TreeNode(3);
        TreeNode rightR = new TreeNode(4);
        root.right = right;
        right.left = rightL;
        right.right = rightR;
        _297SerializeAndDeserializeBinaryTree t = new _297SerializeAndDeserializeBinaryTree();
        System.out.println(t.serialize(root));
        System.out.println(t.serialize(t.deserialize(t.serialize(root))));
    }

    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }

        StringBuilder serResult = new StringBuilder();
        doSerialize(root, serResult);
        serResult.deleteCharAt(serResult.length() - 1);
        return serResult.toString();
    }

    public void doSerialize(TreeNode node, StringBuilder resultStr) {
        if (node == null) {
            resultStr.append("null,");
            return;
        }

        resultStr.append(node.val).append(",");
        doSerialize(node.left, resultStr);
        doSerialize(node.right, resultStr);
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        String[] nodeStrs = data.split(",");
        Queue<String> nodeStrQueue = new LinkedList<>(Arrays.asList(nodeStrs));
        if (nodeStrs.length == 0 || "null".equals(nodeStrs[0])) {
            return null;
        }

        return doDeserialize(nodeStrQueue);
    }

    public TreeNode doDeserialize(Queue<String> nodeQueue) {
        if (nodeQueue.isEmpty()) {
            return null;
        }

        String nodeStr = nodeQueue.poll();
        if ("null".equals(nodeStr)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodeStr));
        root.left = doDeserialize(nodeQueue);
        root.right = doDeserialize(nodeQueue);

        return root;
    }
}
