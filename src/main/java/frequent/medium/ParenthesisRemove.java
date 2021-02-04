package frequent.medium;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 *
 * IMP-1: Elegant solution to a common question - practice
 *
 *
 */
public class ParenthesisRemove {

    public static void main(String [] args) {
        String str = "lee(t(c)o)de)";
        ParenthesisRemove pr = new ParenthesisRemove();
        str = pr.minRemoveToMakeValid(str);
        System.out.println(str);
    }

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stackOpen = new Stack<>();
        List<Integer> close = new ArrayList<>();
        //do regular stack processing
        for (int i =0; i < s.length(); i++) {
            if(s.charAt(i)=='(') {
                stackOpen.push(i);
            } else if (s.charAt(i) == ')') {
                if (stackOpen.isEmpty()) {
                    close.add(i); //these need to be removed so add them to a separate collection
                } else {
                    stackOpen.pop();
                }
            }
        }
        //merge open with close in same collection
        while (!stackOpen.isEmpty()) {
            close.add(stackOpen.pop());
        }
        //sort reverse , delete chars from end to beginning so that your indexes stay consistent
        Collections.sort(close, (a, b) -> b - a);
        //java string are immutable, so copy them to string builder and then modify
        StringBuilder builder = new StringBuilder(s);
        for (Integer i : close) {
            builder.deleteCharAt(i);
        }
        return builder.toString();
    }
}
