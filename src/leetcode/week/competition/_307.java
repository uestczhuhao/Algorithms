package leetcode.week.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _307 {
    public static void main(String[] args) {
        _307 t = new _307();
        int[] energy = {1, 4, 3, 2};
        int[] experience = {2, 6, 3, 1};
//        System.out.println(t.minNumberOfHours(5, 3, energy, experience));
//        String num = "444947137";
//        String num = "0";
//        String num1 = "0000";
//        String num2 = "00000";
//        System.out.println(t.largestPalindromic(num));
//        System.out.println(t.largestPalindromic(num1));
//        System.out.println(t.largestPalindromic(num2));
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(10);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node3.left = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(t.amountOfTime(root, 3));
    }

    int ans = 0;
    // 开始节点的深度
    int startDepth = -1;

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, 0, start);
        return ans;
    }

    // dfs求树的深度，同时求从start感染全树的最小时间ans
    private int dfs(TreeNode root, int level, int start) {
        if (root == null) {
            return 0;
        }
        if (root.val == start) {
            startDepth = level;
        }
        int leftDepth = dfs(root.left, level + 1, start);
        // 先遍历左子树，若此时已经设置了startDepth，则代表start在root的左子树
        boolean inLeft = startDepth != -1;
        int rightDepth = dfs(root.right, level + 1, start);
        // 1. 以start为根，感染需要的时间
        if (root.val == start) {
            ans = Math.max(ans, Math.max(leftDepth, rightDepth));
        }
        // 2. 不以start为根，感染需要的时间，取决于start在root的左子树还是右子树
        // 若在左，则感染时间为root到start的距离+root的右子树的高度；若在右，则感染时间为root到start的距离+root的左子树的高度
        // 注意：第二个分支在root为start的上层节点时可能取到
        if (inLeft) {
            ans = Math.max(ans, startDepth - level + rightDepth);
        } else {
            ans = Math.max(ans, startDepth - level + leftDepth);
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int amountOfTime1(TreeNode root, int start) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        TreeNode beginNode = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.val == start) {
                    beginNode = cur;
                }
                graph.putIfAbsent(cur, new ArrayList<>());
                if (cur.left != null) {
                    queue.offer(cur.left);
                    graph.putIfAbsent(cur.left, new ArrayList<>());
                    graph.get(cur).add(cur.left);
                    graph.get(cur.left).add(cur);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    graph.putIfAbsent(cur.right, new ArrayList<>());
                    graph.get(cur).add(cur.right);
                    graph.get(cur.right).add(cur);
                }
            }
        }
        Set<TreeNode> visited = new HashSet<>();
        visited.add(beginNode);
        int ans = -1;
        queue = new LinkedList<>();
        queue.offer(beginNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                visited.add(cur);
                for (TreeNode next : graph.get(cur)) {
                    if (!visited.contains(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return ans;
    }


    public String largestPalindromic(String num) {
        int[] digitNum = new int[10];
        for (int i = 0; i < num.length(); i++) {
            digitNum[num.charAt(i) - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        int max = -1;
        for (int i = 9; i >= 0; i--) {
            if (digitNum[i] != 0) {
                for (int j = 0; j < digitNum[i] / 2; j++) {
                    sb.append(i);
                }
                if (digitNum[i] % 2 == 1 && max == -1) {
                    max = i;
                }
            }
        }
        if (max != -1) {
            sb.append(max);
        }
        int start = 0;
        while (start < sb.length() && sb.charAt(start) == '0') {
            start++;
        }
        int end = max == -1 ? sb.length() - 1 : sb.length() - 2;
        StringBuilder ans = new StringBuilder();
        ans.append(sb.substring(start));
        while (end >= start) {
            ans.append(sb.charAt(end));
            end--;
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; i++) {
            if (initialEnergy <= energy[i]) {
                int add = energy[i] - initialEnergy + 1;
                initialEnergy += add;
                ans += add;
            }
            initialEnergy -= energy[i];

            if (initialExperience <= experience[i]) {
                int add = experience[i] - initialExperience + 1;
                initialExperience += add;
                ans += add;
            }
            initialExperience += experience[i];
        }
        return ans;
    }
}
