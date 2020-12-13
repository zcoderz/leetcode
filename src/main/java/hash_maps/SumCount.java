package hash_maps;

import java.util.HashMap;
import java.util.Map;

public class SumCount {

    int count = 0;
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int [][] arrays = {A, B, C, D};
        processCount (arrays);
        return count;
    }

    void processCount(int [][]arrays) {
        Map<Integer, Integer> arrayCount = new HashMap<>();
        processMapCount(arrays, arrayCount, 0, 0);
        processCounterMapCount(arrays, arrayCount, arrays.length/2, 0);
    }

    /**
     * find complements of values in the second half that exist
     * @param arrays
     * @param mapCount
     * @param arrIndex
     * @param sum
     */
    void processCounterMapCount(int [][] arrays, Map<Integer, Integer> mapCount, int arrIndex, int sum) {
        if (arrIndex == arrays.length) {
            sum *=-1;
            if (mapCount.containsKey(sum)) {
                count += mapCount.get(sum);
            }
        } else {
            for (int i : arrays[arrIndex]) {
                processCounterMapCount(arrays, mapCount, arrIndex+1, sum + i);
            }
        }
    }

    /**
     * Clever approach. find count of the sums that occur for first half
     *
     * @param arrays
     * @param mapCount
     * @param arrIndex
     * @param sum
     */
    void processMapCount(int [][]arrays , Map<Integer, Integer> mapCount, int arrIndex, int sum) {
        if (arrIndex == arrays.length/2) {
            mapCount.put(sum, mapCount.getOrDefault(sum, 0) + 1);
        } else {
            for (int i : arrays[arrIndex]) {
                processMapCount(arrays, mapCount, arrIndex+ 1, sum + i);
            }
        }
    }

}
