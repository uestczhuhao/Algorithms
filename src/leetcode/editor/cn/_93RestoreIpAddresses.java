package leetcode.editor.cn;

//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
// 和 "192.168@1.1" 是 无效的 IP 地址。 
//
// 
//
// 示例 1： 
//
// 输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法 
// 👍 500 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _93RestoreIpAddresses {
    public static void main(String[] args) {
        Solution t = new _93RestoreIpAddresses().new Solution();
        System.out.println(t.restoreIpAddresses("010010"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：暴力，每个子串取1-3长度，最后取符合要求的结果返回
         */
        public List<String> restoreIpAddresses1(String s) {
            List<String> answer = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return answer;
            }

            int len = s.length();
            StringBuilder curIp = new StringBuilder();
            for (int a = 1; a <= 3; a++) {
                for (int b = 1; b <= 3; b++) {
                    for (int c = 1; c <= 3; c++) {
                        for (int d = 1; d <= 3; d++) {
                            // 第一步，过滤长度不等于len的记录
                            if (a + b + c + d != len) {
                                continue;
                            }
                            int n1 = Integer.parseInt(s.substring(0, a));
                            int n2 = Integer.parseInt(s.substring(a, a + b));
                            int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int n4 = Integer.parseInt(s.substring(a + b + c));
                            // 第二步，过滤不合法的ip值段
                            if (n1 > 255 || n2 > 255 || n3 > 255 || n4 > 255) {
                                continue;
                            }

                            curIp.append(n1).append(".").append(n2).append(".").append(n3).append(".").append(n4);
                            // 第三步，排除类似01，这种不合法ip
                            if (curIp.length() == len + 3) {
                                answer.add(curIp.toString());
                            }
                            // 注意，要清除字符串
//                            curIp = new StringBuilder();
                            curIp.delete(0, curIp.length());
                        }
                    }
                }
            }
            return answer;
        }

        /**
         * 思路：回溯法，对截取的每一段，判断其是否满足要求
         */

        List<String> answer = new LinkedList<>();
        LinkedList<String> curPath = new LinkedList<>();
        String srcStr;

        public List<String> restoreIpAddresses(String s) {
            if (s == null) {
                return answer;
            }

            srcStr = s;
            dfs(0, 4);
            return answer;
        }

        /**
         * @param begin  字符串的开始位置
         * @param remain 剩余段数，初始为4
         */
        private void dfs(int begin, int remain) {
            if (begin == srcStr.length()) {
                String ipPath = String.join(".", curPath);
                if (remain == 0 && ipPath.length() - 3 == srcStr.length()) {
                    answer.add(ipPath);
                }
                return;
            }

            if (remain <= 0) {
                return;
            }

            for (int i = begin; i < begin + 3 && i < srcStr.length(); i++) {
                // 剩余的字符串太多，此种可能不可能符合要求，直接继续
                if ((remain - 1) * 3 < srcStr.length() - 1 - i) {
                    continue;
                }

                int ipValue = Integer.parseInt(srcStr.substring(begin, i + 1));
                if (ipValue <= 255) {
                    curPath.addLast(String.valueOf(ipValue));
                    dfs(i + 1, remain - 1);
                    curPath.removeLast();
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}