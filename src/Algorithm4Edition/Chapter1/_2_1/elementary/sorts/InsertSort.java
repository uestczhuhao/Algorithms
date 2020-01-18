package Algorithm4Edition.Chapter1._2_1.elementary.sorts;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-12 下午2:16
 */
public class InsertSort {
    public static void main(String[] args) {
        Integer[] arr = {4,5,12,3,1,2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void insertSort(Comparable[] comparables) {
        if (comparables.length <= 1) {
            return;
        }

        for (int i=1; i<comparables.length; i++) {
            for (int j=i; j>0; j--) {
                if (comparables[j].compareTo(comparables[j-1]) < 0) {
                    Comparable temp = comparables[j];
                    comparables[j] = comparables[j-1];
                    comparables[j-1] = temp;
                } else {
                    // 加速，由于前缀都是有序的
                    // j一旦不小于j-1，前面所有的元素都不比j大，可以安全退出
                    break;
                }
            }
        }
    }
}
