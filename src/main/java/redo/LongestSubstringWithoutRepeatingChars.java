package redo;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChars {

    public static void main(String []args) {
        String test = "abba";
        LongestSubstringWithoutRepeatingChars longest = new LongestSubstringWithoutRepeatingChars();
        int res = longest.lengthOfLongestSubstring(test);
        System.out.println(res);
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> mapChars = new HashMap<>();
        int left = 0;
        int maxLen =0;
        for (int i =0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (mapChars.containsKey(ch)) {
                int priorLoc = mapChars.get(ch);
                left = Math.max(priorLoc+1, left);
            }

            maxLen = Math.max(maxLen, i - left +1);

            mapChars.put(ch, i);
        }

        return maxLen;
    }

}
