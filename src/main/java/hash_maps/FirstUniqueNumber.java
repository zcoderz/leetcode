package hash_maps;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


/**
 * 1429. First Unique Number
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 *
 *
 * Example 1:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * Output:
 * [null,2,null,2,null,3,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(5);            // the queue is now [2,3,5,5]
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3
 * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // return -1
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
