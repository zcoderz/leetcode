package frequent.medium;

import java.util.*;

/**
 * 692. Top K Frequent Words
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * nice problem . below implementation is O(n*log(k)) time.
 * uses hash map and priority queue to get the job done.
 *
 * IMP-1: Common question with an interesting twist on priority queue
 */
public class TopKFrequentWords {

    /**
     * used a class to capture word and count. could have done without it and it would have kep memory low
     * but keeping this info in a separate class makes the code cleaner
     */
    class WordCount {
        String word;
        Integer count;
        public WordCount(String w, Integer c) {
            this.word = w;
            this.count = c;
        }
    }

    /**
     * Below algo has an interesting twist on priority queue. You are sorting the PQ to keep smaller frequencies and
     * alphabetically longer words on top. this is because you remove these items from the PQ as
     * possibles increase threshold. The order of data in PQ is reverse of what the question intends
     * thus you reverse the items before you return
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCount = new HashMap<>();
        //use map to count the data
        for (String word : words) {
            Integer i = wordCount.getOrDefault(word, 0);
            wordCount.put(word, i+ 1);
        }
        PriorityQueue<WordCount> priorityQueue = new PriorityQueue<WordCount>(
                (l, m)-> {
                    if (l.count.equals(m.count)) {
                        //keep larger words with same occurrence at top (see how order of l & m is switched below)
                        return m.word.compareTo(l.word);
                    } else {
                        //keep smaller occurrences at top of PQ
                        return Integer.compare(l.count , m.count);
                    }
                } );
        for (Map.Entry<String, Integer> mapVal : wordCount.entrySet()) {
            priorityQueue.offer(new WordCount(mapVal.getKey(), mapVal.getValue()));
            if (priorityQueue.size() > k) {
                priorityQueue.poll();   //trim pq size when its greater than threshold
            }
        }
        List<String> strings = new ArrayList<>();
        //important to use poll here to get next head from the pq. we cant just iterate over the pq
        while (!priorityQueue.isEmpty()) {
            strings.add(priorityQueue.poll().word);
        }
        //reverse the collection such that words with higher frequency and lower alphabetical count are at beginning
        Collections.reverse(strings);
        return strings;
    }
}
