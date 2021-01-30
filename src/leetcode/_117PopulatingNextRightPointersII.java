package leetcode;

/**
 * @author mizhu
 * @date 2020/8/16 09:18
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class _117PopulatingNextRightPointersII {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // 下一层的头节点的前一个哨兵节点
        Node nexLevelHead = new Node(-1);
        Node curLevelIndex = root;
        // 下一层的index指针
        Node nexLevelIndex = nexLevelHead;
        while (curLevelIndex != null) {
            if (curLevelIndex.left != null) {
                nexLevelIndex.next = curLevelIndex.left;
                nexLevelIndex = nexLevelIndex.next;
            }
            if (curLevelIndex.right != null) {
                nexLevelIndex.next = curLevelIndex.right;
                nexLevelIndex = nexLevelIndex.next;
            }

            if (curLevelIndex.next != null) {
                // 在本层依次往后走
                curLevelIndex = curLevelIndex.next;
            } else { // 本层遍历完了，进入下一层
                curLevelIndex = nexLevelHead.next;
                nexLevelHead.next = null;
                nexLevelIndex = nexLevelHead;
            }
        }


        return root;
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        Node root2 = new Node(2);
        Node root3 = new Node(3);
        Node root4 = new Node(4);
        Node root5 = new Node(5);
//        Node root6 = new Node(6);
        Node root7 = new Node(7);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
//        root3.left = root6;
        root3.right = root7;
        _117PopulatingNextRightPointersII t = new _117PopulatingNextRightPointersII();
        t.connect(root1);
    }


    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
