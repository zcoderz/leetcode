package frequent.hard;

import java.util.*;

/**
 * this is a hard problem.
 * using a priority queue simplifies it
 * but still edge cases are not simple
 *
 * pretty much create a list, sort based on x coordinates, height , start/end
 *
 *
 */
public class Skyline {

    public static void main(String [] args) {
        int[][] buildings = {{0,2,3}, {2,5,3}};
        Skyline sk = new Skyline();
        List<List<Integer>>  arr = sk.getSkyline(buildings);
        for (List<Integer> list : arr) {
            System.out.println(list);
        }

    }

    class Height implements Comparable {
        int xCoordinate;
        int height;
        boolean isStart;

        /**
         * you can draw on a white board the sorting criteria. pretty much items are sorted in increasing x coordinate
         * special cases where x is same are coded below. there are 3 special cases, draw them on board and you will
         * get it.
         * @param o
         * @return
         */
        @Override
        public int compareTo(Object o) {
            Height oH = (Height) o;
            if (xCoordinate == oH.xCoordinate) {
                if (isStart && oH.isStart) {
                    //add in height decreasing order for start items -- i,e add higher height first
                    return Integer.compare(oH.height, this.height) ;
                } else if (!isStart && !oH.isStart) {
                    //add in height increasing order for end items -- i,e add lower height first
                    return Integer.compare(this.height, oH.height);
                } else {
                    if (this.isStart) return -1; // have start come before end for same x
                    else return 1;
                }
            }  else {
                if (xCoordinate > oH.xCoordinate) return 1;
                else return -1;
            }
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
        priorityQueue.put(0,1);
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
                    if (n ==0) return null;
                    else return n;
                });
            }
            //get height item in queue
            int currH = priorityQueue.lastKey();
            if (currH != prevH) {
                //only output when last height is changing
                List<Integer> arr = new ArrayList<>();
                arr.add(h.xCoordinate); arr.add(currH);
                output.add(arr);
                prevH = currH;
            }
        }
        return output;
    }

}
