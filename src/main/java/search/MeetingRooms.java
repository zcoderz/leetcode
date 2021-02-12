package search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * IMP-1: Extremely common question
 */
public class MeetingRooms {

    private Integer roomsInUse = 0;


    public static void main(String[] args) {
        int[][] meetings = {{15, 16}, {10, 15}, {16, 25}};

        MeetingRooms mr = new MeetingRooms();
        int val = mr.minMeetingRooms(meetings);
        int j = 1;
    }

    /**
     * A clever approach that simplifies the problem a whole lot
     * 1. sort by meeting start times
     * 2. add meeting end times into priority queue. if priority queue isn't empty and it has a meeting whole end time
     * is before the new meeting's start time , remove the element at top of the priority queue. Otherwise, increment
     * the number of rooms variable.
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (!minHeap.isEmpty() && minHeap.peek() <= intervals[i][0]) {
                minHeap.poll();
            } else {
                roomsInUse++;
            }
            minHeap.add(intervals[i][1]);
        }
        return roomsInUse;
    }
}
