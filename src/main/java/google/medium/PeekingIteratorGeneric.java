package google.medium;

import java.util.Iterator;

/**
 * Same as PeekingIterator but implemented to solve generically.
 *
 * @param <T>
 *
 *  IMP-3: Design problem
 */
public class PeekingIteratorGeneric<T> implements Iterator<T> {
    Iterator<T> iterator;
    boolean currNonInitialized = true;
    T currValue;

    public PeekingIteratorGeneric(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        if (currNonInitialized) {
            currValue = iterator.next();
            currNonInitialized = false;
        }
        return currValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        if (!currNonInitialized) {
            currNonInitialized = true;
            return currValue;
        } else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (!currNonInitialized) {
            return true;
        }
        return iterator.hasNext();
    }

}
