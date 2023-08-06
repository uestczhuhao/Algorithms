package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _357 {
    public static void main(String[] args) {
        _357 t = new _357();
        System.out.println(t.finalString("poiinter"));
        List<Integer> a = new ArrayList<>(Arrays.asList(2, 3, 3, 2, 3));
        System.out.println(t.canSplitArray(a, 6));
    }

    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< s.length(); i ++) {
            if (s.charAt(i) == 'i') {
                sb.reverse();
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 核心：只要有一个相邻的元素和>=m，则满足题意，因为总能找到一种方式，把数组一个个的剥离，只剩这两个元素
     */
    public boolean canSplitArray(List<Integer> nums, int m) {
        if (nums.size() <= 2) {
            return true;
        }
        for (int i = 1; i< nums.size(); i++) {
            if (nums.get(i) + nums.get(i-1) >= m) {
                return true;
            }
        }
        return false;
    }
}
