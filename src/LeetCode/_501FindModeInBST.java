package LeetCode;

/**
 * @author mizhu
 * @date 2020/9/6 11:37
 */
public class _501FindModeInBST {

    /**
     出现次数最多的次数
     */
    int maxCount = 0;
    /**
     * 当前值出现次数记数
     */
    int currentCount = 0;
    /**
     * 结果规模
     */
    int resultCount = 0;
    /**
     * 存放众数结果
     */
    int[] modeResult = null;

    TreeNode previousNode = null;

    /**
     * 两次遍历
     * 第一次目的：找到众数的个数最大值maxCount和结果数组的size，并初始化结果数组
     * 第二次目的：根据最大值填充结果数组，思路是当前值出现的次数currentCount已经达到第一次遍历时找到的众数的个数最大值maxCount
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        inOrder(root);
        currentCount = 0;
        modeResult = new int[resultCount];
        resultCount = 0;
        inOrder(root);
        return modeResult;
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (previousNode == null || previousNode.val != node.val) {
            currentCount = 1;
        } else {
            currentCount += 1;
        }

        // 第一次中序遍历，找出众数的maxCount
        if (currentCount > maxCount) {
            maxCount = currentCount;
            resultCount = 1;
        } else if (currentCount == maxCount) {
            // 第二次遍历，modeResult不为空，开始记录
            if (modeResult != null) {
                modeResult[resultCount] = node.val;
            }
            // 第一次遍历，找出结果个数
            // 第二次遍历，作为下标使用，理由是一样的过程，resultCount会从0涨到第一次的最大值-1
            resultCount++;
        }

        previousNode = node;
        inOrder(node.right);
    }
}
