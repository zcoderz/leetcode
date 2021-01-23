package design;

import utils.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * this is the most asked interview question since a long time. i have personally been asked to write this code in past
 * interviews. practice it.
 * <p>
 * the below code is not the most optimal but its fast to implement and is nicely written in reusable blocks important
 * to write this code via reusable methods as this is more a design question than an algorithm question
 */
public class LRUCache {

    int capacity;
    Map<Integer, ListNode> nodeMap = new HashMap<>();
    //very important to keep these sentinels as they make the code much simpler to write
    //these are place holders sentinels to for tail and head
    //the actual head is next to head
    //the actual tail is before the tail
    ListNode head;
    ListNode tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode();
        tail = new ListNode();
        //initialize head to point to tail and vice versa
        head.next = tail;
        tail.prior = head;
    }

    public static void main(String[] args) {

        //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
        //[[3],[1,1],[2,2],[3,3],[4,4],
        //
        // [4],[3],[2],[1],
        // [5,5],
        // [1],[2],[3],[4],[5]]

        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.get(2);
        cache.put(1, 1);
        cache.put(4, 1);
        cache.get(2);

//        LRUCache cache = new LRUCache(1);
//        cache.put(2, 1);
//        cache.get(2);

//        LRUCache cache = new LRUCache(3);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.put(3, 3);
//        cache.put(4, 4);
//        cache.get(4);
//        cache.get(3);
//        cache.get(2);
//        cache.get(1);
//        cache.put(5, 5);
//        int v = cache.get(1);
//        System.out.println(v);
//        v = cache.get(2);
//        System.out.println(v);
//        v = cache.get(3);
//        System.out.println(v);
//        v = cache.get(4);
//        System.out.println(v);
//        v = cache.get(5);
//        System.out.println(v);
    }

    /**
     * remove node and adjust pointers
     *
     * @param node
     */
    void removeNode(ListNode node) {
        nodeMap.remove(node.key);
        node.prior.next = node.next;
        node.next.prior = node.prior;
    }

    /**
     * add after head
     *
     * @param node
     */
    void addNode(ListNode node) {
        nodeMap.put(node.key, node);
        node.next = head.next;
        node.prior = head;
        head.next.prior = node;
        head.next = node;
    }

    /**
     * remove and add does the same
     *
     * @param node
     */
    void moveToHead(ListNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * get the actual tail and adjust pointers related to popping of the tail
     *
     * @return
     */
    ListNode popTail() {
        ListNode actTail = tail.prior;
        tail.prior = tail.prior.prior;
        return actTail;
    }

    /**
     * leverage the methods above to write a simple algo for put
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (nodeMap.size() == capacity && !nodeMap.containsKey(key)) {
            ListNode node = popTail();
            removeNode(node);
        }
        ListNode node = nodeMap.get(key);
        if (node != null) {
            // if exists , update value and move to head
            node.val = value;
            moveToHead(node);
        } else {
            //add a new node via addNode method
            node = new ListNode(key, value);
            addNode(node);
        }
    }

    /**
     * leverage methods already created to write a simple solution for get
     *
     * @param key
     * @return
     */
    public int get(int key) {
        ListNode node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        //move node to head if its in cache
        moveToHead(node);
        return node.val;
    }
}
