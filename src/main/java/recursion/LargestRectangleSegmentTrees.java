package recursion;

import utils.SegmentTreeNodeRectangleArea;

/**
 * 84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 * IMP-1: Common question. Below is a simple approach where we :
 *
 * Use this to understand practical use cases for Segment Trees
 *
 * This is same approach as that in LargestRectangleDivideAndConquer except that we use segment trees
 * to quickly find minimum height over a range
 *
 */
public class LargestRectangleSegmentTrees {

    public static void main (String [] args) {
        int [] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        LargestRectangleSegmentTrees lg = new LargestRectangleSegmentTrees();
        int area = lg.largestRectangleArea(heights);
        System.out.println(area);
    }

    /**
     * this is the same as divide and conquer approach except that it doesnt run into penalty of O(n^2)
     * when the array is sorted , which occurs because every time we have to find the smallest element by searching
     * a large sub array.
     * the algorithm's time complexity is O(n*log(n))
     * @param heights
     * @return
     */
    int largestRectangleArea(int []heights) {
        if (heights.length== 0) return 0;
        // first build a segment tree
        SegmentTreeNodeRectangleArea root = SegmentTreeNodeRectangleArea.
                buildSegmentTree(heights, 0, heights.length - 1);
        // next calculate the maximum area recursively
        return calculateMax(heights, root, 0, heights.length - 1);
    }

    /**
     * this is the same code as divide and conquer approach
     * except that it is quickly querying the segment tree that was constructed above to find index
     * of the minimum element in the range start to end.
     * @param heights
     * @param root
     * @param start
     * @param end
     * @return
     */
    int calculateMax(int [] heights, SegmentTreeNodeRectangleArea root, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return heights[start];
        }
        int minIndex = root.query(root, heights, start, end);
        int leftMax = calculateMax(heights, root, start, minIndex - 1);
        int rightMax = calculateMax(heights, root, minIndex + 1, end);
        int minMax = heights[minIndex] * (end - start + 1);
        return Math.max( Math.max(leftMax, rightMax), minMax );
    }

}
