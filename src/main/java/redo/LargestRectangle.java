package redo;

import java.util.Stack;

public class LargestRectangle {

    public static void main(String [] args) {
        int [] heights = {5,4,1,2};
        LargestRectangle largest = new LargestRectangle();
        int area = largest.largestRectangleArea(heights);
        System.out.println(area);
    }
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> coordinateStack = new Stack<>();
        coordinateStack.push(-1);
        int maxArea = 0;
        for (int i =0; i < heights.length; i++) {
            while (coordinateStack.peek() != -1 && heights[coordinateStack.peek()] > heights[i]) {
                int height = heights[coordinateStack.pop()];
                int width = i - coordinateStack.peek()-1;
                int area = width * height;
                maxArea = Math.max(maxArea, area);
            }
            coordinateStack.push(i);
        }
        while (coordinateStack.peek() != -1) {
            int height = heights[coordinateStack.peek()];
            coordinateStack.pop();
            int width = heights.length - (coordinateStack.peek() +1);
            int area = height * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
