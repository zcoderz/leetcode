package hash_maps;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithAtMostKDistinctCharacters {

    public static void main(String [] args) {
        LongestSubStringWithAtMostKDistinctCharacters lgs = new LongestSubStringWithAtMostKDistinctCharacters();
        int ct = lgs.lengthOfLongestSubstringKDistinct("eceba", 2);
        System.out.println(ct);
    }

    /**
     * solve via sliding window and hash map
     * keep track of left most index included in map
     * when count increases k decrement the item at the left most index from map and move left forward
     *      this is logically correct since left index is moved forward the size stays less than or equal to
     *      the last max size reached with at most k distinct items
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int right = 0;
        int len = s.length();
        if (len ==0 || k==0) {
            return 0;
        }
        int maxLen = 0;
        //use map to store distinct items
        Map<Character, Integer> map = new HashMap<>();
        while (right < len) {
            Character ch = s.charAt(right++);
            int count = map.getOrDefault(ch, 0);
            count++; //track count
            map.put(ch, count);
            if (map.size() > k) {
                ch = s.charAt(left++);
                count = map.get(ch);
                count--;
                if (count == 0) {
                    //remove item from map if its count reaches 0 since
                    //we are using size of map to determine the distinct items contained in the map
                    map.remove(ch);
                } else {
                    map.put(ch, count);
                }
            }
            maxLen = Math.max(maxLen, right-left);
        }
        return maxLen;
    }
}