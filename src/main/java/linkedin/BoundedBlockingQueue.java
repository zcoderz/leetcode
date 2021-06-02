package linkedin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
    private final Lock aLock = new ReentrantLock();
    private final Condition conditionTake = aLock.newCondition();
    private final Condition conditionPut = aLock.newCondition();
    int capacity;
    Queue<Integer> queue = new LinkedList<>();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        aLock.lock();
        try {
            while (queue.size() == capacity) {
                conditionPut.await();
            }
            queue.add(element);
        } finally {
            conditionTake.signalAll();
            aLock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        aLock.lock();
        Integer val;
        try {
            while (queue.isEmpty()) {
                conditionTake.await();
            }
            return queue.poll();
        } finally {
            conditionPut.signalAll();
            aLock.unlock();
        }
    }

    public int size() {
        aLock.lock();
        try {
            return queue.size();
        } finally {
            aLock.unlock();
        }
    }
}
