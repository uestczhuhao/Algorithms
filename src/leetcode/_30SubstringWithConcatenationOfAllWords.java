package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _30SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
//        String s ="lingmindraboofooowingdingbarrwingmonkeypoundcake";
//        String[] words = new String[]{"fooo","barr","wing","ding","wing"};
//        String s = "wordgoodgoodgoodbestword";
//        String[] words = new String[]{"word","good","best","good"};
//
        String s = "barfoofoobarthefoobarman";
        String[] words = new String[]{"bar", "foo", "the"};

        _30SubstringWithConcatenationOfAllWords t = new _30SubstringWithConcatenationOfAllWords();
        System.out.println(t.findSubstring(s, words));
    }

    //考虑引入HashMap做优化
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) {
            return result;
        }

        int step = words[0].length();
        int index = 0;
        while (index <= s.length() - words.length * step) {

            int end = index + words.length * step;
            int findIndex = findSubValid(s, index, end, words);
            if (findIndex == index) {
                result.add(findIndex);
            }
            index += 1;
        }

        return result;
    }

    // 若以index开头的子字符串满足要求，则返回index，否则返回不满足的下标
    private int findSubValid(String s, int start, int end, String[] words) {
        boolean[] isVisited = new boolean[words.length];
        int step = words[0].length();
        int i = start;
        for (; i < end; i += step) {
            String thisStr = s.substring(i, i + step);
            boolean find = false;
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(thisStr) && !isVisited[j]) {
                    isVisited[j] = true;
                    find = true;
                    break;
                }
            }
            if (!find) {
                return start + 1;
            }
        }
        return start;
    }
}
