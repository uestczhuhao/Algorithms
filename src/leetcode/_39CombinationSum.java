package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mizhu on 19-6-15 下午5:27
 */
public class _39CombinationSum {

    public static void main(String[] args) {
        int[] arr = {2, 6, 7, 3, 5, 1};
//        System.out.println(combinationSum(arr, 7));
        _39CombinationSum t = new _39CombinationSum();
        System.out.println(t.combinationSum1(arr, 9));
    }

    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);

        doFindCombination(candidates, 0, target);
        return ans;
    }

    private void doFindCombination(int[] candidates, int startIndex, int target) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 递归 + 剪枝，如果target < candidate[i] 则剪掉
        for (int i = startIndex; i < candidates.length && target >= candidates[i]; i++) {
            path.addLast(candidates[i]);
            doFindCombination(candidates, i, target - candidates[i]);
            path.removeLast();
        }
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        findCombination(0, candidates, target, res, new ArrayList<>());
        return res;
    }

    public static void findCombination(int startIndex, int[] candidates, int target, List<List<Integer>> res, List<Integer> sumCombinition) {
        if (target == 0) {
            res.add(new ArrayList<>(sumCombinition));
            return;
        } else if (target < 0) {
            return;
        } else {
            for (int i = startIndex; i < candidates.length && target >= candidates[i]; i++) {
                sumCombinition.add(candidates[i]);
                findCombination(i, candidates, target - candidates[i], res, sumCombinition);
                sumCombinition.remove(sumCombinition.size() - 1);
            }
        }
    }

}
