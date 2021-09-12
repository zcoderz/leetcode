package redo;

public class LargestRectangleDivideAndConquer {

    public static void main(String [] args) {
        LargestRectangleDivideAndConquer largest = new LargestRectangleDivideAndConquer();
        int [] heights = {2, 1, 5, 6, 2, 3};
        int area = largest.largestRectangleArea(heights);
        System.out.println(area);
    }

    public int largestRectangleArea(int[] heights) {
        return calcRecurse(heights, 0, heights.length-1);
    }

    int calcRecurse(int[] heights, int left, int right) {
        if (left > right) {
            return 0;
        }
        int minHeight = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i =left; i <= right; i++) {
            int newMinHeight = Math.min(minHeight, heights[i]);
            if (newMinHeight != minHeight) {
                minIndex = i;
                minHeight = newMinHeight;
            }
        }
        int minArea = minHeight * (right-left+1);

        int leftArea = calcRecurse(heights, left, minIndex-1);
        int rightArea = calcRecurse(heights, minIndex+1, right);
        return Math.max(minArea, Math.max(leftArea, rightArea));
    }
}
