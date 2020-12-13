package frequent.general.medium;

import java.util.Arrays;
import java.util.Comparator;

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
