package hash_maps;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 *
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 *
 * Example:
 *
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * Output:
 * 2
 *
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * IMP-1: This is a very clever approach. Should practice!
 */
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
