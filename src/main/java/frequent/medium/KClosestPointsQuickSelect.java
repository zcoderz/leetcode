package frequent.medium;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 973. K Closest Points to Origin
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * IMP-1 : Very common question. Important to practice
 *
 * this is a quicker method to find the first K shortest points than the standard sort
 * it will work to find the right location for the Kth element and then stop.
 *
 * unlike quick sort it doesn't recuse into both sides of partitioned array and therefore its complexity is O(N)
 * instead of O(N LogN)
 *
 * partition method is tricky , practice writing it again to get it sunk inside the head.....
 * IMP-1: Common question and good practice for quick select
 */
public class KClosestPointsQuickSelect {
    int[][] points;

    public static void main(String [] args) {
        //int [][]points = {{-5,4},{-6,-5},{4,6}, {2, 11} , {7,11}};
        //int [][] points = {{1,3},{-2,2}};
        //int [][] points = {{0,1},{1,0}};
        int [][] points = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};


        KClosestPointsQuickSelect kcp = new KClosestPointsQuickSelect();
        points = kcp.kClosest(points, 2);

        for (int [] p: points) {
            System.out.println(p[0] + " " + p[1]);
        }

    }

    /**
     * returns the first K elements
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        //subtract one from K below because array indexes start from index 0
        sort(0, points.length-1, K-1);

        return Arrays.copyOf(points, K);
    }

    /**
     * this is a simple sort routine thats copied off of quick sort. except that it stops when the kth index is found
     * whereas quick sorts keeps iterating untill all indexes have been found
     * @param left
     * @param right
     * @param k
     */
    void sort(int left, int right, int k) {
        int index = partition(left, right);
        if (k > index) {
            sort(index+1, right, k);
        } else if (k < index) {
            sort(left, index-1, k);
        }

    }

    /**
     * this is a regular method for quick sort partition
     * @param left
     * @param right
     * @return
     */
    int partition (int left, int right) {
        //get a random index , could have taken average value of left, mid and right which is more efficient than random
        int partitionIndex = ThreadLocalRandom.current().nextInt(left, right+1);
        //move the partition index left most
        swap(left, partitionIndex);
        int compVal = dist(left); //calculate value to compare
        int iL = left++; //move left forward
        //this is a regular loop for quick sort partition.
        //it calculates right index such that items that are right of right index are greater than partition val
        //and those left are less than it
        while (true) {
            while (right >= left && dist(left) < compVal) {
                left++; //move left forward
            }
            while (right >= left && dist(right) > compVal) {
                right--; //move right back
            }
            if (left >= right) break; // break when left and right cross
            swap(left++, right--); //when swapping move indexes forward and back
        }
        //move original partition index to the right's spot because that spot indicates its correct location
        swap(iL, right);
        return right;
    }

    /**
     * method to calculate distance of a point
     * @param index
     * @return
     */
    int dist(int index) {
        return points[index][0] * points[index][0] + points[index][1] * points[index][1];
    }

    /**
     * regular swap method
     * @param a
     * @param b
     */
    void swap(int a, int b) {
        int t1 = points[a][0];
        int t2 = points[a][1];
        points[a][0] = points[b][0];
        points[a][1] = points[b][1];
        points[b][0] = t1;
        points[b][1] = t2;
    }
}
