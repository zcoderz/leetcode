package stack;

import java.util.Stack;

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * simple fun question....!
 */
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer[]> stackMin = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public static void main (String[] args) {
        MinStack mStack = new MinStack();
        mStack.push(512);
        mStack.push(-1024);
        mStack.push(-1024);
        mStack.push(512);

        mStack.pop();
        int i = mStack.getMin();
        System.out.println(i);

        mStack.pop();
        i = mStack.getMin();
        System.out.println(i);

        mStack.pop();
        i = mStack.getMin();
        System.out.println(i);

    }

    public void push(int x) {
        stack.push(x);
        if (!stackMin.isEmpty()) {
            Integer[] minStack = stackMin.peek();
            if (x < minStack[0]) {
                stackMin.push(new Integer[]{x, 1});
            } else if (x == minStack[0]) {
                minStack[1] = minStack[1] + 1;
            }
        } else {
            stackMin.push(new Integer[]{x, 1});
        }
    }

    public void pop() {
        Integer val = stack.pop();
        if (stackMin.peek()[0].equals(val)) {
            if (stackMin.peek()[1] == 1) {
                stackMin.pop();
            } else {
                stackMin.peek()[1] = stackMin.peek()[1] - 1;
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stackMin.peek()[0];
    }
}
