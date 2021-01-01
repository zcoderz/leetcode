package amazon.medium;

import java.util.Stack;

public class BasicCalculator {

    public static void main(String [] args) {
        BasicCalculator bc = new BasicCalculator();

        String expression = "2*3*4";
        int v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = " 3/2 ";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "3-2";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "2*2";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "3/2";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "2+2*3";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "2+2*3+7*3-1-2*3";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);

        expression = "2 + 2 * 3 - 1";
        v = bc.calculate(expression);
        System.out.println(expression + " : " + v);
    }

    /**
     * similar to the below solution but doesnt use stack.
     * the only reason to keep the stack was to store the last calculated value
     * and prior numbers that could be aggregated once the string was parsed
     *
     * so the idea here is that we will track the last number in a variable
     * and store the prior calculated numbers in a result variable that will be aggregated dynamically
     * as string is parsed
     * @param s
     * @return
     */
    public int calculate(String s) {

        int result = 0;
        int lastNum = 0;
        int currentNum = 0;
        char operator = '+';

        for (int i =0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length()-1) {
                switch (operator) {
                    //when you see a +- add the last value to result and make current the last
                    case '+' -> {result += lastNum; lastNum = currentNum;}
                    case '-' -> {result += lastNum; lastNum = -1*currentNum;}
                    //when you see a * just update the last number
                    case '/' -> {lastNum /= currentNum; }
                    case '*' -> lastNum *= currentNum;
                }
                currentNum = 0;
                operator = c;
            }
        }
        //since at end of above loop we dont process the last instance of lastNum, we add it to the reuslt here
        result += lastNum;
        return result;
    }

    /**
     * this is a very simple solution but the simplicity results due to elegance in design
     * goes to show once again that should deliberate very carefully about creating an elegant solution at start
     * otherwise on the go (figure as you write code) solutions are never elegant
     *
     * got the below solution from leetcode, my original solution was a lot more complicated
     * @param s
     * @return
     */
    public int calculateStack(String s) {
        Stack<Integer> stack = new Stack<>();

        int currentNum = 0;
        char operator = '+';

        for (int i =0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length()-1) {
                switch (operator) {
                    case '+' -> stack.push(currentNum);
                    case '-' -> stack.push(-1*currentNum);
                    case '/' -> {int last = stack.pop(); last /= currentNum; stack.push(last);}
                    case '*' -> {int last = stack.pop(); last *= currentNum; stack.push(last);}
                }
                currentNum = 0;
                operator = c;
            }
        }
        int res = 0;
        for (int val : stack) {
            res += val;
        }
        return res;
    }


}
