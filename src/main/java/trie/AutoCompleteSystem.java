package trie;

import java.util.*;

/**
 * 642. Design Search Autocomplete System
 * Design a search autocomplete system for a search engine. Users may input a sentence
 * (at least one word and end with a special character '#'). For each character they type except '#',
 * you need to return the top 3 historical hot sentences that have prefix the same
 * as the part of sentence already typed. Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
 * If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data.
 * Sentences is a string array consists of previously typed sentences.
 * Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user.
 * The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system.
 * The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * this is an interesting design problem what we get is quick retrieval of the top n sentences at each node. however ,
 * this comes at a cost where we are storing a lot of data at each trienode.
 * <p>
 * we could have a background job that updates the trie data by removing unneeded data we could also simplify the trie
 * structure to just contain the top n strings , rather than hold them in a set and map thereby reducing space needed
 * for the set and map. however, we'd need an external aggregator that can build the trie with top n strings and feed
 * the top n strings to the trie responsible for returning the data
 *
 * IMP-2: Good design practice question
 */
public class AutoCompleteSystem {

    String s = "";
    private TrieNode node;
    private TrieNode root;
    private List<TrieNode> nodes = new ArrayList<>();
    private static Integer SENTENCE_COUNT_THRESHOLD = 3;

    public AutoCompleteSystem(String[] sentences, int[] times) {
        node = new TrieNode();
        root = node;
        for (int i = 0; i < sentences.length; i++) {
            int time = times[i];
            String sentence = sentences[i];
            for (Character c : sentence.toCharArray()) {
                process(c, false, time);
            }
            process('#', false, time);

        }
    }

    public static void main(String[] args) {

        String[] sentences = {"for life", "for work", "for dad", "mango is yum", "apple is good"};
        int[] times = {2, 1, 1, 1, 1};
        AutoCompleteSystem autoCompletSystem = new AutoCompleteSystem(sentences, times);
        List<String> out = autoCompletSystem.input('f');
        System.out.println(out);
        autoCompletSystem.input('#');

        out = autoCompletSystem.input('f');
        System.out.println(out);
        autoCompletSystem.input('#');

        out = autoCompletSystem.input('m');
        System.out.println(out);
        autoCompletSystem.input('#');

        out = autoCompletSystem.input('f');
        System.out.println(out);

    }

    public List<String> input(char c) {
        return process(c, true, 1);
    }

    List<String> process(Character c, boolean doReturn, int delta) {
        if (c == '#') {
            for (TrieNode node : nodes) {
                node.processString(s, delta);
            }
            //reset the mappings when the given sentence ends
            nodes.clear();
            s = "";
            node = root;
            if (doReturn) {
                return new ArrayList<>();
            } else {
                return null;
            }
        } else {
            s += c;
            node = node.nodeMap.computeIfAbsent(c, k -> new TrieNode());
            nodes.add(node);
            if (doReturn) {
                int i = 0;
                Iterator<SentenceCount> iterator = node.sentenceCounts.iterator();
                List<String> sentences = new ArrayList<>();
                while (i < SENTENCE_COUNT_THRESHOLD && iterator.hasNext()) {
                    SentenceCount count = iterator.next();
                    sentences.add(count.sentence);
                    i++;
                }
                return sentences;
            } else {
                return null;
            }
        }
    }

    /**
     * this class is used to store a sentence along with its weight
     */
    private static class SentenceCount {
        String sentence;
        int count;

        public SentenceCount(String str, int count) {
            this.sentence = str;
            this.count = count;
        }

        public boolean equals(Object right) {
            SentenceCount sRight = (SentenceCount) right;
            return sRight.sentence.equals(sentence);
        }
    }

    /**
     * this trie stores regular character to sub trie mappings and in addition stores a set of SentenceCount. This
     * sentence is sorted by weight and then alphabetically so that it can return the appropriate sentences
     * <p>
     * when a sentence's frequency is being updated we need to remove and then add it from the set therefore we use a
     * map that maps given string to the sentence
     */
    public static class TrieNode {
        private Map<Character, TrieNode> nodeMap = new HashMap<>();
        private Set<SentenceCount> sentenceCounts = new TreeSet<>((first, second) -> {
            if (first.count == second.count) {
                return first.sentence.compareTo(second.sentence);
            }
            return Integer.compare(second.count, first.count);
        });
        private Map<String, SentenceCount> stringToCount = new HashMap<>();

        /**
         * method updates the counts
         *
         * @param str
         * @param delta delta being the weight to modify the given string by
         */
        void processString(String str, int delta) {
            SentenceCount sc = stringToCount.computeIfAbsent(str, k -> new SentenceCount(str, 0));
            sentenceCounts.remove(sc);
            sc.count = sc.count + delta;
            sentenceCounts.add(sc);
        }
    }
}
