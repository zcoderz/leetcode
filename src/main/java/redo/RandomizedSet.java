package redo;

import java.util.*;

public class RandomizedSet {

    ArrayList<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    static Random random = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        int loc = list.size()-1;
        map.put(val, loc);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = map.get(val);
        if (index == null) {
            return false;
        }
        Integer adjustedVal = list.get(list.size()-1);
        map.put(adjustedVal, index);
        list.set(index, adjustedVal);
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

}
