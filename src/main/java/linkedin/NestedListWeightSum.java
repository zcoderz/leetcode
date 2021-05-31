package linkedin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NestedListWeightSum {

    Map<Integer, Integer> integerMap = new HashMap<>();
    Map<Integer, List<Integer>> mapList = new HashMap<>();
    int maxLevel = 0;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        for (NestedInteger nI : nestedList) {
            processNiLevel(1, nI);
        }
        int res = 0;
        for (Map.Entry<Integer, List<Integer>> mapEntry: mapList.entrySet()) {
            for (Integer level : mapEntry.getValue()) {
                int levelDiff = maxLevel - level + 1;
                res += mapEntry.getKey() * levelDiff;
            }
        }
        return res;
    }

    void processNiLevel(int level, NestedInteger nestedInteger) {
        maxLevel = Math.max(level, maxLevel);
        if (nestedInteger.isInteger()) {
            int intV = nestedInteger.getInteger();
            List<Integer> levels = mapList.getOrDefault(intV, new LinkedList<>());
            levels.add(level);
            mapList.put(intV, levels);
        } else {
            for (NestedInteger ni : nestedInteger.getList()) {
                processNiLevel(level+1, ni);
            }
        }
    }

    public int depthSum(List<NestedInteger> nestedList) {
        for (NestedInteger nI : nestedList) {
            processNi(1, nI);
        }
        int res = 0;
        for (int iVal : integerMap.values()) {
            res += iVal;
        }
        return res;
    }



    void processNi(int level, NestedInteger nestedInteger) {
        if (nestedInteger.isInteger()) {
            int intV = nestedInteger.getInteger();
            int inSum = level * intV;
            inSum += integerMap.getOrDefault(intV, 0);
            integerMap.put(intV, inSum);
        } else {
            for (NestedInteger ni : nestedInteger.getList()) {
                processNi(level+1, ni);
            }
        }
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // Constructor initializes an empty nested list.
        //public NestedInteger();

        // Constructor initializes a single integer.
        //public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

}
