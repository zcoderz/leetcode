package frequent.medium;

import java.util.*;

/**
 * nice problem . below implementation is O(n*log(k)) time.
 * uses hash map and priority queue to get the job done.
 *
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
