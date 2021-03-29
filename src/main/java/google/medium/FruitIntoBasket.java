package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 *
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop. Move to the next tree to the right of
 * the current tree.  If there is no tree to the right, stop. Note that you do not have any choice after the initial
 * choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you
 * stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one
 * type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 * IMP-1: Sliding window. This approach repeats often.
 */
public class FruitIntoBasket {

    public static void main(String[] args) {
        int[] trees = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        FruitIntoBasket fruitIntoBasket = new FruitIntoBasket();
        int ct = fruitIntoBasket.totalFruit(trees);
        System.out.println(ct);

        int[] trees1 = {1, 2, 3, 2, 2};
        fruitIntoBasket = new FruitIntoBasket();
        ct = fruitIntoBasket.totalFruit(trees1);
        System.out.println(ct);

        int[] trees2 = {0, 1, 2, 2};
        fruitIntoBasket = new FruitIntoBasket();
        ct = fruitIntoBasket.totalFruit(trees2);
        System.out.println(ct);

    }

    /**
     * sliding window solution move from left to right while tracking number of distinct fruits and their aggregated
     * count
     *
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> mapIntegers = new HashMap<>();
        int left = 0, right = 0;
        int maxFruits = 0;
        int currCount = 0;
        for (; left < tree.length && right < tree.length; ) {
            while (mapIntegers.size() > 2 && left < tree.length) {
                int fruit = tree[left];
                int count = mapIntegers.get(fruit);
                count--;
                currCount--;
                if (count == 0) {
                    mapIntegers.remove(fruit);
                } else {
                    mapIntegers.put(fruit, count);
                }
                left++;
            }
            while (mapIntegers.size() < 3 && right < tree.length) {
                int fruit = tree[right];
                int count = mapIntegers.getOrDefault(fruit, 0);
                count++;
                currCount++;
                mapIntegers.put(fruit, count);
                if (mapIntegers.size() < 3) {
                    maxFruits = Math.max(maxFruits, currCount);
                }
                right++;
            }
        }
        return maxFruits;
    }

}
