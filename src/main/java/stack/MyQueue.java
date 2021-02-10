package stack;


import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * mplement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a stack,
 * which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively.
 * You may simulate a stack using a list or deque (double-ended queue)
 * as long as you use only a stack's standard operations.
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 *
 * In pop push elements to stackB and return from there
 * special case : for peek if stack b is empty, keep pointer to head of stack A and return from there
 */
public class MyQueue {
    Integer head;
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (stackA.isEmpty()) {
            head = x;
        }
        stackA.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!stackB.isEmpty()) {
            return stackB.pop();
        }
        while (!stackA.isEmpty()) {
            Integer v = stackA.pop();
            stackB.push(v);
        }
        return stackB.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stackB.isEmpty()) return head;
        return stackB.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackB.isEmpty() && stackA.isEmpty();
    }

}
