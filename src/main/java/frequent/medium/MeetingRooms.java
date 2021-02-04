package frequent.medium;

import java.util.ArrayList;

/**
 * 253. Meeting Rooms II
 *
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * IMP-1: Common question
 */
public class MeetingRooms {

    /**
     * a class to denote a time unit and whether it is start or end
     */
    public static class TimeCoordinate {
        int time;
        boolean isStart;
    }

    // the problem can be solved similar to the sky line problem
    public int minMeetingRooms(int[][] intervals) {
        ArrayList<TimeCoordinate> times = new ArrayList<>();
        //iterate through intervals and add start and end times to list
        for (int[] time : intervals) {
            TimeCoordinate tc = new TimeCoordinate();
            tc.time = time[0];
            tc.isStart = true;
            TimeCoordinate tcE = new TimeCoordinate();
            tcE.time = time[1];
            tcE.isStart = false;
            times.add(tc); times.add(tcE);
        }
        //sort the list by time , if time is same have end time be processed before the start
        //this is so that we can shut down the meeting so that another can start immediately afterwards
        times.sort((TimeCoordinate a , TimeCoordinate b) -> {
          if (a.time == b.time) {
              if (a.isStart) {
                  return 1;
              } else {
                  return -1; //-1 so a (end) is recorded before b
              }
          } else {
            return Integer.compare(a.time, b.time);
          }
        });
        int maxRooms = 0;
        int currRooms = 0;
        //iterate through all time coordinates, if a meeting starts increment count, if ends decrement
        for (TimeCoordinate tc: times) {
            if (tc.isStart) currRooms++;
            else currRooms--;
            maxRooms = Math.max(currRooms, maxRooms); //keep track of maxRooms
        }
        return maxRooms;
    }
}
