package leetcode;

import java.util.PriorityQueue;

/**
 * @author mizhu
 * @date 20-2-6 下午9:26
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */
public class _703kthLargestElement {
    public static void main(String[] args) {
        int k = 2;
        int[] arr = {0};
        _703kthLargestElement t = new _703kthLargestElement();
        KthLargest tt = t.new KthLargest(k, arr);
        System.out.println(tt.add(-1));
        System.out.println(tt.add(1));
        System.out.println(tt.add(-2));
        System.out.println(tt.add(-4));
        System.out.println(tt.add(3));
    }

    class KthLargest {

        PriorityQueue<Integer> smallTopKHeap;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            smallTopKHeap = new PriorityQueue<>(k);
            buildHeap(k, nums);
        }

        private void buildHeap(int k, int[] nums) {

            int i = 0;
            for (; i < k && i < nums.length; i++) {
                smallTopKHeap.add(nums[i]);
            }

            for (; i < nums.length; i++) {
                smallTopKHeap.add(nums[i]);
                smallTopKHeap.poll();
            }
        }

        public int add(int val) {
            smallTopKHeap.add(val);
            if (k < smallTopKHeap.size()) {
                smallTopKHeap.poll();
            }
            return smallTopKHeap.isEmpty() ? -1 : smallTopKHeap.peek();
        }
    }
}
