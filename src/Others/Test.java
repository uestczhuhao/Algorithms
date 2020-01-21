package Others;

import java.util.Arrays;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-21 下午7:00.
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        int[] paragraphSentence = {3, 2, 5, 1, 4};
        int[] stringSentence = {2, 1, 3, 4, 2, 3};

        System.out.println(Arrays.toString(findSentenceLocation(paragraphSentence, stringSentence)));
    }

    static LocationAuxiliary[] findSentenceLocation(int[] src, int[] dst) {
        if (null == src
                || null == dst) {
            return null;
        }
        int[] paragTotal = new int[src.length + 1];
        int[] strTotal = new int[dst.length + 1];
        paragTotal[0] = 0;
        strTotal[0] = 0;
        for (int i = 1; i < paragTotal.length; i++) {
            paragTotal[i] = src[i - 1] + paragTotal[i - 1];
        }

        for (int i = 1; i < strTotal.length; i++) {
            strTotal[i] = dst[i - 1] + strTotal[i - 1];
        }

        LocationAuxiliary[] sentenceLocation = new LocationAuxiliary[dst.length];

        for (int i = 1; i < strTotal.length; i++) {
            int targetNum = strTotal[i - 1];
            for (int j = 0; j < paragTotal.length; j++) {
                // 找到第一个大于目标值的位置
                if (paragTotal[j] > targetNum) {
                    int offsetSentence = paragTotal[j - 1];
                    LocationAuxiliary temp = new LocationAuxiliary(j, targetNum - offsetSentence);
                    sentenceLocation[i - 1] = temp;
                    break;
                }
            }
        }
        return sentenceLocation;
    }

    private static class LocationAuxiliary {
        int paragraph;
        int startLocation;

        public LocationAuxiliary(int paragraph, int startLocation) {
            this.paragraph = paragraph;
            this.startLocation = startLocation;
        }

        @Override
        public String toString() {
            return paragraph + " " + startLocation;
        }
    }

}
