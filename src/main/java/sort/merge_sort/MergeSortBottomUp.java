package sort.merge_sort;

/**
 * version of merge sort bottom up
 */
public class MergeSortBottomUp {


    public static void main(String [] args) {
        Integer [] arr = { 71, 1, 5, 7, 11, 2, 21, 54, 91, 2, 4, 8, 9, 12, 111, 78};
        //Integer [] arr = { 71, 1, 5, 7, 11, 2};

        MergeSortBottomUp ms = new MergeSortBottomUp();
        ms.sort(arr);

        for (int i : arr) {
            System.out.println(i);
        }
    }

    public void sort(Integer [] dest) {
        Integer src [] = new Integer[dest.length];
        int len = dest.length;
        for (int i =1 ; i < len; i *= 2) {
            //start with array of size one and start sorting blocks twice the size
            for (int j =0 ; j < len-i; j += i + i) {
                //each loop merge sorted array whose size is defined by i
                int lo=j;
                int mid = lo+i-1;
                int hi = lo+2*i-1;
                merge(src, dest, lo, hi, mid);
            }

        }

    }

    private static void merge (Integer[] src, Integer[] dest, int lo, int hi, int mid) {
        //copy into src to setup the merge below
        for (int i = lo; i <= hi; i++) {
            src[i] = dest[i];
        }

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
