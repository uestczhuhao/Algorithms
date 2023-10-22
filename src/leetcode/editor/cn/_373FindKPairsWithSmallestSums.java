package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _373FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        Solution t = new _373FindKPairsWithSmallestSums().new Solution();
        int[] n1 = {1, 7, 11};
        int[] n2 = {2, 4, 6};
        System.out.println(t.kSmallestPairs(n1, n2, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 多路归并的方式，假设nums1的长度为n，nums2的长度为m，数对 (i, j) 表示取num1中的第i个和num2中的第j个
         * 观察 (0, 0)  (1, 0)  (2, 0) ... (n-1, 0)
         * (0, 1)  (1, 1)  (2, 1) ... (n-1, 1)
         * ... ...
         * (0, m-1)  (1, m-1)  (2, m-1) ... (n-1, m-1)
         * 上面的每一行都是有序的序列，把他们合并成一个，取第k个就是答案（从上到下也是有序的）
         * 注意：每次当(i,j)满足条件弹出时，就取 (i, j+1)，因为后者在上面的数对中，正好在(i,j)的正下方
         */
        boolean flag = true;

        public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
            // 保证n <= m
            if (nums1.length > nums2.length) {
                int[] tmp = nums1;
                nums1 = nums2;
                nums2 = tmp;
                flag = false;
            }

            int n = nums1.length, m = nums2.length;
            // 优先队列，存放题解中的数对(i, j)
            int[] finalNums1 = nums1;
            int[] finalNums2 = nums2;
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (finalNums1[a[0]] + finalNums2[a[1]])));
            // 先存入第一行的数对
            for (int i = 0; i < Math.min(n, k); i++) {
                queue.add(new int[] {i, 0});
            }
            List<List<Integer>> ans = new ArrayList<>();
            while (ans.size() < k && !queue.isEmpty()) {
                int[] curMin = queue.poll();
                List<Integer> cur = new ArrayList<>();
                cur.add(flag ? nums1[curMin[0]] : nums2[curMin[1]]);
                cur.add(flag ? nums2[curMin[1]] : nums1[curMin[0]]);
                ans.add(cur);
                if (curMin[1] < m - 1) {
                    queue.offer(new int[] {curMin[0], curMin[1] + 1});
                }
            }
            return ans;
        }


        int[] nums1, nums2;
        int n, m;

        /**
         * 二分法，题解：https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solutions/1209848/gong-shui-san-xie-duo-lu-gui-bing-yun-yo-pgw5/
         */
        public List<List<Integer>> kSmallestPairs(int[] n1, int[] n2, int k) {
            nums1 = n1;
            nums2 = n2;
            n = nums1.length;
            m = nums2.length;
            List<List<Integer>> ans = new ArrayList<>();
            int l = nums1[0] + nums2[0], r = nums1[n - 1] + nums2[m - 1];
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                if (check(mid, k)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            int x = r;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (nums1[i] + nums2[j] < x) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums1[i]);
                        temp.add(nums2[j]);
                        ans.add(temp);
                    } else {
                        break;
                    }
                }
            }
            for (int i = 0; i < n && ans.size() < k; i++) {
                int a = nums1[i], b = x - a;
                int c, d;
                l = 0;
                r = m - 1;
                while (l < r) {
                    int mid = l + ((r - l) >> 1);
                    if (nums2[mid] >= b) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                if (nums2[r] != b) {
                    continue;
                }
                c = r;
                l = 0;
                r = m - 1;
                while (l < r) {
                    int mid = l + ((r - l + 1) >> 1);
                    if (nums2[mid] <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                d = r;
                for (int p = c; p <= d && ans.size() < k; p++) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(a);
                    temp.add(b);
                    ans.add(temp);
                }
            }
            return ans;
        }

        // 查看nums1和nums2中数对小于等于x的个数是否小于等于k
        boolean check(int x, int k) {
            int ans = 0;
            for (int i = 0; i < n && ans < k; i++) {
                for (int j = 0; j < m && ans < k; j++) {
                    if (nums1[i] + nums2[j] <= x) {
                        ans++;
                    } else {
                        break;
                    }
                }
            }
            return ans >= k;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
