package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 *
 * quick select is a fairly complicate implementation for an interview. perhaps choose map and priority queue if in
 * interview.
 *
 * if choosing quick select , write place holder methods for partition , swap and quick select recursion for on the key
 * calling method and time permitting go into the other methods.
 *
 */
public class TopKFrequent {

    Map<Integer, Integer> numCount = new HashMap<>();

    public static void main(String[] args) {
        TopKFrequent top = new TopKFrequent();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        int[] vals = top.topKFrequent(nums, 5);
        for (int i = 0; i < vals.length; i++) {
            System.out.println(vals[i]);
        }
    }

    /**
     * clever method to leverage quick select for top K
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            int val = numCount.getOrDefault(nums[i], 0);
            numCount.put(nums[i], val + 1);
        }

        Set<Integer> dNums = numCount.keySet();

        //get the distinct numbers and sort based on them while using count in map
        int[] distinct = new int[dNums.size()];
        int i = 0;
        for (Integer iNum : dNums) {
            distinct[i++] = iNum;
        }

        //the parameter for K is tricky. you basically are looking for the highest K values
        //therefore when you find the item that goes in N-K (-1 for adjusting oth index), that's the stop point
        //for the quick selection
        quickSelect(distinct, 0, distinct.length - 1, distinct.length - k - 1);

        //copy starting from the N-K index
        return Arrays.copyOfRange(distinct, distinct.length - k, distinct.length);
    }

    /**
     * regular QS recursion. The comparison is however based on values retrieved from key to count map this is a clever
     * trick as it simplifies code a bunch
     *
     * @param arr
     * @param low
     * @param high
     * @param k
     */
    void quickSelect(int[] arr, int low, int high, int k) {
        if (low >= high) return;

        int mid = (low + high) / 2;
        //get counts from map
        //use the median value from left, mid, right
        int midFrequency = numCount.get(arr[mid]);
        int lowFrequency = numCount.get(arr[low]);
        int highFrequency = numCount.get(arr[high]);

        int pivot = mid;
        if (lowFrequency > midFrequency && lowFrequency < highFrequency) {
            pivot = low;
        } else if (highFrequency > lowFrequency && highFrequency < midFrequency) {
            pivot = high;
        }

        int index = partition(arr, low, high, pivot);
        //when index is reached, exit!
        if (index == k) return;
        else if (index > k) {
            quickSelect(arr, low, index - 1, k);
        } else {
            quickSelect(arr, index + 1, high, k);
        }
    }

    /**
     * the loops in QS partition are tricky! need to practice them a bunch
     *
     * @param arr
     * @param low
     * @param high
     * @param pivot
     * @return
     */
    int partition(int[] arr, int low, int high, int pivot) {
        swap(arr, low, pivot);
        int pIndex = low;
        //get pivot from count map
        int pivotFrequency = numCount.get(arr[low++]);
        //loop continuous
        while (true) {
            //check bounds before accessing array
            while (low <= high && numCount.get(arr[low]) < pivotFrequency) low++;
            //check bounds before accessing array
            while (low <= high && numCount.get(arr[high]) > pivotFrequency) high--;
            //break if off bound
            if (low >= high) break;
            swap(arr, low++, high--);
        }
        //high is at right index
        swap(arr, high, pIndex);
        return high;
    }

    void swap(int[] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }
}
