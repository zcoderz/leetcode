package face_book.medium;

/**
 * 227. Basic Calculator II
 * Medium
 *
 * 2869
 *
 * 424
 *
 * Add to List
 *
 * Share
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 */
public class BasicCalculator {


    public static void main(String [] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        int val = basicCalculator.calculate("20*2");
        System.out.println(val);
        val = basicCalculator.calculate("2*2+2");
        System.out.println(val);
        val = basicCalculator.calculate("2*2/30-1");
        System.out.println(val);
        val = basicCalculator.calculate("3+5/2");
        System.out.println(val);
    }

    public int calculate(String s) {
        int total = 0;
        char priorOp = '+';
        int priorNum = 0;

        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            int currentNum = 0;
            boolean isDigit = false;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                currentNum = currentNum*10 + s.charAt(i) - '0';
                i++;
                isDigit = true;
            }

            if (!isDigit) {
                priorOp = s.charAt(i);
                i++;
            } else {
                switch (priorOp) {
                    case '+' :
                        total += currentNum;
                        priorNum = currentNum;
                        break;
                    case '-' :
                        total -= currentNum;
                        priorNum = -currentNum;
                        break;
                    case '*':
                        total -= priorNum;
                        currentNum = currentNum * priorNum;
                        total += currentNum;
                        priorNum = currentNum;
                        break;
                    case '/':
                        total -= priorNum;
                        currentNum =  priorNum / currentNum;
                        total += currentNum;
                        priorNum = currentNum;
                }
            }
        }
        return total;
    }
}
