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
                //idea here is to skip characters when a mismatch occurs.
                //number of characters to skip is based on the last occurrence of that character in the string to search
                //logic is that if a mismatch is found at an index from right , you can skip as many indexes from left
                //in original string as the last index of that character that was mis matched. if that character doesn't
                //exist at all in the string to search then you can skip the entire length from right that you have traversed
                //its easier to understand the algo by watching a video such as :
                //https://www.coursera.org/lecture/algorithms-part2/boyer-moore-CYxOT
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
