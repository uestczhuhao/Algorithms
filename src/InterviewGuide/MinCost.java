package InterviewGuide;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 题目：https://leetcode.cn/circle/discuss/DBfim2/
 * 给你一个长度为 2n 的数组 data，data[i] 表示一台服务器的 capacity。
 * 其中每两台服务器可以凑成一对，现在要关闭每一对中的一台服务器。
 *      关闭其中 capacity 小的服务器，会使 cost 加 1，关闭大的没有 cost。
 *
 * 给你数组 data 和 int k，要求返回在关闭服务器后，还在运行的服务器的 capacity 之和大于等于 k 时的 最小 cost。
 *
 * data = [1, 2, 3, 5, 7, 8]    k = 14
 * result: [1, 8] [2, 3] [5, 7] 关闭 1, 3, 7。 cost = 1
 */
public class MinCost {
    public static void main(String[] args) {
        MinCost min = new MinCost();
        int[] cost = {1, 2, 3, 5, 7, 8};
        System.out.println(min.minCost(cost, 18));
    }

    public int minCost(int[] capacity, int k) {
        Arrays.sort(capacity);
        //const == 0关闭最大的，最佳匹配方式从右至左找到第一个比其小且未被使用的服务器
        int sumCapacity = 0;
        //考虑capacity存在相同的情况,单调栈维护右边比其大且未被使用的服务器，如果capacity都不相同排序相邻配对
        int[] preSum = new int[capacity.length + 1];
        for (int i = 1; i <= capacity.length; i++) {
            sumCapacity += capacity[i - 1];
            preSum[i] = sumCapacity;
        }
        //二分枚举cost,最大的拿出一台服务器和最小的去匹配
        int l = 0, r = capacity.length / 2;
        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (sumCapacity - closeMax(mid, capacity, preSum[mid]) >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return  l;
    }

    //关闭大的服务器
    public int closeMax(int cost, int[] capacity, int close) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        //考虑capacity存在相同的情况,单调栈维护右边比其大且未被使用的服务器，如果capacity不相同排序相邻配对
        for (int i = capacity.length - 1 - cost; i >= cost; i--) {
            boolean pop = false;
            if (!stack.isEmpty() && stack.peek() > capacity[i]) {
                close += stack.pop();
                pop = true;
            }
            if (!pop) {
                stack.push(capacity[i]);
            }
        }
        return close;
    }
}
