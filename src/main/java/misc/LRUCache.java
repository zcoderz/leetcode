package misc;

import java.util.HashMap;


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
