package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mizhu on 19-6-15 下午5:27
 */
public class _39CombinationSum {

    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        System.out.println(combinationSum(arr, 7));
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
                findCombination(i, candidates, target-candidates[i], res, sumCombinition);
                sumCombinition.remove(sumCombinition.size() - 1);
            }
        }
    }

}
