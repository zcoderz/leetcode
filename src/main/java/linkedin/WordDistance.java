package linkedin;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class WordDistance {

    public static void main (String [] args) {
        String [] words = {"a", "a", "b", "b"};
        WordDistance wordDistance = new WordDistance(words);
        int dist = wordDistance.shortest("b", "a");
        System.out.println(dist);

    }
    Map<String, ArrayList<Integer>> wordMap = new HashMap<>();
    public WordDistance(String[] wordsDict) {
        int index = 0;
        for (String word : wordsDict) {
            ArrayList<Integer> indexList = wordMap.getOrDefault(word, new ArrayList<>());
            indexList.add(index++);
            wordMap.put(word, indexList);
        }
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> l1 = wordMap.get(word1);
        ArrayList<Integer> l2 = wordMap.get(word2);
        int minDist = Integer.MAX_VALUE;
        int indexL1 = 0;
        int indexL2 = 0;

        while (indexL1 != l1.size() && indexL2 != l2.size()) {
            minDist = Math.min(Math.abs(l1.get(indexL1) - l2.get(indexL2)), minDist);
            if (l1.get(indexL1) > l2.get(indexL2)) {
                indexL2++;
            } else {
                indexL1++;
            }
        }
        return minDist;

    }

}
