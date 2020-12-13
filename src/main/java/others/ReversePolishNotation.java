package others;

import java.util.Stack;

public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
                int iVal1 = stack.pop();
                int iVal2 = stack.pop();
                switch (token) {
                    case "+": {
                        int iVal3 = iVal1 + iVal2;
                        stack.push(iVal3);
                        break;
                    }
                    case "-": {
                        int iVal3 = iVal2 - iVal1;
                        stack.push(iVal3);
                        break;
                    }
                    case "*": {
                        int iVal3 = iVal2 * iVal1;
                        stack.push(iVal3);
                        break;
                    }
                    case "/": {
                        int iVal3 = iVal2 / iVal1;
                        stack.push(iVal3);
                        break;
                    }
                }
            } else {
                int iVal = Integer.parseInt(token);
                stack.push(iVal);
            }

        }
        return stack.pop();
    }

}
