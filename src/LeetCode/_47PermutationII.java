package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/4/18 21:34
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class _47PermutationII {

    public static void main(String[] args) {
        _47PermutationII t = new _47PermutationII();
        int[] n = {1, 2, 1};
        System.out.println(t.permuteUnique(n));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (null == nums || nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        doPermute(result, new ArrayList<>(), nums, visited);
        return result;
    }

    public void doPermute(List<List<Integer>> permutation, List<Integer> curPermute, int[] nums, boolean[] visited) {
        if (curPermute.size() == nums.length) {
            permutation.add(new ArrayList<>(curPermute));
            return;
        }

        // https://blog.csdn.net/nicolelili1/article/details/89043733
        for (int i = 0;i <nums.length; i++) {
            /* 注意visited[i-1]代表着i和i-1相等，但是i-1还未被访问
             则表示i比i-1先被访问，这是不允许的
             如：{1，1，2} 在全排列时，可以有1A 1B 2，但不能有1B 1A 2
             第一种表示i-1比i先访问，第二种表示i先被访问
             所以当存在visited[i-1] && nums[i] == nums[i-1]时，直接跳过即可
            */
            if (visited[i] || (i > 0 && nums[i] == nums[i-1] && !visited[i-1])) {
                continue;
            }

            visited[i] = true;
            curPermute.add(nums[i]);
            doPermute(permutation, curPermute, nums, visited);
            curPermute.remove(curPermute.size()-1);
            visited[i] = false;
        }
    }
}
