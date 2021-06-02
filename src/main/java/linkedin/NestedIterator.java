package linkedin;

import java.util.*;

public class NestedIterator {

    public static void main(String [] args) {
        //[[1,1],2,[1,1]]
        NestedInteger nestedOne = new NestedInteger(true, 1, null);
        NestedInteger nestedTwo = new NestedInteger(true, 2, null);
        List<NestedInteger> firstList = new ArrayList<>();
        firstList.add(nestedOne); firstList.add(nestedOne);
        NestedInteger nestedIntegerA = new NestedInteger(false, 0, firstList);

        List<NestedInteger> secondList = new ArrayList<>();
        secondList.add(nestedOne); secondList.add(nestedOne);
        NestedInteger nestedIntegerB = new NestedInteger(false, 0, secondList);

        List<NestedInteger> completeList = new ArrayList<>();
        completeList.add(nestedIntegerA); completeList.add(nestedTwo); completeList.add(nestedIntegerB);

        NestedIterator nestedIter = new NestedIterator(completeList);

        while (nestedIter.hasNext()) {
            System.out.println(nestedIter.next());
        }

    }


    public static class NestedInteger {
        boolean isInteger;
        int iVal;
        List<NestedInteger> lNestedIter;


        public NestedInteger(boolean isInteger, int iVal, List<NestedInteger> lNestedIter) {
            this.isInteger = isInteger;
            this.iVal = iVal;
            this.lNestedIter = lNestedIter;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return isInteger;
        }

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger() {
            return iVal;
         }

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList() {
            return lNestedIter;
         }
    }

    Stack<Iterator<NestedInteger>> stackIterators = new Stack<>();
    NestedInteger nI;
    public NestedIterator(List<NestedInteger> nestedList) {
        stackIterators.push(nestedList.iterator());
    }


    public Integer next() {
        if (nI != null) {
            int val =  nI.getInteger();
            nI = null;
            return val;
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        while (!stackIterators.isEmpty()) {
            Iterator<NestedInteger> iter = stackIterators.pop();
            if (iter.hasNext()) {
                NestedInteger nestedInteger = iter.next();
                if (nestedInteger.isInteger()) {
                    if (iter.hasNext()) {
                        stackIterators.push(iter);
                    }
                    nI = nestedInteger;
                    return true;
                }
                stackIterators.push(iter);
                stackIterators.push(nestedInteger.getList().iterator());
            }
        }
        return false;
    }
}
