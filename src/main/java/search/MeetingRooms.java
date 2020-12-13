package search;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

    private Integer roomsInUse = 0;


    public static void main(String [] args) {
        int [][] meetings = {{15,16}, {10, 15}, {16, 25}};

        MeetingRooms mr = new MeetingRooms();
        int val = mr.minMeetingRooms(meetings);
        int j = 1;
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
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
