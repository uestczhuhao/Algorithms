package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/3/28 20:43
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 * <p>
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 */
public class _862ShortestSubarraySumtLargerK {
    public static void main(String[] args) {
        int[] a = { 2};
        int k = 2;
        System.out.println(shortestSubarray(a, k));
    }

    /**
     * 1. 先求出前i项和sum
     * 2. 对sum数组，如果sum[i] < sum[i-1]，代表第i个数为负
     * 3. 初始化一个双端队列deque，存放数组下标（每个下标都放一次）
     * 4. 根据2，从deque中剔除为负的项i，因为子序列不可能以i开头，理由是若i为负，从i+1开始的话和更大，而且长度更短
     * 5. 遍历数组A的每一个位置，比较当前位置和deque的头部的距离，找出最小长度（条件是这部分的和大于等于K）
     *
     * 注意：
     * 1. 剔除负的部分和0
     * 2. sum数组要留个哨兵0，用以处理第一个满足条件的子序列长度（如前x项满足，其长度为x-0）
     */
    public static int shortestSubarray(int[] A, int K) {
        if (null == A || A.length == 0) {
            return -1;
        }

        int len = A.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            while (!deque.isEmpty() && sum[i] <= sum[deque.peekLast()]) {
                deque.removeLast();
            }

            // 比较当前位置与deque的首元素直接的差值（即这段下标直接的子数组和），看是否大于等于K
            // 若是，则记录这段子数组长度，并与当前的最短长度比较
            while (!deque.isEmpty() && sum[i] - sum[deque.peekFirst()] >= K) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            deque.addLast(i);
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;

    }

}
