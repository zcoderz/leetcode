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
        int [][] transitions = buildTransitions(strToFind);
        int j = 0;
        for (int i =0 ; i < strToSearchFrom.length() && j < strToFind.length(); i++) {
             char c = strToSearchFrom.charAt(i);
             j = transitions[c][j];
        }
        return j == strToFind.length();
    }

    int [][] buildTransitions(String subString) {
        Set<Character> characters = new HashSet<>();
        for (Character c: subString.toCharArray()) {
            characters.add(c);
        }
        int len = subString.length();
        int [][] transitions = new int [128][len];
        transitions[subString.charAt(0)][0] = 1;
        //this is a really cool concept where you keep X one behind J
        //so that you dont have to traverse the pattern again for mis matches

        for (int x =0, j = 1 ; j < len; j++) {
            for (Character c: characters) {
                transitions[c][j] = transitions[c][x]; //copy mis matches
            }
            //copy forward transition for match
            transitions[subString.charAt(j)][j] = j + 1;
            //copy forward transition for x
            x = transitions[subString.charAt(j)][x];
        }
        return transitions;
    }
}
