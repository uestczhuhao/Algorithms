package LeetCode;

/**
 * @author mizhu
 * @date 20-5-31 下午5:22
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * <p>
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 */
public class _138CopyListWithRandomPointer {

    public static void main(String[] args) {
        _138CopyListWithRandomPointer t = new _138CopyListWithRandomPointer();
        Node head = new Node(7);
        Node next = new Node(13);
        Node next1 = new Node(11);
        Node next2 = new Node(10);
        Node next3 = new Node(1);
//        head.next = next;
//        next.next = next1;
//        next1.next = next2;
//        next2.next = next3;
//        next.random = head;
//        next3.random = head;
//        next1.random = next3;
        head.random = head;
        printList(head);
        Node newHead = t.copyRandomList(head);
        printList(head);
        printList(newHead);
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node index = head;
        while (index != null) {
            Node newNode = new Node(index.val);

            newNode.next = index.next;
            newNode.random = index.random;
            index.next = newNode;
            index = newNode.next;
        }

        Node copyHead = head.next;
        Node copyIndex = copyHead;
        while (copyIndex != null) {
            if (copyIndex.random != null) {
                copyIndex.random = copyIndex.random.next;
            }
            copyIndex = copyIndex.next == null ? null : copyIndex.next.next;
        }

        index = head;
        copyIndex = copyHead;

        while (copyIndex != null) {
            index.next = index.next.next;
            index = index.next;
            copyIndex.next = copyIndex.next == null ? null : copyIndex.next.next;
            copyIndex = copyIndex.next;
        }

        return copyHead;
    }

    private Node insertNodeAfter(Node target, Node newNode) {
        newNode.next = target.next;
        newNode.random = target.random;
        target.next = newNode;

        return newNode;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
