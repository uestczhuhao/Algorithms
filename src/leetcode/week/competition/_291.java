package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _291 {
    public static void main(String[] args) {
        _291 t = new _291();
        String a = "2998589353917872714814599237991174513476623756395992135212546127959342974628712329595771672911914471";
//        System.out.println(t.removeDigit(a, '2'));
        int[] cards = {3, 4, 2};
//        System.out.println(t.minimumCardPickup(cards));
        int[] nums = {2, 3, 3, 2, 2};
        int[] nums1 = {1,2,3,4};
//        System.out.println(t.countDistinct(nums1, 4, 1));
        System.out.println(t.appealSum("abbca"));
    }

    /**
     * 将所有子串按照其末尾字符的下标分组。
     * 考虑两组相邻的子串：以 s[i-1] 结尾的子串、以 s[i] 结尾的子串。
     * 以 s[i] 结尾的子串，可以看成是以 s[i−1] 结尾的子串，在末尾添加上 s[i] 组成。
     *
     */
    public long appealSum(String s) {
        long ans = 0L;
        // pos记录每个字符最后一次出现的地方。
        // 每遍历到一个字符，计算以该下标结尾会为最后的答案贡献的引力值。
        // pos[c] < 0 ,前面的 i + 1 串字符都会收益（包括第i个字符自己组成的子串）， 不然就只有i - pos[c];
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        // sum为每次以i为结尾的子串的引力值之和
        long sum = 0;
        for (int i = 0; i< s.length();i++) {
            int curIndex = s.charAt(i) - 'a';
            sum += i - pos[curIndex];
            ans += sum;
            pos[curIndex] = i;
        }
        return ans;
    }

    public int countDistinct(int[] nums, int k, int p) {
        int len = nums.length;
        HashSet<List<Integer>> set = new HashSet<>();
        boolean[] canDivide = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] % p == 0) {
                canDivide[i] = true;
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int curSub = 0;
            for (int j = i; j < len; j++) {
                curSub += canDivide[j] ? 1 : 0;
                if (curSub > k) {
                    break;
                }

                List<Integer> subArr = Arrays.stream(Arrays.copyOfRange(nums, i, j + 1)).boxed().collect(Collectors.toList());
                if (!set.contains(subArr)) {
                    ans++;
                }
                set.add(subArr);
            }
        }
        return ans;
    }

    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            int card = cards[i];
            if (indexMap.containsKey(card)) {
                min = Math.min(min, i - indexMap.get(card) + 1);
            }
            indexMap.put(card, i);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public String removeDigit(String number, char digit) {
        String max = "";
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                String cur = number.substring(0, i) + number.substring(i + 1);
                max = bigger(max, cur) ? max : cur;
            }
        }
        return max;
    }

    private boolean bigger(String source, String target) {
        if (source.length() != target.length()) {
            return source.length() > target.length();
        } else {
            for (int i = 0; i < source.length(); i++) {
                if (source.charAt(i) > target.charAt(i)) {
                    return true;
                } else if (source.charAt(i) < target.charAt(i)) {
                    return false;
                }
            }
        }
        return false;
    }
}
