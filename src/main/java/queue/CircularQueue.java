package queue;
/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

class MyCircularQueue {
    //this article on leet code describes a good solution.
    //https://leetcode.com/problems/design-circular-queue/solution/

    public static void main(String [] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        queue.enQueue(1);
        queue.deQueue(); queue.deQueue();

        boolean test = queue.enQueue(2);
        System.out.println(test);
        test = queue.enQueue(3);
        System.out.println(test);
        test = queue.enQueue(4);
        System.out.println(test);
        test = queue.enQueue(5);
        System.out.println(test);
    }

    int [] queue ;
    int head;
    int tail;
    int len;
    boolean isEmpty ;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k]; //allocate the array
        head = 0; tail = 0; len = k;
        isEmpty = true;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {

        if (size()==len) {
            return false;
        }
        queue[head] = value;
        head = moveAhead(head);
        isEmpty=false;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty) {
            return false;
        }
        int size = size();
        tail = moveAhead(tail);
        if (size==1) {
            isEmpty=true;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty) return -1;

        return queue[tail];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty) return -1;
        return queue[moveBack(head)];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size()==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size()==len;
    }

    public int size() {
        if(isEmpty) return 0;

        int theHead = moveBack(head);
        if(theHead >= tail) {
            return theHead-tail+1;
        } else {
            return theHead + 1 + len-tail;
        }
    }

    private int moveAhead(int i ) {
        return (i+1) % len;
    }

    private int moveBack(int i) {
        if (i==0) {
            return len-1;
        }
        else return i-1;
    }
}

