package face_book.medium;

public class KClosestPoints {

    public static void main(String [] args) {
        int [][] points = {{3,3},{5,-1},{-2,4}};

        KClosestPoints closes = new KClosestPoints();
        int [][] res = closes.kClosest(points, 2);
        int j = 1;
    }

    double [][] distances;
    public int[][] kClosest(int[][] points, int K) {
        distances = new double[points.length][2];
        for (int i =0; i < points.length; i++) {
            double dist = Math.sqrt(points[i][0]* points[i][0] + points[i][1]* points[i][1]);
            distances[i] = new double[] { (double) i, dist};
        }
        quickSelect (distances, 0, distances.length-1, K-1);
        int[][] res = new int[K][2];
        for (int i =0 ; i < K; i++) {
            res[i] = points[ (int) distances[i][0]];
        }
        return res;
    }

    void quickSelect(double [][] distances, int left, int right, int k) {
        if (left >= right) return;
        int origRight = right;
        int pivot = left;
        left++;
        double pivotV = distances[pivot][1];
        while (true) {
            while (right >= left && distances[left][1] < pivotV) {
                left++;
            }
            while (right >= left && distances[right][1] > pivotV) {
                right--;
            }
            if (left >= right) break;
            swap(distances, left++, right--);
        }
        swap(distances, right, pivot);
        if (right == k) return;
        if (k > right ) {
            quickSelect(distances, right + 1, origRight, k);
        } else {
            quickSelect(distances, pivot, right-1, k);
        }
    }

    void swap(double [][] distances, int left, int right) {
        double [] tmp = distances[left];
        distances[left] = distances[right];
        distances[right] = tmp;
    }
}
