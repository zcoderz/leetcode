package frequent.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 973. K Closest Points to Origin
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
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
 */
public class KClosestPoints {

    public static void main(String [] args) {
        int [][]points = {{-5,4},{-6,-5},{4,6}};
        KClosestPoints kcp = new KClosestPoints();
        points = kcp.kClosest(points, 2);

        for (int [] p: points) {
            System.out.println(p[0] + " " + p[1]);
        }

    }
    public int[][] kClosest(int[][] points, int K) {
        double [] [] pointsWithDistance = new double[points.length][3];
        for (int i = 0; i < points.length; i++) {
            double [] pWithD = new double[3];
            pWithD[0] = points[i][0];
            pWithD[1] = points[i][1];
            pWithD[2] =  Math.pow(Math.pow(points[i][0], 2) + Math.pow(points[i][1],2), (0.5D));
            pointsWithDistance[i] = pWithD;
        }
        Arrays.sort(pointsWithDistance, Comparator.comparingDouble( (double[] a) -> a[2]));
        int[][] kPoints = new int[K][2];
        for (int i = 0; i < K; i++) {
            int []v = new int[2];
            v[0]= (int) pointsWithDistance[i][0];
            v[1]= (int) pointsWithDistance[i][1];
            kPoints[i] = v;
        }
        return kPoints;
    }

}
