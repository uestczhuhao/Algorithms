package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author mizhu
 * @date 20-2-4 下午9:32
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 */
public class _295FindMedianFromDataStream {
    public static void main(String[] args) {
        _295FindMedianFromDataStream t = new _295FindMedianFromDataStream();
        MedianFinder medianFinder = t.new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());

    }

    class MedianFinder {

        /**
         * 采用双堆法，一个大顶堆存放较小的一半数，一个小顶堆存放较大的一半数
         * 注意：大顶堆的规模小于等于小顶堆，但规模最多差1（当size为奇数时）
         */
        private PriorityQueue<Integer> largerMinHeap;
        private PriorityQueue<Integer> smallerMaxHeap;
        private int size;

        private final double DEFAULT_MEDIAN = 0.0;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            largerMinHeap = new PriorityQueue<>();
            smallerMaxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            size = 0;
        }

        public void addNum(int num) {
            if (size > 1) {
                // 此时smallerMaxHeap不为空
                assert !smallerMaxHeap.isEmpty();
                int smallerMax = smallerMaxHeap.peek();
                if (num < smallerMax) {
                    smallerMaxHeap.offer(num);
                } else {
                    largerMinHeap.offer(num);
                }
                adjustHeap();
            } else {
                largerMinHeap.offer(num);
                adjustHeap();
            }
            size ++;
            // 方法2，代码更简洁，但更耗时，理由是比较比log(n) 的offer+poll更快
//            largerMinHeap.offer(num);
//            smallerMaxHeap.offer(largerMinHeap.poll());
//            adjustHeap();
//            size ++;
        }

        private void adjustHeap() {
            while (smallerMaxHeap.size() > largerMinHeap.size()) {
                largerMinHeap.offer(smallerMaxHeap.poll());
            }
            while (!largerMinHeap.isEmpty() && largerMinHeap.size() - smallerMaxHeap.size() > 1) {
                smallerMaxHeap.offer(largerMinHeap.poll());
            }
        }

        public double findMedian() {
            if (size % 2 == 0) {
                double smallMid = smallerMaxHeap.isEmpty() ? DEFAULT_MEDIAN : smallerMaxHeap.peek();
                double largeMid = largerMinHeap.isEmpty() ? DEFAULT_MEDIAN : largerMinHeap.peek();
                return 1.0 * (smallMid + largeMid) / 2;
            } else {
                return largerMinHeap.isEmpty() ? DEFAULT_MEDIAN : largerMinHeap.peek();
            }
        }
    }
}
