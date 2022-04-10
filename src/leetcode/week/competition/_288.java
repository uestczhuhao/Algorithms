package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class _288 {
    public static void main(String[] args) {
        _288 t = new _288();
//        System.out.println(t.largestInteger(65875));
//        System.out.println(t.largestInteger(1234));
//        System.out.println(t.largestInteger(45899));
//        System.out.println(t.largestInteger(3159));
        System.out.println(t.minimizeResult("65+6"));
        System.out.println(t.minimizeResult("12+34"));
        System.out.println(t.minimizeResult("247+38"));
        System.out.println(t.minimizeResult("999+999"));
//        int[] nums = {6, 3, 3, 2};
//        System.out.println(t.maximumProduct(nums, 2));

    }

    public int maximumProduct(int[] nums, int k) {
        long ans = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        while (k > 0) {
            queue.offer(queue.poll() + 1);
            k--;
        }
        while (!queue.isEmpty()) {
            Integer curNum = queue.poll();
            ans *= curNum;
            if (ans > 1000000007) {
                ans %= 1000000007;
            }
        }
        return (int) ans;
    }

    public int largestInteger(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        boolean[] oddDigit = new boolean[digits.length];
        List<Character> odd = new ArrayList<>();
        List<Character> even = new ArrayList<>();
        for (int i = 0; i < digits.length; i++) {
            int cur = digits[i] - '0';
            if (cur % 2 == 0) {
                even.add(digits[i]);
            } else {
                odd.add(digits[i]);
                oddDigit[i] = true;
            }
        }
        odd.sort(Collections.reverseOrder());
        even.sort(Collections.reverseOrder());
        int oIndex = 0, eIndex = 0;
        for (int i = 0; i < digits.length; i++) {
            if (!oddDigit[i]) {
                digits[i] = even.get(eIndex++);
            } else {
                digits[i] = odd.get(oIndex++);
            }
        }

        return Integer.parseInt(new String(digits));
    }


    public String minimizeResult(String expression) {
        int opIndex = expression.indexOf('+');
        char[] left = expression.substring(0, opIndex).toCharArray();
        char[] right = expression.substring(opIndex + 1).toCharArray();
        int min = Integer.MAX_VALUE;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < left.length; i++) {
            int left1 = convert2Int(left, 0, i - 1);
            int left2 = convert2Int(left, i, left.length - 1);
            for (int j = right.length - 1; j >= 0; j--) {
                int right1 = convert2Int(right, 0, j);
                int right2 = convert2Int(right, j + 1, right.length - 1);
                if (left1 * (left2 + right1) * right2 < min) {
                    min = left1 * (left2 + right1) * right2;
                    ans = new StringBuilder();
                    ans.append(new String(left), 0, i).append("(").append(new String(left), i, left.length)
                        .append("+").append(new String(right), 0, j + 1).append(")").append(new String(right), j + 1, right.length);
                }
            }
        }
        return ans.toString();
    }

    private int convert2Int(char[] chs, int start, int end) {
        if (start > end) {
            return 1;
        }
        return Integer.parseInt(new String(chs, start, end - start + 1));
    }

}
