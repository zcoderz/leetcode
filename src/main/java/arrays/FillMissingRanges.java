package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FillMissingRanges {

    public static void main(String[] args) {
        FillMissingRanges ranges = new FillMissingRanges();
//        int [] nums = {0,1,3,50,75};
//        List<String> strRanges = ranges.findMissingRanges(nums, 0, 99);
        int[] nums = {-1};

        List<String> strRanges = ranges.findMissingRanges(nums, -1, 0);
        Collections.reverse(strRanges);

        for (String range : strRanges) {
            System.out.println(range);
        }
    }

    /**
     * Thinking through the cases becomes simpler if we considered thinking from right edge with first limit between
     * edge and first element in exclusion list from right. then walk back towards the start, keeping count of each
     * range until you reach the beginning
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> strVals = new ArrayList<>();
        int j = nums.length - 1;
        int higher = upper;

        for (; j >= 0; j--) {
            int val = nums[j];
            if (val > lower && val <= higher) {
                // if in range
                int tmpLower = Math.max(val + 1, lower);
                if (higher >= tmpLower) {
                    String strVal = formatOutput(tmpLower, higher);
                    strVals.add(strVal);
                }
            }
            higher = val - 1;
        }
        //handle the case where we may have missed the starting edge from lower to first element in exclusion list
        //this case will also handle the scenario where exclusion list is empty
        if (higher >= lower) {
            String strVal = formatOutput(lower, higher);
            strVals.add(strVal);
        }
        return strVals;
    }

    private String formatOutput(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        } else {
            return lower + "->" + upper;
        }
    }
}
