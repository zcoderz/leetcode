package search;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MergeRange {
    TreeSet<Range> leftSet = new TreeSet<>();
    TreeSet<Range> rightSet = new TreeSet<>();

    public static void main(String [] args) {

        MergeRange range = new MergeRange();
        int [][] theR = { {1,7}, {4,11}, {10, 17}};
        int [][] mergedR = range.merge(theR);
        int j = 2;
    }
    private class Range  implements Comparable {
        int left;
        int right;
        Boolean compareLeft;

        public Range(int left, int right, Boolean compareLeft) {
            this.left = left;
            this.right = right;
            this.compareLeft = compareLeft;
        }

        public boolean equals (Object r) {
            Range right = (Range) r;
            if (compareLeft) {
                if ((right.left == left)) {
                    return true;
                }
            } else {
                if ((right.right == this.right)) {
                    return true;
                }
            }
            return false;
        }

        public int compareTo(Object r) {
            Range right = (Range) r;
            if (compareLeft) {
                return Integer.compare(this.left, right.left);
            } else {
                return Integer.compare(this.right, right.right);
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            int [] theRange = intervals[i];
            Range leftRange = new Range(theRange[0], theRange[1], true);
            clearSubRanges(leftRange);
            mergeLeftRange(leftRange);
            Range rightRange = new Range(leftRange.left, leftRange.right, false);
            mergeRightRange(rightRange);
            rightSet.add(rightRange);
            leftRange.left = rightRange.left;
            leftRange.right = rightRange.right;
            leftSet.add(leftRange);

        }
        int [][] ranges = new  int [leftSet.size()][2];
        int i = 0;
        for (Range r: leftSet) {
            int [] theR = new int [2];
            theR[0] = r.left;
            theR[1] = r.right;
            ranges[i++] = theR;
        }
        return ranges;
    }

    void clearSubRanges(Range theRange) {
        SortedSet<Range> greater = leftSet.tailSet (theRange);
        for (Range r: greater) {
            if (r.right <= theRange.right) {
                leftSet.remove(r);
                r.compareLeft = false;
                rightSet.remove(r);
            }
        }
    }

    void mergeLeftRange(Range theRange) {
        Range left = leftSet.lower(theRange);
        if (left == null) return;
        if (left.right >= theRange.left) {
            //left element is spanning inside range
            theRange.left = left.left;
            if (left.right > theRange.right) {
                theRange.right = left.right;
            }
            leftSet.remove(left);
            left.compareLeft = false;
            rightSet.remove(left);
        }
    }

    void mergeRightRange(Range theRange) {
        Range right = rightSet.ceiling(theRange);
        if (right == null) return;
        if (right.left <= theRange.right) {
            theRange.right = right.right;
            if (right.left < theRange.left) {
                theRange.left = right.left;
            }
            rightSet.remove(right);
            right.compareLeft = true;
            leftSet.remove(right);
        }
    }
}
