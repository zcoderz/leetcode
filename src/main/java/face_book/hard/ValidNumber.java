package face_book.hard;

import java.util.List;
import java.util.Map;

/**
 * 65. Valid Number
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers:
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 * while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 */
public class ValidNumber {

    public static void main(String [] args) {
        String str = ".20";
        ValidNumber valid = new ValidNumber();
        boolean isV = valid.isNumber(str);
        System.out.println(isV);
    }
    static List<Map<String, Integer>> dfa = List.of(
            Map.of("digit", 1, "sign", 2, "dot", 3), //0
            Map.of("digit", 1, "dot", 4, "exp", 5), //1
            Map.of("dot", 3, "digit", 1), //2
            Map.of("digit", 4), //3
            Map.of("exp", 5, "digit", 4), //4
            Map.of("sign", 6, "digit", 7), //5
            Map.of("digit", 7), //6
            Map.of("digit", 7)); //7

    boolean isNumber(String str) {
        Integer currentState = 0;

        for (int i =0; i < str.length(); i++) {
            String format = "";
            if (Character.isDigit(str.charAt(i))) {
                format = "digit";
            } else if (str.charAt(i) == '.') {
                format = "dot";
            } else if (str.charAt(i) == 'E' || str.charAt(i) == 'e') {
                format = "exp";
            }  else if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                format = "sign";
            }
            currentState = dfa.get(currentState).get(format);
            if (currentState == null) {
                return false;
            }
        }

        if ((currentState == 1) || (currentState == 4) || (currentState == 7)) {
            return true;
        }
        return false;
    }
}
