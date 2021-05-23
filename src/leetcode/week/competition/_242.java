package leetcode.week.competition;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author mizhu
 * @date 2021/5/23 10:31
 */
public class _242 {
    public static void main(String[] args) {
        _242 t = new _242();
//        System.out.println(t.checkZeroOnes("110100010"));
        int[] dist = {1, 1, 100000};
        double hour = 2.01;
//        System.out.println(t.minSpeedOnTime(dist, hour));
//        System.out.println(t.canReach(dist,100002, hour));
        String s = "01000";
        int min = 2;
        int max = 3;
        System.out.println(t.canReach(s, min, max));
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        int size = s.length();
        if (s.charAt(size - 1) != '0') {
            return false;
        }

        int furthest = 0;
        boolean[] reach = new boolean[s.length()];
        reach[0] = true;
        for (int i = 0; i < s.length(); i++) {
            // 第二遍访问，挨个访问
            if (reach[i]) {
                // 更新左右边界
                int left = Math.max(i + minJump, furthest), right = Math.min(i + maxJump, s.length() - 1);
                // 第一遍更新
                while (left <= right) {
                    if (s.charAt(left) == '1') {
                        left++;
                        continue;
                    }
                    reach[left++] = true;
                }
                // 当访问i时，最远能i + maxJump 左边的所有元素都已经处理过了
                // 下次从furthest的右边开始更新即可，保证每个元素被更新一次
                furthest = Math.max(furthest, i + maxJump) + 1;
            }

        }

        return reach[s.length() - 1];

    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int hourInt = (int) Math.ceil(hour);
        if (hourInt < dist.length) {
            return -1;
        }

        int right = dist[0];
        int max = dist[0];
        for (int i = 1; i < dist.length; i++) {
            right += dist[i];
            max = Math.max(max, dist[i]);
        }

        right = Math.max(right, (int) (max / (hour - Math.floor(hour))));

        int left = 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (speedValid(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean speedValid(int[] dist, int speed, double hour) {
        double time = 0;
        int len = dist.length;
        // 等效向上取整，但是会快很多
        for (int i = 0; i < len - 1; i++) {
            time += (dist[i] + speed - 1) / speed;
        }
        time += 1.0 * dist[len - 1] / speed;
        return time <= hour;
    }

    public boolean checkZeroOnes(String s) {
        int oneLen = 0;
        int zeroLen = 0;
        int curOne = 0, curZero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                curOne = 0;
                curZero = 0;
            }

            if (s.charAt(i) == '1') {
                curOne++;
            } else {
                curZero++;
            }

            oneLen = Math.max(curOne, oneLen);
            zeroLen = Math.max(zeroLen, curZero);

        }

        return oneLen > zeroLen;
    }

}
