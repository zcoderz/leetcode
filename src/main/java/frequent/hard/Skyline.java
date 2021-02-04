package frequent.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 218. The Skyline Problem
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Given the locations and heights of all the buildings,
 * return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate
 * in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the
 * skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's
 * termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings
 * should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5
 * should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 *
 * IMP-1 : common problem. concepts practiced in this problem are applicable every where else.
 * create a list, sort based on x coordinates, height , start/end
 */
public class Skyline {

    public static void main(String[] args) {
        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
        Skyline sk = new Skyline();
        List<List<Integer>> arr = sk.getSkyline(buildings);
        for (List<Integer> list : arr) {
            System.out.println(list);
        }

    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Height> heights = new ArrayList<>();
        for (int[] building : buildings) {
            Height h = new Height();
            h.xCoordinate = building[0];
            h.height = building[2];
            h.isStart = true;

            Height h2 = new Height();
            h2.xCoordinate = building[1];
            h2.height = building[2];
            h2.isStart = false;
            heights.add(h);
            heights.add(h2);
        }

        Collections.sort(heights);
        List<List<Integer>> output = new ArrayList<>();
        //tree is used instead of a priority queue because it supports log N inserts and deletes
        TreeMap<Integer, Integer> priorityQueue = new TreeMap<>();
        //this sentinel is important. when an item is deleted from the queue the queue is basically empty
        //but we want the queue to indicate height as 0 and hence this value
        priorityQueue.put(0, 1);
        int prevH = 0;
        for (Height h : heights) {
            boolean isStart = h.isStart;
            //map.merge simplifies code.
            if (isStart) {
                //if height already exists add 1 to it, otherwise add a new height of 1
                priorityQueue.merge(h.height, 1, Integer::sum);
            } else {
                //decrement height by 1, if the count reaches 0, remove it.
                priorityQueue.merge(h.height, 1, (prev, one) -> {
                    int n = prev - one;
                    if (n == 0) return null;
                    else return n;
                });
            }
            //get height item in queue
            int currH = priorityQueue.lastKey();
            if (currH != prevH) {
                //only output when last height is changing
                List<Integer> arr = new ArrayList<>();
                arr.add(h.xCoordinate);
                arr.add(currH);
                output.add(arr);
                prevH = currH;
            }
        }
        return output;
    }

    class Height implements Comparable {
        int xCoordinate;
        int height;
        boolean isStart;

        /**
         * you can draw on a white board the sorting criteria. pretty much items are sorted in increasing x coordinate
         * special cases where x is same are coded below. there are 3 special cases, draw them on board and you will get
         * it.
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Object o) {
            Height oH = (Height) o;
            if (xCoordinate == oH.xCoordinate) {
                if (isStart && oH.isStart) {
                    //add in height decreasing order for start items -- i,e add higher height first
                    return Integer.compare(oH.height, this.height);
                } else if (!isStart && !oH.isStart) {
                    //add in height increasing order for end items -- i,e add lower height first
                    return Integer.compare(this.height, oH.height);
                } else {
                    if (this.isStart) return -1; // have start come before end for same x
                    else return 1;
                }
            } else {
                if (xCoordinate > oH.xCoordinate) return 1;
                else return -1;
            }
        }
    }

}
