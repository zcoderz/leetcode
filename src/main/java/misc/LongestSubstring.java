package misc;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static void main(String []args) {
        int val = lengthOfLongestSubstring("dvdf");
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> characterSet = new HashSet<Character>();
        StringBuffer buffer = new StringBuffer();
        int maxLength = 0;
        int origStart = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (characterSet.contains(c)) {
                i = origStart;
                origStart++;
                if (buffer.length() > maxLength) {
                    maxLength = buffer.length();
                }
                characterSet.clear();
                buffer = new StringBuffer();
                continue;
            }
            characterSet.add(c);
            buffer.append(c);
        }
        if (buffer.length() > maxLength) {
            maxLength = buffer.length();
        }
        return maxLength;
    }

}
