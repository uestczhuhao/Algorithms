package LeetCode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * @author mizhu
 * @date 2020/4/29 15:53
 * 不使用任何库函数，设计一个跳表。
 * <p>
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * <p>
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * <p>
 * 样例:
 * <p>
 * Skiplist skiplist = new Skiplist();
 * <p>
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * 约束条件:
 * <p>
 * 0 <= num, target <= 20000
 * 最多调用 50000 次 search, add, 以及 erase操作。
 */
public class _1206DesignSkiplist {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(1));
        skiplist.add(4);
        System.out.println(skiplist.search(1));
        System.out.println(skiplist.erase(0));
        System.out.println(skiplist.erase(1));
        System.out.println(skiplist.search(1));
    }

    private static class Skiplist {

        private static class Node {
            int value;
            Node next;
            Node down;

            public Node(int value) {
                this.value = value;
                this.next = null;
                this.down = null;
            }

            public Node(int value, Node next, Node down) {
                this.value = value;
                this.next = next;
                this.down = down;
            }
        }

        private Node head;
        private Random random = new Random();

        public Skiplist() {
            head = new Node(-1);
        }

        public boolean search(int target) {
            Node current = head;
            while (current != null) {
                // 在一层中找到合适的区间，即当前节点的下一个节点不小于目标值
                while (current.next != null && current.next.value < target) {
                    current = current.next;
                }

                // 如果当前层中，那么必定在current处
                // 否则在下一层中尝试寻找
                if (current.next == null || current.next.value > target) {
                    current = current.down;
                } else {
                    return true;
                }
            }
            return false;
        }

        public void add(int num) {
            Deque<Node> path = new LinkedList<>();
            Node current = head;
            while (current != null) {
                while (current.next != null && current.next.value < num) {
                    current = current.next;
                }
                path.push(current);
                current = current.down;
            }
            boolean updateHigh = true;
            // 新增节点作为下一轮新增节点的下游
            // 理由是add是从下往上进行
            Node newDownNode = null;
            while (updateHigh && !path.isEmpty()) {
                Node preInsert = path.pop();
                newDownNode = new Node(num, preInsert.next, newDownNode);
                preInsert.next = newDownNode;
                updateHigh = random.nextBoolean();
            }

            // 需要新加一层
            if (updateHigh) {
                Node newHead = new Node(num, null, newDownNode);
                /* 新的head必须要在旧的head的顶，例如：
                head --------->  3
                 |               |
                head -> 1 ->2 -> 3
                对于上诉例子，当搜索3时，第一层搜不到，需要到第二层去搜索，因此需要从第一层的head下来
                 */
                head = new Node(-1, newHead, head);
            }
        }

        public boolean erase(int num) {
            Node current = head;
            boolean search = false;
            while (current != null) {
                while (current.next != null && current.next.value < num) {
                    current = current.next;
                }

                if (current.next == null || current.next.value > num) {
                    current = current.down;
                } else {
                    // 找到目标节点，注意找到d节点就是current.next，删除之
                    search = true;
                    current.next = current.next.next;
                    current = current.down;
                }
            }
            return search;
        }
    }

}

class Skiplist {
    private Map<Integer, Integer> map;

    public Skiplist() {
        map = new HashMap<>();
    }

    public boolean search(int target) {
        if (map.containsKey(target)) {
            return true;
        }

        return false;
    }

    public void add(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    public boolean erase(int num) {
        if (!map.containsKey(num)) {
            return false;
        }
        map.put(num, map.get(num) - 1);
        if (map.get(num) == 0) {
            map.remove(num);
        }
        return true;
    }
}


