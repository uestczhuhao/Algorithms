package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 20-6-20 下午9:08
 * <p>
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * <p>
 * <p>
 * 扁平化后的链表如下图：
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2,null,3]
 * 输出：[1,3,2]
 * 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * 1---2---NULL
 * |
 * 3---NULL
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 *  
 * <p>
 * 如何表示测试用例中的多级链表？
 * <p>
 * 以 示例 1 为例：
 * <p>
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL
 * 序列化其中的每一级之后：
 * <p>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 * <p>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 */
public class _430FlattenMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node beforeHead = new Node(0, null, head, null);
        Node preNode = beforeHead, curnode;
        // 利用栈的先进后出的特性实现dfs
        // 先把next入栈，再把child入栈
        Deque<Node> stack = new LinkedList<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            curnode = stack.pop();
            preNode.next = curnode;
            curnode.prev = preNode;

            // 先加入当前节点的右边
            // 因为要把其放在子链表的最后，所以最先加入
            if (curnode.next != null) {
                stack.push(curnode.next);
            }

            if (curnode.child != null) {
                stack.push(curnode.child);
                curnode.child = null;
            }

            preNode = curnode;
        }

        Node res = beforeHead.next;
        res.prev = null;
        return res;
    }

    public static void main(String[] args) {
        _430FlattenMultilevelDoublyLinkedList t = new _430FlattenMultilevelDoublyLinkedList();
        Node head = t.new Node(1);
        Node node1 = t.new Node(2, head, null, null);
        Node node2 = t.new Node(3, node1, null, null);
        Node node3 = t.new Node(4, node2, null, null);
        Node node4 = t.new Node(5, node3, null, null);
        Node node5 = t.new Node(6, node4, null, null);
        Node node6 = t.new Node(7);
        Node node7 = t.new Node(8, node6, null, null);
        Node node8 = t.new Node(9, node7, null, null);
        Node node9 = t.new Node(10, node8, null, null);
        Node node10 = t.new Node(11);
        Node node11 = t.new Node(12, node10, null, null);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.child = node6;

        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node7.child = node10;

        node10.next = node11;

        Node flatten = t.flatten(head);
        System.out.println(flatten);
    }


    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int value) {
            this.val = value;
        }

        public Node(int value, Node prev, Node next, Node child) {
            this.val = value;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }
}


