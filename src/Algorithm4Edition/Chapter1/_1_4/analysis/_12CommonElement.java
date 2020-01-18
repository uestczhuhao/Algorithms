package Algorithm4Edition.Chapter1._1_4.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mizhu on 19-12-29 上午10:31
 */
public class _12CommonElement {
    public static void main(String[] args) {

        int[] src = {1,1,1};
        int[] tgt = {1,1,1};
        System.out.println(commonElements(src, tgt));
    }

    /**
     * 1. 时间复杂度为n
     * 2. 输出两个有序数组的所有公共元素
     */
    static List<Integer> commonElements(int[] src, int[] tgt) {
        List<Integer> commons = new ArrayList<>();
        if (src.length ==0 || tgt.length ==0){
            return commons;
        }
        int sIndex = 0, tIndex = 0;
        while (sIndex < src.length && tIndex < tgt.length) {
            if (src[sIndex] == tgt[tIndex]) {
                commons.add(src[sIndex]);
                sIndex++;
                tIndex++;
                continue;
            }

            if (src[sIndex] < tgt[tIndex]) {
                sIndex ++;
            } else {
                tIndex ++;

            }
        }
        return commons;
    }
}
