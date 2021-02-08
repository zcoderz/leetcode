package frequent.medium;

import java.util.HashMap;


/**
 * 146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * Follow up:
 * Could you do get and put in O(1) time complexity?
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * IMP-1: Very very common interview question
 */
public class LRUCache {

    private int capacity;
    private HashMap<Integer, Entry> itemMap = new HashMap<Integer, Entry>();
    private Entry head;
    private Entry tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        cache.get(1);
    }

    public int get(int key) {
        if (itemMap.containsKey(key)) {
            Entry theKey = itemMap.get(key);
            adjustTail(theKey);
            moveToFront(theKey);
            return theKey.val;
        } else {
            return -1;
        }
    }

    void adjustTail(Entry entry) {
        if (tail == entry && tail.previous != null) {
            tail = tail.previous;
        }
    }

    public void put(int key, int value) {
        Entry entry = itemMap.get(key);
        if (null != entry) {
            entry.val = value;
            get(key);
            return;
        }
        Entry theEntry = new Entry(key, value);
        theEntry.next = head;
        if (head != null) {
            head.previous = theEntry;
        }
        head = theEntry;

        if (tail == null) {
            tail = theEntry;
        }
        itemMap.put(key, theEntry);
        adjustCapacity();
    }

    void adjustCapacity() {
        if (itemMap.size() > capacity) {
            itemMap.remove(tail.getKey());
            tail = tail.previous;
            tail.next = null;
        }
    }

    void moveToFront(final Entry entry) {
        //if entry.previous == null, then the entry must be the head.
        if (entry.previous != null) {
            entry.previous.next = entry.next;
            if (entry.next != null) {
                entry.next.previous = entry.previous;
            }
            entry.previous = null;
            entry.next = head;
            head.previous = entry;
            head = entry;
        }
    }

    class Entry {
        private int val;
        private int key;
        private Entry previous;
        private Entry next;

        public Entry(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public int getKey() {
            return key;
        }

        public Entry getPrevious() {
            return previous;
        }

        public Entry getNext() {
            return next;
        }

    }

    ;
}
