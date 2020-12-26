package frequent.medium;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * code to balance the parenthesis in a string.
 * easy solution. remember to use StringBuilder
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
