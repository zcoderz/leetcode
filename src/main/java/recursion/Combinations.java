package recursion;

import java.util.*;

/**
 * nice problem to practice
 */
public class Combinations {

    //dont need a set since we will be iterating by design on distinct elements
    //Set<String> processedCombinations = new HashSet<>();
    List<List<Integer>> possibleCombinations = new ArrayList<>();
    int k;
    int n;

    public static void main(String[] args) {
        Combinations com = new Combinations();
        com.combine(4, 2);
        for (List<Integer> l : com.possibleCombinations) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        processCombine(new LinkedList<>(), 1);
        return possibleCombinations;
    }

    /**
     *
     * @param numItems list of items you have so far processed
     * @param startingIndex set this to be one ahead from the last index you processed
     *                      so that you are iterating on distinct indexes
     */
    void processCombine(LinkedList<Integer> numItems, Integer startingIndex) {
        if (numItems.size() == k) {
            possibleCombinations.add(new ArrayList<>(numItems));
            return;
        }

        for (int i = startingIndex; i <= n; i++) {
            numItems.add(i);
            processCombine(numItems, i + 1);
            numItems.removeLast();
        }
    }

    /**
     * this is a conceptually simple implementation at high level, but i felt the implementation is fairly tricky
     * the code is from leetcode solutions.
     *
     * you are generating lexicographically sorted combinations :
     *
     * 0,0,1,1 : 0,1,0,1 : 0,1,1,0 : 1,0,0,1 : 1,0,1,0 : 1,1,0,0
     * 4,3,2,1 : 4,3,2,1 : 4,3,2,1 : 4,3,2,1 : 4,3,2,1 : 4,3,2,1
     * 1,2     : 1,3     : 2,3     : 1,4     : 2,4     : 3,4
     *
     * @param n
     * @param k
     */
    public void combineTwo(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1); //add as a sentinel

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        //the while loop breaks when you have processed the first k combinations
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            //
            // if nums[j] + 1 == nums[j + 1] increase nums[j] by one
            // the loop breaks when nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1); //increment the number at jth index
        }
        this.possibleCombinations = output;
    }
}
