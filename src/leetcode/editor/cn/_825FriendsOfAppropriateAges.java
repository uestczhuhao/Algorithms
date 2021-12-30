package leetcode.editor.cn;

//在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
//
// 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
//
//
// age[y] <= 0.5 * age[x] + 7
// age[y] > age[x]
// age[y] > 100 && age[x] < 100
//
//
// 否则，x 将会向 y 发送一条好友请求。
//
// 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
//
// 返回在该社交媒体网站上产生的好友请求总数。
//
//
//
// 示例 1：
//
//
//输入：ages = [16,16]
//输出：2
//解释：2 人互发好友请求。
//
//
// 示例 2：
//
//
//输入：ages = [16,17,18]
//输出：2
//解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
//
//
// 示例 3：
//
//
//输入：ages = [20,30,100,110,120]
//输出：3
//解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
//
//
//
//
// 提示：
//
//
// n == ages.length
// 1 <= n <= 2 * 10⁴
// 1 <= ages[i] <= 120
//
// Related Topics 数组 双指针 二分查找 排序 👍 114 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

public class _825FriendsOfAppropriateAges {
    public static void main(String[] args) {
        Solution t = new _825FriendsOfAppropriateAges().new Solution();
        int[] ages = {20,30,100,110,120};
        System.out.println(t.numFriendRequests(ages));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 对一个候选人y，加其好友的人x需要满足的条件为
         * 1. 0.5 * age[x] + 7 < age[y] <= age[x]
         * 2. age[y] <= 100 || age[x] >= 100 （隐含条件为age[y] <= age[x]）
         * 可以根据这两个条件设定一个[ 0.5 * age[x] + 7, age[x] ] 区间，在这个区间的x都能加好友
         * <p>
         * 注意1：把age排序后，随着x增加，左右区间均向右移动
         * 注意2：由于右边界一定包含x本身，因此求答案时要减1（因为年龄等于x的可能不止一个，因此要全部找到，再减去x本身即可，否则可能会漏判）
         *
         *
         */
        public int numFriendRequests(int[] ages) {
            int num = ages.length;
            Arrays.sort(ages);
            int left = 0, right = 0;
            int ans = 0;
            for (int age : ages) {
                if (age < 15) {
                    continue;
                }
                // left一直右移到合法区域内
                while (ages[left] <= 0.5 * age + 7) {
                    left++;
                }
                // right一直右移到不合法区域内，得到区间[left, right)
                while (right < num && ages[right] <= age) {
                    right++;
                }
                ans += right - left - 1;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
