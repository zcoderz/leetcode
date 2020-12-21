package frequent.general.medium;

import utils.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * questions requires reorganizing the string such that no two characters are adjacent to each other.
 * its tricky to identify that the logic requires use of a pq sorted by char count....
 * but once you identify, solution is real easy!
 */
public class ReorganizeString {

    public static void main(String [] args) {
        //String str = "rvhrlpiesrrryrbrxrkirranbdrrzgasoxmiiiillkkkknnooppptttzzz";
        String str = "aaab";
        //String str = "abcccdddef";
        //String str = "aab";
        ReorganizeString reorg = new ReorganizeString();
        String changed = reorg.reorganizeString(str);
        System.out.println(changed);
    }


    /**
     * this is a brilliant solution from leetcode.
     *
     * idea is to group characters by count and add to PQ
     * where PQ is set to have chars with highest count on top
     *
     * the above logic ensures that characters with highest count are given the most slots
     *
     * @param s
     * @return
     */
    public String reorganizeString(String s) {
        int [] charCount = new int [26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i)-'a'] += 1;
        }
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((first, second) -> {
            if (first.second.equals(second.second)) {
                return Character.compare(first.first, second.first);
            }
            return Integer.compare(second.second, first.second); //move high count items on top
        });
        int len = s.length();
        for (int i =0; i < charCount.length; i++) {
            int count = charCount[i];
            if (count ==0) continue;
            if (count > (len+1)/2) { //edge case to ensure count that cant be handled by the algo
                return "";
            }
            Character ch = (char) ( 'a' + i);
            pq.add(new Pair<>(ch, count));
        }
        StringBuilder builder = new StringBuilder();
        while (pq.size() >= 2) {
            Pair<Character, Integer> first = pq.poll();
            Pair<Character, Integer> second = pq.poll();
            builder.append(first.first);
            builder.append(second.first);
            if (first.second > 1) {
                Pair<Character, Integer> firstM = new Pair<>(first.first, first.second - 1);
                pq.add(firstM);
            }
            if(second.second > 1) {
                Pair<Character, Integer> secondM = new Pair<>(second.first, second.second - 1);
                pq.add(secondM);
            }
        }
        if (!pq.isEmpty()) {
            Pair<Character, Integer> lastItem = pq.poll();
            builder.append(lastItem.first);
        }
        return builder.toString();
    }

    int len ;
    char [] str;
    /**
     * Slow code
     */
//    public String reorganizeString(String s) {
//        len = s.length();
//        str = new char[len];
//        for (int i =0; i < s.length(); i++) {
//            str[i] = s.charAt(i);
//        }
//        boolean worked = reorgString(0, 'Z');
//        if (worked) {
//            return new String(str);
//        } else {
//            return "";
//        }
//    }

    /**
     * this takes too much time on large strings and times out
     * @param startIndex
     * @param priorC
     * @return
     */
    public boolean reorgString(int startIndex, char priorC) {
        if (startIndex == len-1) {
            if (str[startIndex] != priorC) {
                return true;
            }
            return false;
        }
        Set<Character> processedCharSet = new HashSet<>();
        for (int j = startIndex; j < len; j++) {
            if (str[j] != priorC && !processedCharSet.contains(str[j])) {
                processedCharSet.add(str[j]);
                swap(startIndex, j);
                boolean isOk = reorgString(startIndex+1, str[startIndex]);
                if (isOk) return true;
                else swap(startIndex, j);
            }
        }
        return false;
    }

    void swap(int i, int j) {
        if (i==j) return;
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }
}
