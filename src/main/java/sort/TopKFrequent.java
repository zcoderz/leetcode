package sort;

import java.util.*;

public class TopKFrequent {


    public static void main (String [] args) {
        TopKFrequent top = new TopKFrequent();
        //int [] nums = {1,2, 2, 2, 2, 3, 3, 3, 0, 1 };
        //int [] nums = {1,2};
        int [] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        int [] vals = top.topKFrequent(nums , 8);
        for (int i =0; i < vals.length; i++) {
            System.out.println(vals[i]);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCount = new HashMap<>();

        for (int i =0; i < nums.length; i++) {
            int val = numCount.getOrDefault(nums[i], 0);
            numCount.put(nums[i], val+ 1);
        }

        int [] counts = new int [nums.length];
        Map<Integer, List<Integer>> countToNum = new HashMap<>();
        int i = 0;
        for(Map.Entry<Integer, Integer> mapE : numCount.entrySet()) {
            List<Integer> lNums =  countToNum.computeIfAbsent(mapE.getValue(), (l) -> new ArrayList<>());
            lNums.add(mapE.getKey());
            counts[i++] = mapE.getValue();
        }

        quickSelect(counts, 0, i-1, k);
        int [] ret = new int[k];

        Set<Integer> countProcessed = new HashSet<>();
        int left =0;
        int right = i-1;
        while (left < k) {
            int count = counts[right];
            if (!countProcessed.contains(count)) {
                countProcessed.add(count);
                List<Integer> lNums = countToNum.get(counts[right]);
                for (Integer n : lNums) {
                    ret[left++] = n; right--;
                }
            }
        }

        return ret;
    }

    void quickSelect(int [] arr, int low, int high, int k) {
        if (low >= high) return;

        int mid = (low + high) / 2;
        int midV = arr[mid];
        int lowV = arr[low];
        int highV = arr[high];

        int pivot = mid;
        if (lowV > midV && lowV < highV) {
            pivot = low;
        } else if (highV > lowV && highV < midV) {
            pivot = high;
        }

        swap(arr, low, pivot);

        int index = partition(arr, low, high);
        if (index == k) return;
        else if (index > k) {
            quickSelect(arr, low, index-1, k);
        } else {
            quickSelect(arr, index+1, high, k);
        }

    }

    int partition(int [] arr, int low, int high) {
       int pIndex = low;
       int pivot = arr[low++];
       while (true) {
           while (low <= high && arr[low] < pivot ) low++;
           while (low <= high && arr[high] > pivot) high--;
           if (low >= high) break;
           swap(arr, low++, high--);
       }
       swap(arr, high, pIndex);
       return high;
    }

    void swap(int [] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }
}
