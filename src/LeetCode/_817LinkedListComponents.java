package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-23 下午4:43.
 * Description:
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。
 *
 * 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * 输出: 2
 * 解释:
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 *
 * 输入:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * 输出: 2
 * 解释:
 * 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 */
public class _817LinkedListComponents {
    public int numComponents(ListNode head, int[] G) {
        if (head == null) {
            return 0;
        }
        if (G == null || G.length == 0) {
            return 0;
        }

        // 键：G中的元素
        // 值：是否包含在链表中
        Set<Integer> valueSet = new HashSet<>();

        for (int value : G) {
            valueSet.add(value);
        }

        int subNum = 0;
        while (head != null) {
            boolean exist = false;
            while (head != null && valueSet.contains(head.val)) {
                exist = true;
                head = head.next;
            }

            if (!exist) {
                head = head.next;
                continue;
            }

            subNum++;
        }

        return subNum;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        int[] g = {1, 2, 4};

        _817LinkedListComponents t = new _817LinkedListComponents();
        System.out.println(t.numComponents(head, g));

    }
}
