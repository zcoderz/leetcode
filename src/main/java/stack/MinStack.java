package stack;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer[]> stackMin = new Stack<>();

    public static void main(String [] args) {
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

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (!stackMin.isEmpty()) {
            Integer []minStack = stackMin.peek();
            if (x < minStack[0]) {
                stackMin.push(new Integer[] {x, 1});
            } else if (x == minStack[0]) {
                minStack[1] = minStack[1] + 1;
            }
        } else {
            stackMin.push(new Integer[] {x, 1});
        }
    }

    public void pop() {
        Integer val = stack.pop();
        if (stackMin.peek()[0].equals(val)) {
            if (stackMin.peek()[1]==1) {
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
