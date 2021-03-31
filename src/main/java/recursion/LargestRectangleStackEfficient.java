package recursion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

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
 * This approach results in time O(n)
 */
public class LargestRectangleStackEfficient {

    public static void main(String[] args) {
        //int[] heights = {2, 1, 5, 6, 4, 11, 5};
        //int [] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        int [] heights = {1,1};
        LargestRectangleStackEfficient lg = new LargestRectangleStackEfficient();
        int area = lg.largestRectangleArea(heights);
        System.out.println(area);
    }

    int[] heights;
    int maxArea = 0;


    /**
     * the equations in stack based solution are tricky to conceptualize.
     * look at the comments below to understand how to calculate width
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        this.heights = heights;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //add sentinel

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                //when the above condition is met , it means that the element on top of stack is higher than current
                //index based on "heights[stack.peek()] > heights[i]"
                //and by definition all elements between the current index on top of stack and i must be greater in height
                //than the height on top of stack.
                //so you get height via current stack index height
                //and you get width by (i-1-index on top of stack). you subtract 1 from i to exclude current index
                int h = heights[stack.pop()];
                int priorIndex = stack.peek();
                int width = i - priorIndex - 1; //prior index needs to be excluded hence subtract 1
                int area = width * h;
                maxArea = Math.max(area, maxArea);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int h = heights[stack.pop()];
            //here any elements left on stack are greater than the last index
            //so area is just there height * length - index (subtract 1 to remove index of element left on stack)
            int width = heights.length - stack.peek() -1;
            int area = h * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
