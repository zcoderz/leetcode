package trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlienDictionary {

//    Input:
//            [
//            "wrt",
//            "wrf",
//            "er",
//            "ett",
//            "rftt"
//            ]
//
//    Output: "wertf"

    public String alienOrder(String[] words) {
        int numWords = words.length;
        Map<Character, String> wordDependencies = new HashMap<>();
        Set<Character> uniqueChars = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                uniqueChars.add(word.charAt(i));
            }
        }

        for (int i = 1; i < numWords; i++) {
            String wordA = words[i-1];
            String wordB = words[i];
            int inLen = Math.min(wordA.length(), wordB.length());
            for (int j = 0; j < inLen; j++) {
                if(wordA.charAt(j) != wordB.charAt(j)) {
                    String strDep = wordDependencies.getOrDefault(wordA.charAt(j), "");
                    strDep = strDep + wordB.charAt(j);
                    wordDependencies.put(wordA.charAt(j), strDep);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        Set<Character> visited = new HashSet<>();


        for (Character ch : uniqueChars) {
            if (!visited.contains(ch)) {
                Set<Character> visiting = new HashSet<>();
                int val = topoSort(wordDependencies, builder, visited, ch, visiting);
                if (val != 0) {
                    return "";
                }
            }
        }

        return builder.reverse().toString();

    }

    int topoSort(Map<Character, String> wordDependencies, StringBuilder builder, Set<Character> visited,
                  Character ch, Set<Character> visiting) {
        visiting.add(ch);
        String dep = wordDependencies.get(ch);
        if (null != dep) {
            for (int i = 0; i < dep.length(); i++) {
                Character depCh = dep.charAt(i);
                if (visiting.contains(depCh)) {
                    return -1;
                } else if (!visited.contains(depCh)) {
                    int val = topoSort(wordDependencies, builder, visited, depCh, visiting);
                    if (val != 0) {
                        return val;
                    }
                }
            }
        }
        visited.add(ch);
        builder.append(ch);
        return 0;
    }

}
