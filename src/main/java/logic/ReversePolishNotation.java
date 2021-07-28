package logic;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 150. Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 */
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
