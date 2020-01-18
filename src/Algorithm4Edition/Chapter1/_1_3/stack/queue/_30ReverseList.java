package Algorithm4Edition.Chapter1._1_3.stack.queue;

/**
 * Created by mizhu on 19-12-22 下午10:25
 */
public class _30ReverseList {
    public static void main(String[] args) {
        Node head = buildList();
        printList(head);
        printList(reverse(head));
    }

    private static Node buildList() {
        Node node3 = new Node(4, null);
        Node node2 = new Node(3, node3);
        Node node1 = new Node(2, node2);
        Node head = new Node(1, node1);
        new Node(5, node3);
        return head;
    }

    private static void printList(Node node) {
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 原地翻转链表
     * first: 原链表首元素
     * second: 原链表第二个元素
     * revFirst: 翻转后链表首元素
     */
    static Node reverse(Node head) {
        // 如果输入链表为空或只有一个节点，则返回本身
        if (head == null || head.next == null) {
            return head;
        }

        Node first = head;
        Node revFirst = null;
        while (first!=null) {
            Node second = first.next;
            first.next = revFirst;
            revFirst = first;
            first = second;
        }
        return revFirst;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
