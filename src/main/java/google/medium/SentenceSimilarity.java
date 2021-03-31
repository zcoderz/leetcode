package google.medium;

import utils.graph.generic.UnionFindNode;

import java.util.*;

/**
 * 737. Sentence Similarity II Given two sentences words1, words2 (each represented as an array of strings), and a list
 * of similar word pairs pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the
 * similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and
 * "good" are similar, then "great" and "fine" are similar.
 *
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being
 * similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs
 * = [] are similar, even though there are no specified similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"]
 * can never be similar to words2 = ["doubleplus","good"].
 *
 * IMP-1: Good practice for union find
 */
public class SentenceSimilarity {

    public static void main(String[] args) {
        String[] words = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        List<String> l1 = Arrays.asList("great", "good");
        List<String> l2 = Arrays.asList("fine", "good");
        List<String> l3 = Arrays.asList("acting", "drama");
        List<String> l4 = Arrays.asList("skills", "talent");

        List<List<String>> l = new ArrayList<>();
        l.add(l1);
        l.add(l2);
        l.add(l3);
        l.add(l4);

        SentenceSimilarity sentence = new SentenceSimilarity();
        boolean ct = sentence.areSentencesSimilarTwo(words, words2, l);
        System.out.println(ct);
    }

    /**
     * This question begs for a union find algo. group the joined pairs together.
     *
     * @param words1
     * @param words2
     * @param pairs
     * @return
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, UnionFindNode<String>> wordToNodeMap = new HashMap<>();
        for (String word : words1) {
            if (!wordToNodeMap.containsKey(word)) {
                wordToNodeMap.put(word, new UnionFindNode<>(word));
            }
        }
        for (String word : words2) {
            if (!wordToNodeMap.containsKey(word)) {
                wordToNodeMap.put(word, new UnionFindNode<>(word));
            }
        }
        for (List<String> pair : pairs) {
            if (pair.size() != 2) {
                continue;
            }
            UnionFindNode<String> first = wordToNodeMap.get(pair.get(0));
            if (first == null) {
                first = new UnionFindNode<>(pair.get(0));
                wordToNodeMap.put(pair.get(0), first);
            }
            UnionFindNode<String> second = wordToNodeMap.get(pair.get(1));
            if (second == null) {
                second = new UnionFindNode<>(pair.get(1));
                wordToNodeMap.put(pair.get(1), second);
            }
            first.union(second);
        }
        for (int i = 0; i < words1.length; i++) {
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
