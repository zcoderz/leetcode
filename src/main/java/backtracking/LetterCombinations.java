package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    public static void main(String [] args) {
        String digit = "";
        LetterCombinations lc = new LetterCombinations();
        List<String> combs = lc.letterCombinations(digit);
        for (String str: combs) {
            System.out.println(str);
        }
    }

    public List<String> letterCombinations(String digits) {
        Map<Character, char []> letterToNumMap = new HashMap<>();
        letterToNumMap.put('2', new char[] {'a', 'b', 'c'} );
        letterToNumMap.put('3', new char[] {'d', 'e', 'f'} );
        letterToNumMap.put('4', new char[] {'g', 'h', 'i'} );
        letterToNumMap.put('5', new char[] {'j', 'k', 'l'} );
        letterToNumMap.put('6', new char[] {'m', 'n', 'o'} );
        letterToNumMap.put('7', new char[] {'p', 'q', 'r', 's'} );
        letterToNumMap.put('8', new char[] {'t', 'u', 'v'} );
        letterToNumMap.put('9', new char[] {'w', 'x', 'y', 'z'} );

        List<String> strings = new ArrayList<>();
        //recuseCombinations(digits, 0, letterToNumMap, strings);
        if(digits.length() !=0)
        backTrackSimplified("", digits, strings, letterToNumMap);

        return strings;
    }

    void backTrackSimplified(String ch, String digit,
                             List<String> strings,
                             Map<Character, char []> letterToNumMap) {
        if (digit.isEmpty()) {
            strings.add(ch);
            return;
        }
        char newD = digit.charAt(0);
        char [] arr = letterToNumMap.get(newD);
        for (char newCh : arr) {
            backTrackSimplified(ch+newCh, digit.substring(1), strings, letterToNumMap);
        }
    }

    void recuseCombinations(String digit, int dIndex, Map<Character, char []> characterMap, List<String> strings) {
       if (dIndex >= digit.length())  {
           return;
       }

       char c = digit.charAt(dIndex);
       char [] arr = characterMap.get(c);
       List<String> strNew = new ArrayList<>() ;
       if (strings.isEmpty()) {
           for (char ch: arr) {
               strNew.add(String.valueOf(ch));
           }
       } else {
           for (String str: strings) {
               for (char ch: arr) {
                   strNew.add(str + ch);
               }
           }
       }
       strings.clear();
       strings.addAll(strNew);
       recuseCombinations(digit, dIndex+1, characterMap, strings);
    }
}
