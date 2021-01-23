package google.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 281. Zigzag Iterator Given two 1d vectors, implement an iterator to return their elements alternately.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: v1 = [1,2] v2 = [3,4,5,6] Output: [1,3,2,4,5,6] Explanation: By calling next repeatedly until hasNext returns
 * false, the order of elements returned by next should be: [1,3,2,4,5,6].
 */
public class ZigZagIterator {

    //index to the current list in array that is being processed
    int currIndex = 0;
    //lists to be processed
    List<Integer>[] lists;
    //list of current indexes for each of the lists
    Integer[] listIndexes;
    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        int count = 2;
        lists = new IntegerList[count];
        lists[0] = new IntegerList(v1);
        lists[1] = new IntegerList(v2);
        listIndexes = new Integer[count];
        for (int i = 0; i < count; i++) {
            listIndexes[i] = 0;
        }
    }

    public static void main(String[] args) {
        List<Integer> v1 = Arrays.asList(1, 2);
        List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
        ZigZagIterator zigZag = new ZigZagIterator(v1, v2);
        while (zigZag.hasNext()) {
            System.out.print(zigZag.next());
        }
    }

    /**
     * if the index or the element at current list is at end, move to the next list output the element in curr list and
     * move its index ahead
     *
     * @return
     */
    public int next() {
        while (listIndexes[currIndex] == lists[currIndex].size()) {
            currIndex = getNextIndex();
        }
        int val = lists[currIndex].get(listIndexes[currIndex]);
        listIndexes[currIndex] = listIndexes[currIndex] + 1;
        currIndex = getNextIndex();
        return val;
    }

    /**
     * return true if any of the lists has a next element available
     *
     * @return
     */
    public boolean hasNext() {
        int iterations = 0;
        while (listIndexes[currIndex] == lists[currIndex].size() && iterations != listIndexes.length) {
            currIndex = getNextIndex();
            iterations++;
        }
        return listIndexes[currIndex] != lists[currIndex].size();
    }

    /**
     * internal helper method to get next list
     *
     * @return
     */
    int getNextIndex() {
        return (currIndex + 1) % lists.length;
    }

    /**
     * need this class so that we can store the lists in an array. problem is that in java you cant create an array of
     * generic classes hence this is used as a workaround.
     */
    public static class IntegerList extends ArrayList<Integer> {
        public IntegerList(List<Integer> list) {
            super(list);
        }
    }
}
