package leetcode;

/**
 * @author mizhu
 * @date 20-1-28 下午5:08
 */

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 ); // 缓存容量
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class _146LRUCache {
    public static void main(String[] args) {
        _146LRUCache cache = new _146LRUCache( 3);
//        cache.put(2,1);
//        System.out.println(cache.get(2));

//        LinkedList<Integer> list = new LinkedList<>();
//        list.addFirst(1);
//        list.addFirst(2);
//        System.out.println(list);
//        list.removeLast();
//        System.out.println(list);
//
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));

//        [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,-1,null,null,-1,null,null,null,null,14,null,null,-1,null,null,-1,null,null,null,null,null,-1,null,null,-1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,-1,-1,-1,null,null,null,-1,null,null,null,20,null,null,null,29,-1,-1,null,null,null,null,20,null,null,null,null,null,null,null]
//        [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,22,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    private int size;

    /**
     * 采用HashMap保证get的O(1)复杂度
     * 采用双向链表的首尾添加和删除操作，保证put的O(1)复杂度
     *
     * @param capacity 容量
     */
    public _146LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        size = 0;
        head = new Node(-1, -1);
        tail = new Node(-1,-1);
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node tgtNode = map.get(key);
            int value = tgtNode.value;
            move2Head(tgtNode);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // 如果新节点已经在map中，更新即可；否则创建一个新的
        if (!map.containsKey(key)) {
            Node newNode = new Node(key, value);
            // 超过阈值，则删除最旧的，注意跳过最后的辅助节点
            if (size >= capacity) {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
            }
            addHeadNode(newNode);
            map.put(key, newNode);
        } else {
            Node node = map.get(key);
            node.value = value;
            map.put(key, node);
            move2Head(node);
        }
    }

    private void move2Head(Node keyNode) {
        deleteNode(keyNode);
        addHeadNode(keyNode);
    }

    /**
     * tail是哨兵，总是删除tail前面的元素
     */
    private void deleteNode(Node keyNode) {
        Node preNode = keyNode.prev;
        Node nextNode = keyNode.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;
        keyNode.prev = null;
        keyNode.next = null;
        size--;
    }

    /**
     * head是哨兵，总是添加到head
     */
    private void addHeadNode(Node keyNode) {
        keyNode.next = head.next;
        keyNode.prev = head;
        head.next.prev = keyNode;
        head.next = keyNode;
        size++;
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}
