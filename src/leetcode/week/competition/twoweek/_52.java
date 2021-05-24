package leetcode.week.competition.twoweek;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2021/5/24 20:09
 */
public class _52 {
    public static void main(String[] args) {
        _52 t = new _52();
//        System.out.println(t.sortSentence("Myself2 Me1 I4 and3"));
//        System.out.println(Arrays.toString(t.memLeak(8, 11)));
        char[][] box = {{'#', '#', '*', '.', '*', '.'},
            {'#', '#', '#', '*', '.', '.'},
            {'#', '#', '#', '.', '#', '.'}};

//        System.out.println(Arrays.deepToString(t.rotateTheBox(box)));
        int[] nums = {2, 5, 9};
        System.out.println(t.sumOfFlooredPairs(nums));
    }

    public int sumOfFlooredPairs(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        // 存放num出现的次数
        int[] count = new int[100001];
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
            count[num]++;
        }

        // 存放小于等于num的前缀和
        int[] sum = new int[100001];
        for (int i = 1; i <= maxValue; i++) {
            sum[i] += sum[i - 1] + count[i];
        }

        long ans = 0;
        for (int i = 1; i <= maxValue; i++) {
            // 判断i出现的频率
            int fre = count[i];
            if (fre == 0) {
                continue;
            }

            for (int j = i; j <= maxValue; j += i) {
                // j ~ j+i-1 之间的区间个数
                int bucketNum = sum[Math.min(i + j - 1, maxValue)] - sum[j - 1];
                // 区间个数 * i出现的次数 * 当前区间除以i的整数部分
                ans += (bucketNum * fre * (j / i) % 1000000007);
            }
        }
        return (int) ans;
    }


    public char[][] rotateTheBox(char[][] box) {
        char[][] ans = new char[box[0].length][box.length];
        for (char[] an : ans) {
            Arrays.fill(an, '.');
        }
        for (int i = 0; i < box.length; i++) {
            int stone = 0;
            int j = 0;
            for (; j < box[0].length; j++) {
                if (box[i][j] == '#') {
                    stone++;
                }

                // 遇到障碍或到底，可以填充ans了
                if (box[i][j] == '*') {
                    fillBox(ans, j, box.length - 1 - i, stone, '*');
                    stone = 0;
                }
            }
            if (stone != 0) {
                fillBox(ans, j, box.length - 1 - i, stone, ' ');
            }
        }
        return ans;
    }

    private void fillBox(char[][] ans, int row, int col, int stone, char ch) {
        if (ch == '*') {
            ans[row][col] = ch;
        }

        while (stone > 0) {
            ans[row - stone][col] = '#';
            stone--;
        }
    }

    public int[] memLeak(int memory1, int memory2) {
        int max = Math.max(memory1, memory2);
        int[] ans = new int[]{-1, memory1, memory2};
        for (int i = 1; i <= max + 1; i++) {
            if (i > memory1 && i > memory2) {
                ans = new int[]{i, memory1, memory2};
                return ans;
            }
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
        }
        return ans;
    }

    public String sortSentence(String s) {
        String[] strings = s.split(" ");
        String[] ans = new String[strings.length];
        int index;
        String cur;
        for (String string : strings) {
            index = 0;
            cur = string;
            int j = cur.length() - 1;
            for (; j >= 0; j--) {
                if (!Character.isDigit(cur.charAt(j))) {
                    break;
                }
                index = index * 10 + cur.charAt(j) - '0';
            }
            cur = cur.substring(0, j + 1);
            ans[index - 1] = cur;
        }

        return String.join(" ", ans);
    }
}