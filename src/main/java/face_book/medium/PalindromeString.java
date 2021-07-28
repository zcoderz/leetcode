package face_book.medium;

import java.util.HashMap;
import java.util.Map;

public class PalindromeString {

    public static void main(String [] args) {
        PalindromeString ps = new PalindromeString();
        String v = ps.convertToPalindrome("ababdd");
        System.out.println(v);
        v = ps.convertToPalindrome("a");
        System.out.println(v);
        v = ps.convertToPalindrome("ab");
        System.out.println(v);
        v = ps.convertToPalindrome("abb");
        System.out.println(v);
    }

    /**
     * returns empty string for invalid palindromes or a valid palindrome if its possible to form
     * based on the string passed as parameter
     * @param strVal string that needs to be converted to a palindrome if possible
     * @return
     */
    public String convertToPalindrome(String strVal) {
        char [] chBuffer = new char[strVal.length()];
        Map<Character, Integer> mapCount = new HashMap<>();
        Character oddCh = null; int numOdd = 0;
        int left =0; int right = strVal.length()-1;
        //build map
        for (int i =0; i < strVal.length(); i++) {
            int v = mapCount.getOrDefault(strVal.charAt(i), 0 ) + 1;
            mapCount.put(strVal.charAt(i), v);
        }

        //validate # of odd character occurrences
        for (Map.Entry<Character, Integer> chEntry : mapCount.entrySet()) {
            if (chEntry.getValue() % 2 != 0) {
                numOdd++;
                if (numOdd > 1) {
                    return "";
                }
                oddCh = chEntry.getKey();
            }
        }

        //build with even characters
        for (Map.Entry<Character, Integer> chEntry : mapCount.entrySet()) {
            if (chEntry.getKey() == oddCh) continue;
            int v = chEntry.getValue();
            while (v !=0) {
                chBuffer[left++] = chEntry.getKey();
                chBuffer[right--] = chEntry.getKey();
                v -= 2;
            }
        }

        //append the odd character if any
        if (oddCh != null) {
            int v = mapCount.get(oddCh);
            while (v !=0) {
                chBuffer[left++] = oddCh; v--;
            }
        }

        return new String(chBuffer);
    }
}
