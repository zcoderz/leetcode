package amazon.medium;


/**
 * Elegant solution for integer to roman approach is simple : get numbers from highest to lowest , process the largest
 * value first and reduce the orig number by that value until you reach 0.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        IntegerToRoman iToRom = new IntegerToRoman();
        String val = iToRom.intToRoman(1994);
        System.out.println(val);
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();

        while (num > 0) {

            for (int i = 0; i < values.length; i++) {
                if (num >= values[i]) {
                    num -= values[i];
                    roman.append(symbols[i]);
                    break;
                }
            }
        }

        return roman.toString();
    }

}
