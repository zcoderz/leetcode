package strings;

import java.util.Arrays;

public class BoyerMooreSearch {

    public static void main(String [] args) {
        BoyerMooreSearch bm = new BoyerMooreSearch();
        boolean bFound = bm.subStringSearch("dog", "the dog ran down");
        System.out.println(bFound);
    }

    boolean subStringSearch (String strToFind, String mainString) {
        int [] shifts = getShifts(strToFind);

        int skip ;
        for (int i = 0; i <= mainString.length() - strToFind.length(); i+=skip) {
            skip = 0;

            for (int m = strToFind.length()-1; m >=0; m--) {
                //clever idea here to skip beyond one when a mismatch is found.
                //number of characters to skip depends on where the character is in the string to be found.
                //m - shifts[mainString.charAt(i+m) below is the essence of the ago
                //i+m is the index in the original string where the char was found.
                //strToFind indicates the index of last occurrence of that character if exists
                //or -1. When -1 you want to skip as many characters as the location of the index right
                //when not -1 you want to skip last location of the char in array from the current m index
                //which is m - shifts[mainString.charAt(i+m)
                //best to draw above on a white board to understand the skip
                if (mainString.charAt(i+m) != strToFind.charAt(m)) {
                    skip = Math.max(1, m - shifts[mainString.charAt(i+m)]);
                    break;
                }
            }
            if (skip ==0 ) {
                return true;
            }
        }
        return false;
    }

    int [] getShifts(String searchString) {
        int [] shifts = new int[128];
        Arrays.fill(shifts, -1);

        for (int i = 0 ; i < searchString.length(); i++) {
            shifts[searchString.charAt(i)] = i;
        }

        return shifts;
    }
}
