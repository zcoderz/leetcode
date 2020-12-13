package recursion;

import utils.SegmentTreeNodeRectangleArea;

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
