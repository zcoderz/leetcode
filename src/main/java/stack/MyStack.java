package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * Implement a last in first out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a queue, which means only push to back, peek/pop from front,
 * size, and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively.
 * You may simulate a queue using a list or deque (double-ended queue),
 * as long as you use only a queue's standard operations.
 *
 */
public class MyStack {
    Queue<Integer> queue = new LinkedList<>();

    public MyStack() {

    }

    /**
     * Push element x onto stack. This is hard to conceptualize. But if you invert the queue on each insertion, you end
     * up with the queue looking like a stack. Draw it out on a white board.
     *
     * @param x
     */
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }

}
