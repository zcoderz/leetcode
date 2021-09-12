package face_book.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Given a string num that contains only digits and an integer target, return all possibilities to
 * add the binary operators '+', '-', or '*' between the digits of num so that the resultant
 * expression evaluates to the target value.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 *
 * IMP-1 : Add to website.
 */
public class ExpressionOperators {

    public static void main(String [] args) {
        ExpressionOperators expression = new ExpressionOperators();
        List<String> res = expression.addOperators("123", 6);
        for (String str: res) {
            System.out.println(str);
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        process(num, "", 0, list, target, 0, 0);
        return list;
    }

    void process(String str, String expression, int index , List<String> res, long target, long eval, long anti) {
        if(index == str.length()) {
            if (eval == target) {
                res.add(expression);
            }
            return;
        }

        for (int i = index ; i < str.length(); i++) {
            if (str.charAt(index) == '0' && i != index) {
                break;
            }
            String strVal = str.substring(index, i+1);
            long lVal = Long.parseLong(strVal);
            if (expression.isEmpty()) {
                process(str, "" + lVal, i+1, res, target, eval + lVal, -lVal);
            } else {
                process(str, expression + "+" + lVal, i + 1, res, target, eval + lVal, -lVal);
                process(str, expression + "-" + lVal, i + 1, res, target, eval - lVal, lVal);
                process(str, expression + "*" + lVal, i + 1, res, target, eval + anti + -anti * lVal, lVal * anti);
            }
        }
    }
}
