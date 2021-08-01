package leetcode.week.competition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author mizhu
 * @date 2021/8/1 10:31
 */
public class _252 {
    public static void main(String[] args) {
        _252 t = new _252();
//        System.out.println(t.isThree(4));
//        System.out.println(t.isThree(13));

//        int[] miles = {9, 3, 6, 8, 2, 1};
        int[] miles = {3, 2, 1};
//        System.out.println(t.numberOfWeeks(miles));
//        System.out.println(t.minimumPerimeter(1000000000));
//        System.out.println(t.minimumPerimeter(13));
//        System.out.println(t.minimumPerimeter(1));
        int[] nums = {0,1,2,0,1,2};
//        System.out.println(t.countSpecialSubsequences(nums));

        String a = new String("abc");
        String b = a.intern();
        String c = "abc";
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
    }

    int ans = 0;

    public int countSpecialSubsequences(int[] nums) {
        dfs(nums, 0, new LinkedList<>());
        return ans;
    }

    private void dfs(int[] nums, int start, List<Integer> path) {
        if (path.size() >= 3) {
            if (valid(path)) {
                ans++;
            }
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    private boolean valid(List<Integer> path) {
        int pre = 0;
        Set<Integer> set = new HashSet<>();
        for (int p : path) {
            if (p < pre) {
                return false;
            }
            set.add(p);
            pre = p;
        }
        return set.size() == 3;
    }

    public long minimumPerimeter(long neededApples) {
        long sum = 12;
        long edge = 2;
        while (sum < neededApples) {
            edge += 2;
            sum = sum + (edge - edge / 2 + 1) * (edge + edge / 2) * 4 - 4 * (edge / 2);
        }
        return 4 * edge;
    }

    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        int max = 0;
        for (int mile : milestones) {
            max = Math.max(max, mile);
            sum += mile;
        }

        if (sum / 2 >= max) {
            return sum;
        } else {
            return (sum - max) * 2 + 1;
        }
    }

    public boolean isThree(int n) {
        if (n <= 2) {
            return false;
        }

        boolean exist = false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                if (exist) {
                    return false;
                } else {
                    exist = true;
                }
            }
        }
        return exist;
    }
}
