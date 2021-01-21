package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 299. Bulls and Cows
 *
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess,
 * you provide a hint with the following info:
 *
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position.
 * Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
 * Note that both secret and guess may contain duplicate digits.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 *   |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can
 * only be rearranged to allow one 1 to be a bull.
 *
 */
public class BullsAndCows {

    public static void main(String [] args) {
        String secret = "1123", guess = "0111";
        BullsAndCows bullsAndCows = new BullsAndCows();
        String out = bullsAndCows.getHintOptimized(secret, guess);
        System.out.println(out);
    }

    public String getHintTwoPass(String secret, String guess) {
        Map<Integer, Integer> intToCountMap = new HashMap<>();

        int match = 0;
        int i =0;
        for (; i < guess.length() && i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                match++;
            } else {
                int val = secret.charAt(i) - '0';
                int ct = intToCountMap.getOrDefault(val, 0);
                ct++;
                intToCountMap.put(val, ct);
            }
        }


        int possibles = 0;
        for (i =0; i < guess.length(); i++) {
            if (guess.charAt(i) != secret.charAt(i)) {
                int val = guess.charAt(i) - '0';
                Integer ct = intToCountMap.get(val);
                if (ct != null) {
                    ct--;
                    if (ct ==0) {
                        intToCountMap.remove(val);
                    } else {
                        intToCountMap.put(val, ct);
                    }
                    possibles++;
                }
            }
        }
        return  match + "A" +  possibles + "B";
    }

    public String getHint(String secret, String guess) {
        Map<Integer, Integer> intToCountMap = new HashMap<>();
        int match = 0;
        int possibles = 0;
        for (int i = 0; i < guess.length() && i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                match++;
            } else {
                int valSecret = secret.charAt(i) - '0';
                int valGuess = guess.charAt(i) - '0';
                if (intToCountMap.getOrDefault(valSecret, 0) < 0) {
                    possibles++;
                }
                if (intToCountMap.getOrDefault(valGuess, 0) > 0) {
                    possibles++;
                }
                intToCountMap.put(valSecret, intToCountMap.getOrDefault(valSecret, 0) + 1);
                intToCountMap.put(valGuess, intToCountMap.getOrDefault(valGuess, 0) - 1);
            }
        }
        return  match + "A" +  possibles + "B";
    }

    /**
     * Accessing integers by their indices in an array is much faster than using a hash map
     * the below code runs leetcode test cases in 1 ms whereas the one above runs in 6ms.
     * @param secret
     * @param guess
     * @return
     */
    public String getHintOptimized(String secret, String guess) {
        int [] cts = new int[10];
        int match = 0;
        int possibles = 0;
        for (int i = 0; i < guess.length() && i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                match++;
            } else {
                int valSecret = secret.charAt(i) - '0';
                int valGuess = guess.charAt(i) - '0';
                if (cts[valSecret] < 0) {
                    possibles++;
                }
                if (cts[valGuess] > 0) {
                    possibles++;
                }
                cts[valGuess]--;
                cts[valSecret]++;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(match).append('A').append(possibles).append('B');
        return builder.toString();
    }
}
