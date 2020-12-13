package sort.merge_sort;


/**
 * this algo is very pretty in that it describes divide and conquer approach in recursion very well.
 */
public class RecursiveMergeSort {
    private static Integer CUT_OFF = 0;
    public static void main(String [] args) {
        Integer [] arr = { 71, 1, 5, 7, 11, 2, 21, 54, 91, 2, 4, 8, 9, 12, 111, 78};
        //Integer [] arr = { 71, 1, 5, 7, 11, 2};

        RecursiveMergeSort recursiveMergeSort = new RecursiveMergeSort();
        recursiveMergeSort.sort(arr);

        for (int i : arr) {
            System.out.println(i);
        }
    }

    public RecursiveMergeSort() {
    }

    public void sort(Integer [] dest) {
        Integer src [] = dest.clone();
        sort(src, dest, 0, src.length-1);
    }

    private void sort (Integer [] src, Integer [] dest, int lo, int hi) {
        //reached cutoff, do insertion sort.
        //think insertion sort is fast on small data sets because array data for small segments is close
        //and this algo doesnt take up much memory space. 
        if (lo+CUT_OFF >= hi) {
            insertionSort(dest, lo, hi);
            return;
        }
        int mid = lo + (hi-lo) / 2;

        //the trick to swap src and destination is super nice
        //this allows you to work without having to copy data
        sort(dest, src, lo, mid);
        sort(dest, src, mid+1, hi);
        //if the two arrays are already sorted appropriately then just copy src to destination
        if (isLess (src[mid], src[mid+1])) {
            System.arraycopy(src, lo, dest, lo,hi-lo+1);
            return;
        }

        //to do check and system copy if already sorted
        merge(src, dest, lo, hi, mid);
    }

    private void insertionSort(Integer arr[] , int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j =i; j > lo && isLess(arr[j], arr[j-1]); j--) {
                swap(arr, j, j-1);
            }
        }
    }

    private void swap (Integer arr[] , int l, int r) {
        Integer a = arr[l];
        arr[l] = arr[r];
        arr[r] = a;
    }

    boolean isLess (Integer a, Integer b) {
        return a < b;
    }

    private static void merge (Integer[] src, Integer[] dest, int lo, int hi, int mid) {
        int right = mid+1;
        int i = lo;
        int curr = i;
        while (curr <= hi) {
            if (right > hi) {
                dest[curr++] = src[i++];
            } else if (i > mid) {
                dest[curr++] = src[right++];
            } else if (src[i] > src[right]) {
                dest[curr++] = src[right++];
            } else {
                dest[curr++] = src[i++];
            }
        }
    }


}
