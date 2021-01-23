package sort;

import java.util.ArrayList;
import java.util.List;

public class CountSmallerToSelf {

    public static void main(String[] args) {
        CountSmallerToSelf ms = new CountSmallerToSelf();
        int[] nums = {10, 1, 2, 5, 7, 3, 4, 44, 99};
        ms.mergeSort(nums);
    }

    public List<Integer> mergeSort(int[] nums) {
        int[][] vals = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            vals[i][0] = nums[i];
            vals[i][1] = i;
        }

        int[] count = new int[nums.length];
        int[][] aux = new int[nums.length][2];
        recurseMerge(0, nums.length - 1, vals, aux, count);

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            arr.add(count[i]);
            System.out.println(count[i]);
        }
        return arr;
    }

    void recurseMerge(int low, int high, int[][] vals, int[][] aux, int[] count) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        recurseMerge(mid + 1, high, vals, aux, count);
        recurseMerge(low, mid, vals, aux, count);

        //if (num[mid] > num[mid+1]) return;
        merge(low, mid, high, vals, aux, count);
    }

    void merge(int low, int mid, int high, int[][] num, int[][] aux, int[] count) {
        for (int i = low; i <= high; i++) {
            aux[i] = num[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (k <= high) {

            if (i > mid) {
                num[k++] = aux[j++];
            } else if (j > high) {
                count[aux[i][1]] += j - (mid + 1);
                num[k++] = aux[i++];

            } else if (aux[i][0] > aux[j][0]) {
                num[k++] = aux[j++];
            } else {
                count[aux[i][1]] += j - (mid + 1);
                num[k++] = aux[i++];
            }


        }

    }

}

//https://leetcode.com/explore/interview/card/top-interview-questions-hard/118/trees-and-graphs/851/discuss/76584/Mergesort-solution

//class Solution {
//    public List<Integer> countSmaller(int[] nums) {
//        int n = nums.length;
//        int[][] arr = new int[n][2];
//        int[][] aux = new int[n][2];
//        for (int i = 0; i < n; i++) {
//            arr[i] = new int[] {nums[i], i};
//        }
//        int[] count = new int[n];
//        sort(arr, count, 0, n - 1, aux);
//        List<Integer> list = new ArrayList();
//        for (int i = 0; i < n; i++){
//            list.add(count[i]);
//        }
//        return list;
//    }
//
//    private void sort(int[][] arr, int[] count, int lo, int hi, int[][] aux) {
//        if (lo >= hi) return;
//        int mid = lo + (hi - lo) / 2;
//        sort(arr, count, lo, mid, aux);
//        sort(arr, count, mid + 1, hi, aux);
//        for (int i = lo; i <= hi; i++) {
//            aux[i] = arr[i];
//        }
//        int i = lo, j = mid + 1;
//        for (int k = lo; k <= hi; k++) {
//            if (i == mid + 1) { arr[k] = aux[j++]; }
//            else if (j == hi + 1 || aux[i][0] <= aux[j][0]) {
//                count[aux[i][1]] += j - (mid + 1);
//                arr[k] = aux[i++];
//            } else {
//                arr[k] = aux[j++];
//            }
//        }
//    }
//}