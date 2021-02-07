package google.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 284. Peeking Iterator
 * <p>
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 * <p>
 * Example:
 * <p>
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 * <p>
 * Call next() gets you 1, the first element in the list. Now you call peek() and it returns 2, the next element.
 * Calling next() after that still return 2. You call next() the final time and it returns 3, the last element. Calling
 * hasNext() after that should return false. Follow up: How would you extend your design to be generic and work with all
 * types, not just integer?
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
