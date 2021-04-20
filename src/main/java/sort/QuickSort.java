package sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2, 11, 7, 15};
        QuickSort sort = new QuickSort();
        sort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //get the index which was chosen as pivot and partitioned into its correct place
        int j = partition(arr, lo, hi);
        //repeat for left part of the array
        quickSort(arr, lo, j - 1);
        //repeat for right part of the array
        quickSort(arr, j + 1, hi);
    }

    int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];//better to take pivot as median of hi, mid , lo
        int i = lo + 1;
        int j = hi;
        while (j > i) {
            while (arr[j] > pivot) j--;
            while (arr[i] < pivot) i++;
            if (i >= j) break; //stop when i & j cross
            //per above loop by definition i would be at an index greater than the pivot value
            //and j would be at an index less than the pivot
            swap(arr, i++, j--);
        }
        swap(arr, j, lo); //j represents the index at which the pivot should exist
        return j;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
