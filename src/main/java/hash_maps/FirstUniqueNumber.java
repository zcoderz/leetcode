package hash_maps;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


/**
 *
 *
 * see FirstUniqueWithLinkedHashSet which is another way to solve the same problem.
 */
public class FirstUniqueNumber {

    public static void main(String [] args) {
        int [] nums = {2,3,5};
        FirstUniqueNumber firstUniqueNumber = new FirstUniqueNumber(nums);
        int num = firstUniqueNumber.showFirstUnique();
        System.out.println(num);
        firstUniqueNumber.add(5);
        num = firstUniqueNumber.showFirstUnique();
        System.out.println(num);
        firstUniqueNumber.add(2);
        num = firstUniqueNumber.showFirstUnique();
        System.out.println(num);
        firstUniqueNumber.add(3);
        num = firstUniqueNumber.showFirstUnique();
        System.out.println(num);
    }

    /**
     * a class to represent the number count
     */
    public static class NumberCount implements Comparable  {
        int num;
        int count;
        int time;
        //order based on time
        public int compareTo(Object o) {
            NumberCount other = (NumberCount) o;
            if (this.count == other.count ) {
                return Integer.compare(this.time, other.time);
            } else {
                return Integer.compare(this.count, other.count);
            }
        }

    }

    //store the data in a tree set so it is ordered based on time
    TreeSet<NumberCount> numberCounts = new TreeSet<>();
    Map<Integer, NumberCount> intToNumMap = new HashMap<>();
    int time = 0;
    public FirstUniqueNumber(int[] nums) {
        for (int i =0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    /**
     * return the first number in the treeset numberCounts
     * @return
     */
    public int showFirstUnique() {
        Iterator<NumberCount> iterator = numberCounts.iterator();
        if (iterator.hasNext()) {
            return iterator.next().num;
        }
        return -1;
    }

    /**
     * update count in hash map that maps number to its corresponding data
     * if this is the first insertion of a number add it to the treeset
     * otherwise remove it from teh treeset
     * @param value
     */
    public void add(int value) {
        NumberCount count = intToNumMap.get(value);
        if (count != null) {
            if (count.count == 1) {
                numberCounts.remove(count);
            }
            count.count++;
        } else {
            count = new NumberCount();
            count.count = 1;
            count.time = time++;
            count.num = value;
            numberCounts.add(count);
            intToNumMap.put(value, count);
        }
    }

}
