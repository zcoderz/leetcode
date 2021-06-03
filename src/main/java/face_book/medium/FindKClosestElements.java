package face_book.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindKClosestElements {

    public static void main(String [] args) {
        FindKClosestElements find = new FindKClosestElements();
        //int [] arr = {1,2,3,4,5};
        //int k = 4, x = -1;

//        int [] arr = {0,0,1,2,3,3,4,7,7,8};
//        int k = 3;
//        int x = 5;

        int [] arr = {3,5,8,10};
        int k = 2;
        int x = 15;

        List<Integer> res = find.findClosestElementsEfficient(arr, k, x);
        System.out.println(res);

    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);
        int startingIndex = index;
        if (index < 0) {
            startingIndex++;
            startingIndex *= -1;
        }

        startingIndex = Math.max(startingIndex, 0);
        startingIndex = Math.min(startingIndex, arr.length-1);

        int leftA = Math.max(startingIndex-1, 0);
        int rightA = Math.min(startingIndex+1, arr.length-1);
        if ( (Math.abs(arr[leftA]-x ) < Math.abs(arr[startingIndex]-x))) {
            startingIndex = leftA;
        }
        if ( (Math.abs(arr[rightA]-x ) < Math.abs(arr[startingIndex]-x))) {
            startingIndex = rightA;
        }

        LinkedList<Integer> items = new LinkedList<>();

        int left = startingIndex, right = startingIndex+1;

        while (k != 0) {
            if (left == -1 || right < arr.length && Math.abs(arr[left]-x) > Math.abs(arr[right]-x)) {
                items.add(arr[right++]);
            } else {
                items.addFirst(arr[left--]);
            }
            k--;
        }
        return items;
    }

    public List<Integer> findClosestElementsEfficient(int[] arr, int k, int x) {
        int startingIndex = 0;
        int left = 0;
        int right = arr.length-k;
        while (left < right) {
            int mid = left + (right-left)/2;
            if ( Math.abs(arr[mid]-x) > Math.abs(arr[mid+k] -x)) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        startingIndex = left;
        LinkedList<Integer> items = new LinkedList<>();
        while (k != 0) {
            items.add(arr[startingIndex++]);
            k--;
        }
        return items;
    }
}
