package queue.bfs;

import java.util.*;

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
