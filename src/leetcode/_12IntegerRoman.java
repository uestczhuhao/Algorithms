package leetcode;


import java.util.HashMap;
import java.util.Map;

public class _12IntegerRoman {
    public String intToRoman1(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                res.append(romans[i]);
                num -= values[i];
            }
        }

        return res.toString();
    }

    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            int repeat = 0;
            if (num / 1000 > 0) {
                repeat = num / 1000;
                res.append(this.repeat(map.get(1000), repeat));
                num %= 1000;
            } else if (num / 500 > 0) {
                repeat = num / 500;
                res.append(this.repeat(map.get(500), repeat));
                num %= 500;
            } else if (num / 100 > 0) {
                repeat = num / 100;
                res.append(this.repeat(map.get(100), repeat));
                num %= 100;
            } else if (num / 50 > 0) {
                repeat = num / 50;
                res.append(this.repeat(map.get(50), repeat));
                num %= 50;
            } else if (num / 10 > 0) {
                repeat = num / 10;
                res.append(this.repeat(map.get(10), repeat));
                num %= 10;
            } else if (num / 5 > 0) {
                repeat = num / 5;
                res.append(this.repeat(map.get(5), repeat));
                num %= 5;
            } else {  // 除以 1
                repeat = num;
                res.append(this.repeat(map.get(1), repeat));
                num = 0;
            }
        }


        return res.toString().replace("VIIII", "IX").replace("LXXXX", "XC").replace("DCCCC", "CM").
                replace("IIII", "IV").replace("XXXX", "XL").replace("CCCC", "CD");
    }

    private String repeat(String str, int num) {
        if (num <= 0)
            return "";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num; i++) {
            res.append(str);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        _12IntegerRoman t = new _12IntegerRoman();

        System.out.println(t.intToRoman1(30));
    }
}
