package hash_maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *249. Group Shifted Strings
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 *
 * Example:
 *
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 */
public class GroupShiftedStrings {

    public static void main(String [] args) {
        String []arr = {"abc","bcd","acef","xyz","az","ba","a","z"};
        //String []arr = {"az","ba"};
        GroupShiftedStrings gs = new GroupShiftedStrings();
        List<List<String>> str = gs.groupStrings(arr);
        str.forEach(System.out::println);
    }
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> stringGroups = new HashMap<>();

        for (String str: strings) {
            String num = "";
            for (int i = 0; i < str.length(); i++) {
                int prior = i - 1;
                if (prior == -1) {
                    prior = str.length() - 1;
                }

                //below was tricky!!!
                //basically you are checking how far current character is ahead of the previous.
                Character c = str.charAt(i);
                Character p = str.charAt(prior);
                int diff;
                if (c >= p) {
                    //if current is ahead or same as previous, just subtract
                    diff = c - p;
                } else {
                    //if current is behind previous, need to wrap previous character from 'z' to a
                    //thats done via 'z' - p + 1, and then add to that how much current is ahead of 'a'
                    //same concept as wrapping array from end to beginning (form a ring) but with characters.
                    diff = 'z' - p + 1 + c - 'a';
                }

                num = num + diff;
            }

            List<String> list = stringGroups.computeIfAbsent(num, k -> new ArrayList<>());
            list.add(str);
        }

        return new ArrayList<>(stringGroups.values());
    }

}
