package leetcode.week.competition;

import java.util.*;

public class _270 {
    public static void main(String[] args) {
        _270 t = new _270();
        int[] digits = {1, 0, 0};
//        System.out.println(Arrays.toString(t.findEvenNumbers(digits)));
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
//        node1.next = node2;
        //node2.next=node3;
        //node3.next=node4;
//        t.print(node1);
//        t.print(t.deleteMiddle(node1));
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(4);
        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
//        treeNode2.left = treeNode4;
//        treeNode3.left = treeNode5;
//        treeNode3.right = treeNode6;
        System.out.println(t.getDirections(treeNode1, 5, 1));
    }


    String answer;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = lowestCommonAncestor(root, startValue, destValue);
        int levelNum = countLevel(lca, startValue);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < levelNum - 1; i++) {
            sb.append('U');
        }

        findPath2Dst(lca, destValue, sb);
        return answer;
    }

    private void findPath2Dst(TreeNode ancestor, int targetValue, StringBuilder sb) {
        if (ancestor.val == targetValue) {
            answer = sb.toString();
            return;
        }
        if (ancestor.left != null) {
            sb.append('L');
            findPath2Dst(ancestor.left, targetValue, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (ancestor.right != null) {
            sb.append('R');
            findPath2Dst(ancestor.right, targetValue, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private int countLevel(TreeNode ancestor, int targetValue) {
        Queue<TreeNode> nextLevel = new LinkedList<>();
        nextLevel.offer(ancestor);
        int result = 0;
        while (!nextLevel.isEmpty()) {
            result++;
            Queue<TreeNode> curLevel = nextLevel;
            nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                TreeNode node = curLevel.poll();
                if (node.val == targetValue) {
                    return result;
                }
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
            }
        }

        return result;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int node1, int node2) {
        if (root == null) {
            return null;
        }

        // 找到的值会向上传递，直到LCA被找到为止
        if (root.val == node1 || root.val == node2) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public ListNode deleteMiddle(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode slow = preHead;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        delNextNode(slow);
        return preHead.next;
    }

    private void delNextNode(ListNode node) {
        ListNode next = node.next;
        node.next = next.next;
        next.next = null;
    }

    private void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> evenNumberSet = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                for (int k = j + 1; k < digits.length; k++) {
                    if (digits[i] % 2 == 0) {
                        doAddEvenSet(digits, j, k, i, evenNumberSet);
                    }
                    if (digits[j] % 2 == 0) {
                        doAddEvenSet(digits, k, i, j, evenNumberSet);
                    }
                    if (digits[k] % 2 == 0) {
                        doAddEvenSet(digits, i, j, k, evenNumberSet);
                    }
                }
            }
        }
        Integer[] answer = new ArrayList<>(evenNumberSet).toArray(new Integer[0]);
        int[] a = Arrays.stream(answer).mapToInt(Integer::valueOf).toArray();
        Arrays.sort(a);
        return a;
    }

    private void doAddEvenSet(int[] digits, int i, int j, int end, Set<Integer> evenNumberSet) {
        int ans1 = digits[i] * 100 + digits[j] * 10 + digits[end];
        int ans2 = digits[j] * 100 + digits[i] * 10 + digits[end];
        if (ans1 >= 100) {
            evenNumberSet.add(ans1);
        }
        if (ans2 >= 100) {
            evenNumberSet.add(ans2);
        }
    }
}
