package AcWing;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2021/06/25 14:46
 * 题目背景
 * 原题cf13c 数据加强版
 * <p>
 * 题目描述
 * 给定一个序列，每次操作可以把某个数 +1 或 -1。要求把序列变成非降数列。而且要求修改后的数列只能出现修改前的数。
 * <p>
 * 输入格式
 * 第一行输入一个n，表示有n(n \leq 5*10^5n≤5∗10
 * 5
 * )个数字。第二行输入n个整数，整数的绝对值不超过10^910
 * 9
 * <p>
 * <p>
 * 输出格式
 * 输出一个数，表示最少的操作次数。
 * <p>
 * 输入输出样例
 * 输入 #1复制
 * 5
 * 3 2 -1 2 11
 * 输出 #1复制
 * 4
 * 输入 #2复制
 * 5
 * 2 1 1 1 1
 * 输出 #2复制
 * 1
 */
public class _4597序列sequence题解 {
    public static void main(String[] args) {
        _4597序列sequence题解 t = new _4597序列sequence题解();
        int[] nums = {3, 3, 3, 0};
//        int[] nums = {3, 2, -1, 2, 11};
        System.out.println(t.minOperateNum(nums));
    }

    public int minOperateNum(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        queue.offer(nums[0]);
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (!queue.isEmpty() && nums[i] < queue.peek()) {
                ans += queue.peek() - nums[i];
                queue.poll();
                queue.offer(nums[i]);
            }

            queue.offer(nums[i]);
        }
        return ans;
    }
}
