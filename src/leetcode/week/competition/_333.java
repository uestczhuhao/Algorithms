package leetcode.week.competition;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _333 {
    public static void main(String[] args) {
        _333 t = new _333();
        int[][] nums1 = {{1, 2}, {2, 3}, {4, 5}, {5, 1}, {6, 1}, {9, 1}};
        int[][] nums2 = {{1, 4}, {3, 2}, {4, 1}};
//        System.out.println(Arrays.deepToString(t.mergeArrays(nums1, nums2)));
//        System.out.println(t.minOperations(39));
//        System.out.println(t.minOperations(54));
//        System.out.println(t.minOperations(7));
//        System.out.println(t.minOperations(9));
//        System.out.println(t.minOperations(8));
        int cnt = 0;
        for (long i = 2; i * i < Integer.MAX_VALUE; i++) {
            cnt++;
            System.out.print(i * i + " ");
        }
        System.out.println();
        System.out.println(cnt);
    }

    /**
     * 一次循环可搞定
     */
    public int minOperations(int n) {
        int ans = 0, cnt = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                cnt++;
            } else {
                // 当前位不是0，则判断之前的连续1的个数
                // 如果是1，答案直接+1
                if (cnt == 1) {
                    ans++;
                    cnt = 0;
                } else if (cnt > 1) {
                    // 如果>1，则答案先+1，同时把计数器+1（表示+1后连续的多个1变成了更高位的1个1）
                    ans++;
                    cnt = 1;
                }
            }
            n >>= 1;
        }
        if (cnt == 1) {
            ans += 1;
        } else if (cnt > 1) {
            ans += 2;
        }
        return ans;
    }

    public int minOperations1(int n) {
        int[] bits = new int[32];
        int i = bits.length - 1;
        while (n > 0) {
            int t = n % 2;
            bits[i--] = t;
            n /= 2;
        }
        int ans = 0;
        boolean continueFlag = true;
        while (continueFlag) {
            continueFlag = false;
            for (i = bits.length - 1; i >= 0; i--) {
                if (bits[i] == 0) {
                    continue;
                }
                if (i - 1 >= 0 && bits[i - 1] == 1) {
                    ans++;
                    while (i - 1 >= 0 && bits[i - 1] == 1) {
                        continueFlag = true;
                        bits[i] = 0;
                        i--;
                    }
                    bits[i] = 0;
                    if (i > 0) {
                        bits[i - 1] = 1;
                    }
                }
            }
        }

        for (i = 0; i < bits.length; i++) {
            ans += bits[i];
        }

        return ans;
    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int i1 = 0, i2 = 0;
        LinkedList<int[]> ans = new LinkedList<>();
        while (i1 < nums1.length && i2 < nums2.length) {
            int[] pre = ans.isEmpty() ? new int[] {0, 0} : ans.getLast();
            if (nums1[i1][0] == nums2[i2][0]) {
                ans.add(new int[] {nums1[i1][0], nums1[i1][1] + nums2[i2][1]});
                i1++;
                i2++;
            } else if (nums1[i1][0] < nums2[i2][0]) {
                if (pre[0] == nums1[i1][0]) {
                    pre[1] += nums1[i1][1];
                } else {
                    ans.add(new int[] {nums1[i1][0], nums1[i1][1]});
                }

                i1++;
            } else {
                if (pre[0] == nums2[i2][0]) {
                    pre[1] += nums2[i2][1];
                } else {
                    ans.add(new int[] {nums2[i2][0], nums2[i2][1]});
                }
                i2++;
            }
        }

        while (i1 < nums1.length) {
            ans.add(new int[] {nums1[i1][0], nums1[i1][1]});
            i1++;
        }

        while (i2 < nums2.length) {
            ans.add(new int[] {nums2[i2][0], nums2[i2][1]});
            i2++;
        }
        return ans.toArray(new int[][] {});
    }
}
