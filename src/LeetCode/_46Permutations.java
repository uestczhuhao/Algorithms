package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mizhu
 * @date 2020/4/18 20:58
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class _46Permutations {

    public static void main(String[] args) {
        _46Permutations t = new _46Permutations();
        int[] n = {1, 2, 3};
        System.out.println(t.permute(n));

    }


    public List<List<Integer>> permute(int[] nums) {
        if (null == nums || nums.length == 0) {
            return new ArrayList<>();
        }

        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();

        doPermute(result, new ArrayList<>(), nums, visited);
        return result;
    }

    /**
     * 回溯法
     * 将每个未被访问的元素依次加入当前解中
     * 当前解的个数等于nums的长度时，产生了一个解，记录下这个解，然后回溯
     * 回溯：从当前解中依次删除最后的元素，将删除元素的visit置为false
     * 删除后，再
     */
    private void doPermute(List<List<Integer>> permutation, List<Integer> curPermute, int[] nums, boolean[] visited) {
        if (curPermute.size() == nums.length) {
            permutation.add(new ArrayList<>(curPermute));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            curPermute.add(nums[i]);
            doPermute(permutation, curPermute, nums, visited);
            curPermute.remove(curPermute.size() - 1);
            visited[i] = false;
        }

    }
}
