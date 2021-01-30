package leetcode;

public class _13Roman2Integer {
    public int romanToInt(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int res = 0;
        for (int i = 0; i < romans.length; i++) {
            while (s.startsWith(romans[i])) {
                res += values[i];
                s = s.replaceFirst(romans[i], "");
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _13Roman2Integer t = new _13Roman2Integer();
        System.out.println(t.romanToInt("MCMXCIV"));
    }
}
