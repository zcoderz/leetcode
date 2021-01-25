package google.medium;

import java.util.TreeSet;

/**
 * 729. My Calendar I
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will
 * not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half
 * open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is
 * common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 * Simple approach :
 * 1. Create a class to store meeting start and end times
 * 2. Store the meeting times in a treeset that's sorted by the meeting times
 * 3. Sort criteria is such that if the two meetings overlap with each other return them to be same. Otherwise sort by
 * meeting start time.
 */
public class MyCalendarI {

    public static void main(String [] args) {
        MyCalendarI  cal = new MyCalendarI();
        boolean val = cal.book(10, 20); // returns true
        System.out.println(val);
        val = cal.book(15, 25); // returns false
        System.out.println(val);
        val = cal.book(20, 30); // returns true
        System.out.println(val);
    }

    public static class TimeCoordinate {
        int startTime;
        int endTime ;
        public  TimeCoordinate(int start, int end) {
            this.startTime = start;
            this.endTime = end;
        }
    }

    TreeSet<TimeCoordinate> treeSet = new TreeSet<>(
            (l, r) -> {
                if (overlap(l,r) || overlap(r,l)) {
                    return 0;
                } else {
                    return Integer.compare(l.startTime, r.startTime);
                }
            });

    private boolean overlap(TimeCoordinate left, TimeCoordinate right) {
        return (left.startTime <= right.startTime && left.endTime > right.startTime)
                || (left.startTime < right.endTime && left.endTime > right.endTime);
    }


    public boolean book(int start, int end) {
        TimeCoordinate coordinate = new TimeCoordinate(start, end);
        if (treeSet.contains(coordinate)) {
            return false;
        }
        treeSet.add(coordinate);
        return true;
    }

}
