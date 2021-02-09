package queue;

/**
 * 622. Design Circular Queue
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the
 * operations are performed based on FIFO (First In First Out) principle and the last position is connected
 * back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in
 * front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 *
 */

class MyCircularQueue {
    //this article on leet code describes a good solution.
    //https://leetcode.com/problems/design-circular-queue/solution/

    int[] queue;
    int head;
    int tail;
    int len;
    boolean isEmpty;
    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        queue = new int[k]; //allocate the array
        head = 0;
        tail = 0;
        len = k;
        isEmpty = true;
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        queue.enQueue(1);
        queue.deQueue();
        queue.deQueue();

        boolean test = queue.enQueue(2);
        System.out.println(test);
        test = queue.enQueue(3);
        System.out.println(test);
        test = queue.enQueue(4);
        System.out.println(test);
        test = queue.enQueue(5);
        System.out.println(test);
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {

        if (size() == len) {
            return false;
        }
        queue[head] = value;
        head = moveAhead(head);
        isEmpty = false;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty) {
            return false;
        }
        int size = size();
        tail = moveAhead(tail);
        if (size == 1) {
            isEmpty = true;
        }
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty) return -1;

        return queue[tail];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty) return -1;
        return queue[moveBack(head)];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return size() == len;
    }

    public int size() {
        if (isEmpty) return 0;

        int theHead = moveBack(head);
        if (theHead >= tail) {
            return theHead - tail + 1;
        } else {
            return theHead + 1 + len - tail;
        }
    }

    private int moveAhead(int i) {
        return (i + 1) % len;
    }

    private int moveBack(int i) {
        if (i == 0) {
            return len - 1;
        } else return i - 1;
    }
}

