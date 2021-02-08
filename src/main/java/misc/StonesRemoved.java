package misc;

import utils.graph.generic.UnionFindNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 947. Most Stones Removed with Same Row or Column
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 *
 */
public class StonesRemoved {

    public static void main(String[] args) {
        int[][] stones = {{0, 1}, {1, 0}};
        StonesRemoved removed = new StonesRemoved();
        int j = removed.removeStones(stones);
        System.out.println(j);
    }

    /**
     * the logic is that you union the row and columns via union find.
     * by design union merges the common stones to have same parent. you can use this to mean the union transforms N
     * connected stones to 1 stone. Therefore if there are Y distinct parents after union , then the total number of
     * stones removed = total stones - left over stones.
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        Map<Integer, UnionFindNode<Integer>> unionFindMap = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            UnionFindNode<Integer> unionA = new UnionFindNode<>(stones[i][0]);
            //adding 20000 to cols , so columns and rows have diff ids
            UnionFindNode<Integer> unionB = new UnionFindNode<>(stones[i][1] + 20000);
            unionFindMap.put(stones[i][0], unionA);
            unionFindMap.put(stones[i][1] + 20000, unionB);
            unionA.union(unionB);
        }
        Set<Integer> parentsSet = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            UnionFindNode<Integer> unionA = unionFindMap.get(stones[i][0]);
            parentsSet.add(unionA.find().getValue());
        }
        return stones.length - parentsSet.size();
    }

}



