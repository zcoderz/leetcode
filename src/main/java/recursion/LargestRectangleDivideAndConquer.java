package recursion;

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
 * 1. Calculate the area as min height * widest spread.
 * 2. Divide the rectangle into two sub rectangles, on left and right of the min height and repeat
 *
 * The division of work per iteration makes the code performant. However, division doesnt help much
 * if the heights in the rectangle were already sorted. For an efficient solution that works on sorted heights
 * see implementation in LongestRectangleSegmentTrees
 * See LargestRectangleInHistogram for a simpler implementation
 *
 *
 * Leetcode's visualization diagrams make explanation of min height and widest spread works intuitive
 * From leet code :
 * This approach relies on the observation that the rectangle with maximum area will be the maximum of:
 *
 * The widest possible rectangle with height equal to the height of the shortest bar.
 *
 * The largest rectangle confined to the left of the shortest bar(subproblem).
 *
 * The largest rectangle confined to the right of the shortest bar(subproblem).
 */
public class LargestRectangleDivideAndConquer {

    public static void main(String[] args) {
        //int[] heights = {2, 1, 5, 6, 4, 11, 5};
        //int [] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        int [] heights = {2,1,5,6,2,3};
        LargestRectangleDivideAndConquer lg = new LargestRectangleDivideAndConquer();
        int area = lg.largestRectangleArea(heights);
        System.out.println(area);
    }


    public int calculateArea(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i < end; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        int minA = heights[minIndex] * (end-start+1);
        int lA = calculateArea(heights, start, minIndex-1);
        int rA = calculateArea(heights, minIndex+1, end);
        return Math.max(minA, Math.max(lA, rA));
    }

    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }
}
