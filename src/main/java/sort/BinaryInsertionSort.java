package sort;


/**
 * fastest algo to work on small arrays. use binary search to find location of the element move elements between i and
 * lo right
 * <p>
 * the sort is fast because on small arrays the items are located close together this algo requires a bunch of moves in
 * the array, but moves are fast because of closeness in locality what is more expensive are the comparisons, and since
 * this sort uses less comparisons its faster.
 */
public class BinaryInsertionSort {

    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 11, 9, 98, 2, 6, 879, 98, 101, 2, 7, 9, 11, 1232, 8782178, 7, 1, 2};
        BinaryInsertionSort bs = new BinaryInsertionSort();
        bs.sort(arr);
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        }
    }

    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {

            int lo = 0;
            int hi = i;
            int val = arr[i];

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (val > arr[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            if (i - lo > 0) {
                System.arraycopy(arr, lo, arr, lo + 1, i - lo);
            }
            arr[lo] = val;
        }

    }

//    public void sort(int[] a) {
//        int n = a.length;
//        for (int i = 1; i < n; i++) {
//
//            // binary search to determine index j at which to insert a[i]
//            int v = a[i];
//            int lo = 0, hi = i;
//            while (lo < hi) {
//                int mid = lo + (hi - lo) / 2;
//                if (a[mid] > v) {
//                    hi = mid;
//                } else {
//                    lo = mid +1;
//                }
//            }
//
//            // insetion sort with "half exchanges"
//            // (insert a[i] at index j and shift a[j], ..., a[i-1] to right)
//            for (int j = i; j > lo; --j)
//                a[j] = a[j-1];
//            a[lo] = v;
//        }
//    }
}
