package LeetCode;

/**
 * @author mizhu
 * @date 2021/1/26 17:49
 */
public class _55JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            maxJump = Math.max(maxJump, i + Math.abs(nums[i]));
            if (maxJump >= nums.length - 1) {
                return true;
            }

            if (maxJump <= i) {
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        _55JumpGame t = new _55JumpGame();
        int[] nums = {0};
        System.out.println(t.canJump(nums));
    }
}
