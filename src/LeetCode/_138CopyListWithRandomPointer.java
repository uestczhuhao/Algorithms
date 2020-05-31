package LeetCode;

/**
 * @author mizhu
 * @date 20-5-31 下午5:22
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的 深拷贝。 
 *
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 */
public class _138CopyListWithRandomPointer {

    public static void main(String[] args) {
        _138CopyListWithRandomPointer t = new _138CopyListWithRandomPointer();
        Node head = new Node(1);
        Node next = new Node(2);
        head.next = next;
        next.random = next;
        printList(head);
        Node newHead = t.copyRandomList(head);
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

        Node copyHead = new Node(head.val);
        if (head.next == null) {
            copyHead.next = null;
            copyHead.random = head.random;
            return copyHead;
        }

        Node index = head;
        while (index != null) {
            Node newNode = new Node(index.val);
            insertNodeAfter(index, newNode);
            index = index.next.next;
        }

        copyHead = head.next;
        index = head;
        while (index != null && index.next != null) {
            if (index.random != null) {
                index.random = index.random.next;
            }
            index.next = index.next.next;
            index = index.next;
        }

        return copyHead;
    }

    private void insertNodeAfter(Node target, Node newNode) {
        newNode.next = target.next;
        newNode.random = target.random;
        target.next = newNode;
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
