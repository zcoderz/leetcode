package sort;

import java.util.*;

public class TopKFrequent {


    public static void main (String [] args) {
        TopKFrequent top = new TopKFrequent();
        int [] nums = {1,2};
        top.topKFrequent(nums , 1);
    }

    public static class NumCount{
        int val;
        int count;

        public NumCount (int val, int count) {
            this.val = val;
            this.count = count;
        }

        public int getVal() {
            return val;
        }

        public int getCount() {
            return count;
        }
    }

    public int[] topKFrequent(int[] nums1, int k) {
        k = 2;
        int []nums = {1,1,1,2,2,3};


        Map<Integer, NumCount> numElements = new HashMap<>(k);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            NumCount theNum = numElements.getOrDefault(num, new NumCount(num, 0));
            theNum.count++;
            numElements.put(nums[i], theNum);
        }
        HashMap<Integer, Integer> count = new HashMap();
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> {
                    return count.get(n1) - count.get(n2);
                });

        NumCount[] largeNums = new NumCount[numElements.size()];

        int i = 0;

        for (Map.Entry<Integer, NumCount> entry: numElements.entrySet()) {
            largeNums[i++] = entry.getValue();
        }

        Arrays.sort(largeNums, new Comparator<NumCount>() {
            @Override
            public int compare(NumCount o1, NumCount o2) {
                return Integer.compare(o1.count, o2.count);
            }
        });

        int []arr = new int[k];
        int size = largeNums.length-1;
        int lastIndex = size-k;
        int j = 0;
        for (i = size; i > lastIndex ; i--) {
            arr[j++] = largeNums[i].val;
        }

        return arr;
    }

}
