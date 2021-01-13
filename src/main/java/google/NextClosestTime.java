package google;

import java.util.Iterator;
import java.util.TreeSet;

/**
 *681. Next Closest Time
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid.
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 * Seems deceptively extremely simple. nextTime needs to be coded with good focus to handle the edge cases.
 */
public class NextClosestTime {
    int secondHr = 0;
    int firstHr = 0;
    int secondMin = 0;
    int firstMin = 0;
    TreeSet<Integer> digits = new TreeSet<>();

    public static void main(String [] args) {
        NextClosestTime next = new NextClosestTime();
        String time = "19:34";
        time = next.nextClosestTime(time);
        System.out.println(time);
        next.digits.clear();
        time = "23:59";
        time = next.nextClosestTime(time);
        System.out.println(time);

    }
    public String nextClosestTime(String time) {
        parseTime(time);
        if (digits.size() == 1) {
            //special case if all digits are same
            return time;
        }
        return nextTime();
    }

    void parseTime(String time) {
        secondHr = time.charAt(0) - '0';
        firstHr = time.charAt(1) - '0';
        secondMin = time.charAt(3) - '0';
        firstMin = time.charAt(4) -  '0';

        digits.add(secondHr); digits.add(firstHr);
        digits.add(secondMin); digits.add(firstMin);
    }

    /**
     * for each of first min, second min, first hr, second hr
     * adjust to the next highest in range, if you can find that's it.
     * else set to lowest and move to the next highest time digit.
     *
     * i personally found this approach much simpler to understand and implement than the approach in leet code
     * which searches for the lowest possible next time based on time delta from orig to each of the possitibilities
     * based on available digits
     *
     * @return
     */
    String nextTime() {
        Integer next = digits.higher(firstMin);
        if (next != null) {
            firstMin = next;
            return buildNewTime();
        }
        firstMin = digits.first();
        next = digits.higher(secondMin);
        if (next != null && next <=5) {
            secondMin = next;
            return buildNewTime();
        }
        secondMin = digits.first();
        next = digits.higher(firstHr);
        if (next != null && next <=9) {
            if (!(secondHr == 2 && next > 4)) {
                firstHr = next;
                return buildNewTime();
            }
        }
        firstHr = digits.first();
        next = digits.higher(secondHr);
        if (next != null && next == 2) {
            secondHr = next;
        } else {
            secondHr = digits.first();
            if (secondHr ==0 ) {
                //if you reached this case then for each of hr, min you have picked the lowest
                //but you cant do that for the second hr, it has to be one next after the first
                Iterator<Integer> iter = digits.iterator();
                iter.next();
                secondHr = iter.next();
            }
        }
        return buildNewTime();
    }

    String buildNewTime() {
        return String.valueOf(secondHr) + firstHr + ":" + secondMin + firstMin;
    }
}
