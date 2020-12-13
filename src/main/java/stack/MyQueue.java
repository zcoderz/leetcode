package stack;


import java.util.Stack;

/**
 * implement behavior of a queue via using two stacks.
 * structure such that all operations are done in amortized O(1) time
 */
public class MyQueue {
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();
    Integer head ;

    /** Initialize your data structure here. */
    public MyQueue() {
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stackA.isEmpty()) {
            head = x;
        }
        stackA.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!stackB.isEmpty()) {
            return stackB.pop();
        }
        while (!stackA.isEmpty()) {
            Integer v = stackA.pop();
            stackB.push(v);
        }
        return stackB.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stackB.isEmpty()) return head;
        return stackB.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackB.isEmpty() && stackA.isEmpty();
    }

}
