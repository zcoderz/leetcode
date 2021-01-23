package amazon.medium;

import java.util.*;

public class AnagramsInAString {

    Set<String> anagrams = new HashSet<>();

    public static void main(String[] args) {
        AnagramsInAString ags = new AnagramsInAString();
        List<Integer> l = ags.findAnagrams("cbaebabacd", "abc");
        System.out.println(l);
    }

    /**
     * same approach as the hash map but leverages an array for comparing count of chars in array 1 vs array 2 this can
     * be done as we know the possible chars are lower case letters consisting of 26 unique chars
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.isEmpty() || p.isEmpty() || p.length() > s.length()) return new ArrayList();
        int[] target = new int[26];
        int[] source = new int[26];

        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            target[c - 'a'] += 1;
        }

        for (int i = 0; i < p.length(); i++) {
            char c = s.charAt(i);
            source[c - 'a'] += 1;
        }
        if (Arrays.equals(target, source)) {
            out.add(0);
        }
        int len = p.length();
        for (int i = 1; (i + len) <= s.length(); i++) {
            char c = s.charAt(i - 1);
            source[c - 'a'] -= 1;
            c = s.charAt(i + len - 1);
            source[c - 'a'] += 1;
            if (Arrays.equals(target, source)) {
                out.add(i);
            }
        }
        return out;
    }

    /**
     * a very clever approach, calculate char count and store in a map then as you pass through the string, construct a
     * new map and compare that against the target map
     * <p>
     * algo's approach is very clever, but hash map operations arent the fastest. hence the algo takes a good amount of
     * time.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsBetter(String s, String p) {
        if (s.isEmpty() || p.isEmpty() || p.length() > s.length()) return new ArrayList();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            Integer ct = targetMap.getOrDefault(p.charAt(i), 0);
            targetMap.put(p.charAt(i), ct + 1);
        }

        List<Integer> out = new ArrayList<>();
        HashMap<Character, Integer> slidingMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            Integer ct = slidingMap.getOrDefault(s.charAt(i), 0);
            slidingMap.put(s.charAt(i), ct + 1);
        }
        if (slidingMap.equals(targetMap)) {
            out.add(0);
        }
        int len = p.length();
        for (int i = 1; (i + len) <= s.length(); i++) {
            //update sliding count to remove count of last index
            char priorC = s.charAt(i - 1);
            int ctR = slidingMap.get(priorC);
            ctR--;
            if (ctR == 0) {
                slidingMap.remove(priorC);
            } else {
                slidingMap.put(priorC, ctR);
            }
            //update sliding count to include count of current index
            int newIndex = i + len - 1;
            Integer ct = slidingMap.getOrDefault(s.charAt(newIndex), 0);
            slidingMap.put(s.charAt(newIndex), ct + 1);
            if (slidingMap.equals(targetMap)) {
                out.add(i);
            }
        }
        return out;
    }

    /**
     * sorts the target string for each substring , sort it and then compare against the target sorted string still slow
     * as sort is slow N*logN and you have to repeat it for each sub string
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsAbitBetterButStillSlow(String s, String p) {
        char[] arr = p.toCharArray();
        Arrays.sort(arr);
        String sortedStr = new String(arr);
        int len = sortedStr.length();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; (i + len) <= s.length(); i++) {
            String subStr = s.substring(i, i + len);
            arr = subStr.toCharArray();
            Arrays.sort(arr);
            String tmpStr = new String(arr);
            if (tmpStr.equals(sortedStr)) {
                l.add(i);
            }
        }
        return l;
    }

    /**
     * leverages the anagrams created and stored in map where it matches that against current substring
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsSlow(String s, String p) {
        generateAnagrams(new StringBuilder(p), 0);
        int len = p.length();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; (i + len) <= s.length(); i++) {
            String subStr = s.substring(i, i + len);
            if (anagrams.contains(subStr)) {
                l.add(i);
            }
        }
        return l;
    }

    /**
     * generates all possible anagrams for string and adds to hash map this is a slow algo , runs in N! time.
     *
     * @param str
     * @param startIndex
     */
    void generateAnagrams(StringBuilder str, int startIndex) {
        anagrams.add(str.toString());
        for (int i = startIndex; i < str.length(); i++) {
            swap(str, startIndex, i);
            generateAnagrams(str, startIndex + 1);
            swap(str, startIndex, i);
        }
    }

    /**
     * swap chars at index i & j
     *
     * @param builder
     * @param i
     * @param j
     */
    void swap(StringBuilder builder, int i, int j) {
        char c = builder.charAt(i);
        builder.setCharAt(i, builder.charAt(j));
        builder.setCharAt(j, c);
    }
}
