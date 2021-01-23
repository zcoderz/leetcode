package design;

import java.util.*;

public class RandomizedSet {
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> elements = new ArrayList<>();
    private Random ran = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            int size = elements.size();
            map.put(val, size - 1);
            elements.add(val);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val);
            Collections.swap(elements, index, elements.size() - 1);
            map.remove(val);
            val = elements.get(index);
            map.put(val, index);
            elements.remove(elements.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int size = elements.size() - 1;
        int index = ran.nextInt(size);
        return elements.get(index);
    }
}
