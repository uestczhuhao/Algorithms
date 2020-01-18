package Algorithm4Edition.Chapter1._2_1.elementary.sorts;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-5 下午10:53
 */
public class ShellSort {
    public static void main(String[] args) {
        Integer[] q = {2, 1, 4, 5, 0};
        shellSort(q);
        System.out.println(Arrays.toString(q));
    }

    /**
     * 以length/3为初始间隔的希尔排序
     *
     * @param comparables 待排序数组
     */
    static void shellSort(Comparable[] comparables) {
        int len = comparables.length;
        int interval = 1;
        while (interval < len) {
            interval = interval * 3 + 1;
        }

        while (interval >= 1) {
            // 以下为间隔为interval的插入排序
            // 理由是插入排序对有序性比较好的数组排序性能比较好
            for (int i = interval; i < len; i++) {
                System.out.println("外层i " + i);
                for (int j = i; j >= interval; j -= interval) {
                    System.out.println("内层j " + j);
                    System.out.println("比较对象 " + j + " " + (j-interval));
                    if (comparables[j].compareTo(comparables[j - interval]) < 0) {
                        swap(comparables, j, j - interval);
                    }
                }
            }
            interval /= 3;
        }
    }

    private static void swap(Comparable[] a, int src, int tgt) {
        Comparable tmp = a[src];
        a[src] = a[tgt];
        a[tgt] = tmp;
    }
}
