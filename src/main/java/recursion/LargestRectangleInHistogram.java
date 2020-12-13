package recursion;

public class LargestRectangleInHistogram {

    int[] heights;
    int maxArea = 0;
    public int largestRectangleArea(int[] heights) {
        this.heights = heights;

        for (int i =0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j =i ; j < heights.length; j++) {
                minHeight = Math.min(heights[j], minHeight);
                int currArea = minHeight * (j-i+1) ;
                this.maxArea = Math.max(maxArea, currArea);
            }
        }
        return maxArea;
    }

}
