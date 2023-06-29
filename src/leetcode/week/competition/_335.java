package leetcode.week.competition;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _335 {
    public static void main(String[] args) {
        _335 t = new _335();
//        System.out.println(t.passThePillow(4, 5));
//        System.out.println(t.passThePillow(3, 10));
//        System.out.println(t.passThePillow(3, 2));

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
//        System.out.println(t.kthLargestLevelSum(root, 2));
        System.out.println(t.gcd(9, 6));
        System.out.println(t.gcd(13, 37));
    }

//    public int findValidSplit(int[] nums) {
//
//    }

    private long gcd(long n1, long n2) {
        if (n1 < n2) {
            return gcd(n2, n1);
        }
        long remain = n1 % n2;
        return remain == 0 ? n2 : gcd(n2, remain);
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        if (k <= 0) {
            return -1;
        }
        PriorityQueue<Long> queue = new PriorityQueue<>(k);
        Queue<TreeNode> level = new LinkedList<>();
        level.offer(root);
        while (!level.isEmpty()) {
            long curLevelSum = 0;
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = level.poll();
                curLevelSum += node.val;
                if (node.left != null) {
                    level.offer(node.left);
                }
                if (node.right != null) {
                    level.offer(node.right);
                }
            }
            if (queue.size() < k || queue.peek() < curLevelSum) {
                queue.offer(curLevelSum);
            }
            while (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.size() < k ? -1 : queue.peek();
    }

    public int passThePillow(int n, int time) {
        int ans = 1;
        boolean forward = true;
        while (time > 0) {
            if (ans == n) {
                forward = false;
                ans = n - 1;
            } else if (ans == 1) {
                forward = true;
                ans = 2;
            } else {
                ans += forward ? 1 : -1;
            }
            time--;
        }
        return ans;
    }
}
