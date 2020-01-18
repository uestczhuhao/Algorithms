package Algorithm4Edition.Chapter1._1_4.analysis;

/**
 * Created by mizhu on 19-12-29 上午10:47
 */
public class _15TwoSumFaster {
    public static void main(String[] args) {
        int[] src = {-5, -1, 1, 2, 3, 4};
        System.out.println(do2SumFaster(src));
    }

    /**
     * 找出列表中和为0的组合数量，要求时间复杂度为n
     * @param src 输入的有序列表
     * @return
     */
    static int do2SumFaster(int[] src) {
        int count = 0;
        if (src == null || src.length == 0) {
            return count;
        }

        int head = 0, tail = src.length - 1;
        while (head < tail) {
            if (src[head] + src[tail] == 0) {
                count++;
                head++;
                tail--;
                continue;
            }

            if (src[head] + src[tail] > 0) {
                tail--;
            } else {
                head++;
            }
        }
        return count;
    }
}
