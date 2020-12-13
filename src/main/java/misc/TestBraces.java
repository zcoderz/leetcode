package misc;

import java.util.*;

public class TestBraces {

    public boolean checkForMatchedBraces(String bracheStr) {
        Set<Character> openBraces = new HashSet<Character>();
        openBraces.add('{');
        openBraces.add('(');
        openBraces.add('[');
        Map<Character, Character> closedBraces = new HashMap<Character, Character>();
        closedBraces.put('}', '{');
        closedBraces.put(')', '(');
        closedBraces.put(']', '[');
        Stack<Character> openBracesStack = new Stack<Character>();
        int braceStrLen = bracheStr.length();
        for (int i = 0; i < braceStrLen; i++) {
            Character c = bracheStr.charAt(i);
            if (openBraces.contains(c)) {
                openBracesStack.push(c);
            } else {
                if (openBracesStack.isEmpty()) {
                    return false;
                }
                Character openC = openBracesStack.pop();
                Character expectedOpen = closedBraces.get(c);
                if (expectedOpen != openC) {
                    return false;
                }
            }
        }
        return true;
    }
}
