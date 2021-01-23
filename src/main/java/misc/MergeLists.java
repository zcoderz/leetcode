package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeLists {
    /**
     * @param toMerge
     * @return
     */
    public static List<Range> mergeLists(final Range[] toMerge) {
        List<Range> mergedList = new ArrayList<Range>();
        Arrays.sort(toMerge);
        if (toMerge.length == 0) {
            return mergedList;
        }
        int i = 0;
        Range range = toMerge[i++];
        while (i < toMerge.length) {
            Range rangeNext = toMerge[i];
            if (range.max >= rangeNext.min) {
                range.max = rangeNext.max;
            } else {
                mergedList.add(range);
            }
            i++;
        }
        mergedList.add(range);
        return mergedList;
    }

    public static void main(String[] args) {
        Range[] rangeArr = new Range[2];
        Range r1 = new MergeLists.Range(1, 7);
        Range r2 = new MergeLists.Range(5, 10);
        rangeArr[0] = r2;
        rangeArr[1] = r1;
        List<Range> mergedList = MergeLists.mergeLists(rangeArr);
        int j = 0;
    }

    public static class Range implements Comparable {
        int min;
        int max;

        public Range(int minP, int maxP) {
            this.min = minP;
            this.max = maxP;
        }

        public int compareTo(Object rightO) {
            Range right = (Range) rightO;
            if (this.min < right.min) {
                return -1;
            } else if (this.min > right.min) {
                return 1;
            } else {
                if (this.max < right.max) {
                    return -1;
                } else if (this.max > right.max) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
