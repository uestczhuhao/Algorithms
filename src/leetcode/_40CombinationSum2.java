package leetcode;

import java.util.*;

/**
 * Created by mizhu on 19-7-21 下午3:39
 */
public class _40CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {1, 1, 1, 2, 2};
        int target = 4;
        System.out.println(combinationSum2(candidates, target));
        _40CombinationSum2 t = new _40CombinationSum2();
        System.out.println(t.combinationSum21(candidates, target));
    }

    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    /**
     * 回溯+剪枝
     * for循环横向遍历，递归纵向遍历
     * 1 横向遍历时剪枝，nums[i] == nums[i-1]时，跳过
     * 2 每个数字取一次，因此下一次递归从i + 1 开始
     * 3 跳过不可能的组合，即target < candidate[i]的分支
     */
    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }

        Arrays.sort(candidates);
        doCombination1(0, candidates, target);
        return ans;
    }

    private void doCombination1(int startIndex, int[] candidates, int target) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && candidates[i] <= target; i++) {
            // i > startIndex && candidates[i] == candidates[i - 1]
            // 用于去除本层的重复元素，因为for循环的所有i都处于同一层，相同的元素只要第一个参与运算就够了
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            doCombination1(i + 1, candidates, target - candidates[i]);
            path.removeLast();
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);

        Set<List<Integer>> reSet = new HashSet<>();
        doCombination(0, candidates, reSet, target, new ArrayList<>());

        res.addAll(reSet);
        return res;
    }

    public static void doCombination(int start, int[] candidates, Set<List<Integer>> res,
                                     int target, List<Integer> curList) {
        if (target == 0) {
            res.add(new ArrayList<>(curList));
        } else if (target < 0) {
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    return;
                }
                /*
                每个值有且仅有一次被加入队列的机会，就是递归至此值为start的时候
                avoid duplicate combinations. Consider following example:
                Search in [1, 1, 1, 2, 2] for target 4, without the expression, you will get three identical combinations:
                [1, 1, 2, 2] from index [0, 1, 3, 4] of the candidates;
                [1, 1, 2, 2] from index [0, 2, 3, 4] of the candidates;
                [1, 1, 2, 2] from index [1, 2, 3, 4] of the candidates.
                 */
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                curList.add(candidates[i]);
                doCombination(i + 1, candidates, res, target - candidates[i], curList);
                curList.remove(curList.size() - 1);
            }
        }
    }
}
