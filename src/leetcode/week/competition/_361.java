package leetcode.week.competition;

import java.util.Deque;
import java.util.LinkedList;

public class _361 {

    public static void main(String[] args) {
        _361 t = new _361();
//        System.out.println(t.countSymmetricIntegers(1200, 1230));
//        System.out.println(t.countSymmetricIntegers(1, 100));


    }




    public int minimumOperations(String num) {
        int n = num.length();
        int i = n - 1, j = n - 1;
        int[] fiveTail = {-1, n};
        int[] zeroTail = {-1, n};
        int ans = n;
        for (int k = 0; k < n; k++) {
            if (num.charAt(k) == '5') {
                i = k;
                fiveTail[1] = k;
            } else if (num.charAt(k) == '0') {
                ans = n - 1;
                j = k;
                zeroTail[1] = k;
            }
        }

        for (i = i - 1; i >= 0; i--) {
            if (num.charAt(i) == '2' || num.charAt(i) == '7') {
                fiveTail[0] = i;
                break;
            }
        }

        for (j = j - 1; j >= 0; j--) {
            if (num.charAt(j) == '0' || num.charAt(j) == '5') {
                zeroTail[0] = j;
                break;
            }
        }

        if (fiveTail[0] != -1 && fiveTail[1] != n) {
            ans = Math.min(ans, n - 2 - fiveTail[0]);
        }

        if (zeroTail[0] != -1 && zeroTail[1] != n) {
            ans = Math.min(ans, n - 2 - zeroTail[0]);
        }

        return ans;
    }

    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            if (i <= 10) {
                i = 10;
                continue;
            } else if (i >= 100 && i <= 1000) {
                i = 1000;
                continue;
            }
            if (i / 10 < 10) {
                ans += i / 10 == i % 10 ? 1 : 0;
            } else {
                int l = i % 100;
                int h = i / 100;
                ans += l / 10 + l % 10 == h / 10 + h % 10 ? 1 : 0;
            }
        }
        return ans;
    }
}
