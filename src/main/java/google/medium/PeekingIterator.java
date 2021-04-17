package google.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 284. Peeking Iterator
 * Design an iterator that supports the peek operation on a list in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 * PeekingIterator(int[] nums) Initializes the object with the given integer array nums.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * bool hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 *
 *
 * Example 1:
 *
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 *
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 *
 * IMP-3 : its more of a design problem
 */
public class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    boolean currNonInitialized = true;
    Integer currValue;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public static void main(String[] args) {
        List<Integer> testArr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Iterator<Integer> iter = testArr.iterator();
        PeekingIterator pI = new PeekingIterator(iter);
        System.out.println(pI.peek());
        System.out.println(pI.next());
        System.out.println(pI.next());
        System.out.println(pI.peek());
        System.out.println(pI.hasNext());
        System.out.println(pI.peek());
        System.out.println(pI.next());
        System.out.println(pI.peek());
        System.out.println(pI.next());
    }

    /**
     * Returns the next element in the iteration without advancing the iterator. track the current value by calling next
     * , but knowing the value is tracked so subsequent call to next can return the already tracked value.
     *
     * @return
     */
    public Integer peek() {
        if (currNonInitialized) {
            currValue = iterator.next();
            currNonInitialized = false;
        }
        return currValue;
    }

    /**
     * return the next element just like a regular iterator would special case if that if peek was previously called
     * return the value being peeked at and reset flag for peek
     *
     * @return
     */
    public Integer next() {
        if (!currNonInitialized) {
            currNonInitialized = true;
            return currValue;
        } else {
            return iterator.next();
        }
    }

    /**
     * Same as regular iterator if peek was previously called just return true else call the iterator's hasNext
     *
     * @return
     */
    public boolean hasNext() {
        if (!currNonInitialized) {
            return true;
        }
        return iterator.hasNext();
    }
}
