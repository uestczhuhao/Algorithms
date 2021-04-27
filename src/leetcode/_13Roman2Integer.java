package leetcode;

public class _13Roman2Integer {
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int res = 0;
        int index = 0;
        int len = s.length();
        for (int i = 0; i < romans.length; i++) {
            while ((index <= len - 2 && s.substring(index, index + 2).equals(romans[i]))
                || (index <= len - 1 && s.substring(index, index + 1).equals(romans[i]))) {
                res += values[i];
                index += romans[i].length();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _13Roman2Integer t = new _13Roman2Integer();
        System.out.println(t.romanToInt("MCMXCIV"));
    }
}
