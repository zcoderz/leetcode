package microsoft;

import java.util.*;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public static void main(String [] args) {
        MinimumDeletionsToMakeCharacterFrequenciesUnique min = new MinimumDeletionsToMakeCharacterFrequenciesUnique();
        int del = min.minDeletions("aabb");
        System.out.println(del);
    }

    public int minDeletions(String s) {
        Integer [] charCount = new Integer[26];
        Arrays.fill(charCount, 0);
        for (int i =0; i < s.length(); i++) {
            charCount[s.charAt(i)-'a']++;
        }

        Set<Integer> frequencies = new HashSet<>();
        //dont need to sort.
        //Arrays.sort(charCount, Collections.reverseOrder());
        int deletes = 0;
        for (int val : charCount) {
            while ((val != 0) && (frequencies.contains(val))) {
                val--;
                deletes++;
            }
            frequencies.add(val);
        }
        return deletes;
    }
}
