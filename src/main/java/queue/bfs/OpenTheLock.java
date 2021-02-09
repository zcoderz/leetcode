package queue.bfs;

import java.util.*;

/**
 * 752. Open the Lock
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * IMP-1: Excellent question to practice BFS based search to target.
 */
public class OpenTheLock {
    private Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        String[] strDeadEnds = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};

        OpenTheLock openTheLock = new OpenTheLock();
        int numMoves = openTheLock.openLock(strDeadEnds, "8888");
        System.out.println(numMoves);
    }

    public int openLock(String[] deadends, String target) {
        String start = "0000";
        Queue<String> queue = new LinkedList<>();
        String level = "L";
        queue.add(start);
        queue.add(level);

        Set<String> deads = new HashSet<>();
        Collections.addAll(deads, deadends);

        int passes = 0;
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str.equals(level)) {
                passes++;
                if (!queue.isEmpty()) {
                    queue.add(level);
                }
                continue;
            }
            if (str.equals(target)) {
                return passes;
            }
            if (deads.contains(str)) {
                continue;
            }
            List<String> possibleTransforms = transform(str);
            queue.addAll(possibleTransforms);
        }
        return -1;
    }

    List<String> transform(String str) {
        List<String> strTransforms = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);
            String strNewA = str.substring(0, i) + transformCharAhead(val) + str.substring(i + 1);
            String strNewB = str.substring(0, i) + transformCharBack(val) + str.substring(i + 1);

            if (!visited.contains(strNewA)) {
                strTransforms.add(strNewA);
                visited.add(strNewA);
            }
            if (!visited.contains(strNewB)) {
                strTransforms.add(strNewB);
                visited.add(strNewB);
            }
        }
        return strTransforms;
    }

    String transformCharAhead(char givenChar) {
        int i = givenChar - 48;
        int iNext = i + 1;
        iNext = iNext % 10;
        return String.valueOf(iNext);
    }

    String transformCharBack(char givenChar) {
        int i = givenChar - 48;
        int iNext = i - 1;
        if (iNext == -1) {
            iNext = 9;
        }
        return String.valueOf(iNext);
    }
}
