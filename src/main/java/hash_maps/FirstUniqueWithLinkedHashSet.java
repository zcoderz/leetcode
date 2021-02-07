package hash_maps;

import java.util.*;

/**
 * 1429. First Unique Number
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue,
 * and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 */
public class FirstUniqueWithLinkedHashSet {

    //see documentation of linked hash set here.
    //the class is a combination of a doubly linked list and a hash set
    //thus via the linked list its able to maintain the order in which data was inserted
    LinkedHashSet<Integer> uniqueNumsLinkedHash = new LinkedHashSet<>();
    Map<Integer, Integer> intToCountMap = new HashMap<>();

    public FirstUniqueWithLinkedHashSet(int[] nums) {
        for (int i =0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int showFirstUnique() {
        if (!uniqueNumsLinkedHash.isEmpty()) {
            return uniqueNumsLinkedHash.iterator().next();
        }
        return -1;
    }

    public void add(int value) {
        int ct = intToCountMap.getOrDefault(value, 0);
        ct++;
        intToCountMap.put(value, ct);
        if (ct > 1) {
            uniqueNumsLinkedHash.remove(value);
        } else {
            uniqueNumsLinkedHash.add(value);
        }
    }
}
