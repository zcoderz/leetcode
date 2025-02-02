package google.medium;

import strings.KnuthMorrisPratSubstringSearch;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 686. Repeated String Match
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is
 * a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it,
 * return -1.
 *
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".
 *
 *
 *
 * Example 1:
 *
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 */
public class RepeatedStringMatch {

    public static void main(String[] args) {
        RepeatedStringMatch rep = new RepeatedStringMatch();
        String a = "abcd", b = "cdabcdab";
        int res = rep.repeatedStringMatch(a, b);
        System.out.println(res);

        a = "abc";
        b = "wxyz";
        res = rep.repeatedStringMatch(a, b);
        System.out.println(res);

        a = "a";
        b = "aa";
        res = rep.repeatedStringMatch(a, b);
        System.out.println(res);

        a = "a";
        b = "a";
        res = rep.repeatedStringMatch(a, b);
        System.out.println(res);

        a = "aaac";
        b = "aac";
        res = rep.repeatedStringMatch(a, b);
        System.out.println(res);

        a = "bb";
        b = "bbbbbbb";
        res = rep.repeatedStringMatch(a, b);
        System.out.println(res);
    }

    public int repeatedStringMatch(String a, String b) {
        HashMap<Character, Integer> charCountA = new HashMap<>();
        HashMap<Character, Integer> charCountB = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            Character c = a.charAt(i);
            int ct = charCountA.getOrDefault(c, 0);
            charCountA.put(c, ct + 1);
        }

        for (int i = 0; i < b.length(); i++) {
            Character c = b.charAt(i);
            int ct = charCountB.getOrDefault(c, 0);
            charCountB.put(c, ct + 1);
        }

        int largestDiff = 0;
        for (Map.Entry<Character, Integer> entry : charCountB.entrySet()) {
            Integer existingCt = charCountA.get(entry.getKey());
            if (existingCt == null) {
                return -1;
            }
            int currD = (int) Math.ceil(Math.max(0, (entry.getValue() - existingCt) / (double) existingCt));
            largestDiff = Math.max(largestDiff, currD);
        }
        StringBuilder cBuilder = new StringBuilder(a);
        cBuilder.append(a.repeat(Math.max(0, largestDiff)));
        String c = cBuilder.toString();
        int valid = validate(a, b, c);
        if (valid == -1) {
            return -1;
        } else {
            return valid + largestDiff + 1;
        }
    }

    int validate(String a, String b, String c) {
        boolean match = KnuthMorrisPratSubstringSearch.kpm(c.toCharArray(), b.toCharArray());
        if (match) {
            return 0;
        }
        c = c + a;
        match = KnuthMorrisPratSubstringSearch.kpm(c.toCharArray(), b.toCharArray());
        if (match) {
            return 1;
        }
        return -1;
    }
}
