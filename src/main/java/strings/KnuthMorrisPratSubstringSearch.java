package strings;

import java.util.HashSet;
import java.util.Set;

public class KnuthMorrisPratSubstringSearch {

    public static void main(String [] args) {
        KnuthMorrisPratSubstringSearch substringSearch = new KnuthMorrisPratSubstringSearch();
        boolean bFound = substringSearch.subStringFound("atmz", "catmyz");
        System.out.println(bFound);
    }

    public boolean subStringFound(String strToFind, String strToSearchFrom) {
        return kpm(strToSearchFrom.toCharArray(), strToFind.toCharArray());
    }

    /**
     * the idea is that for each index you store a prior index in the array you store an index
     * where the prefix matches the suffix so that you dont have rematch the entire string again
     *
     * for example for string abcaby you will build the below transitions
     *  a b c a b y
     *  0 0 0 1 2 0
     *
     *  when you are comparing the above string with a string such as
     *  a b c a b c a b y
     *  0 1 2 3 4 5 6 7 8
     *
     *  when you reach index 5 in above string which is a c and the value in your string is a y you go back
     *  to start search from the index 2 in the prefix because chars ab before y are also present before the char c
     *  that is index 2
     *
     *
     * @param pattern
     * @return
     */
    private static int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                //if match progress both index and i forward
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                //when not match move index back to prior possible match index
                if(index != 0){
                    index = lps[index-1];
                } else {
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * KMP algorithm of pattern matching.
     */
    public static boolean kpm(char []text, char []pattern){
        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j != 0){
                    j = lps[j-1]; //move j back to prefix index that has a possible match
                }else{
                    i++;
                }
            }
        }

        if (j == pattern.length){
            return true;
        }
        return false;
    }
}
