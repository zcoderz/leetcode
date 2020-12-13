package recursion;

import java.util.Stack;

/**
 * O(N) solution for largest Rectangle
 */
public class LargestRectangleStack_ON {


    public static void main(String[] args) {
        //int[] heights = {2, 1, 5, 6, 4, 11, 5};
        int [] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        LargestRectangleStack_ON lg = new LargestRectangleStack_ON();
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
        stack.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                //get the height of last element in stack but pop it
                //need to pop it so that we can know index of element before it that was smaller than it
                //remember area is from current length to that of length of index smaller than the last popped element
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
            // similar to above concept except we are taking width from end of array to element before the popped one
            int width = heights.length - stack.peek() -1;
            int area = h * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
