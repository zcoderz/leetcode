package google.medium;

import utils.graph.generic.UnionFindNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceSimilarity {

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, UnionFindNode<String>> wordToNodeMap = new HashMap<>();
        for (String word: words1) {
            if (!wordToNodeMap.containsKey(word)) {
                wordToNodeMap.put(word, new UnionFindNode<>(word));
            }
        }
        for (String word: words2) {
            if (!wordToNodeMap.containsKey(word)) {
                wordToNodeMap.put(word, new UnionFindNode<>(word));
            }
        }
        for(List<String> pair: pairs) {
            if (pair.size() !=2 ) {
                continue;
            }
            UnionFindNode<String> first = wordToNodeMap.get(pair.get(0));
            UnionFindNode<String> second = wordToNodeMap.get(pair.get(0));
            first.union(second);
        }
        for (int i =0; i < words1.length; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            if (word1.equals(word2)) {
                continue;
            }
            UnionFindNode<String> first = wordToNodeMap.get(word1);
            UnionFindNode<String> second = wordToNodeMap.get(word2);
            if (first == null || second == null) {
                return false;
            }
            if (!first.find().equals(second.find())) {
                return false;
            }
        }
        return true;
    }
}
