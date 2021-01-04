package dynamic;


import java.util.HashMap;
import java.util.Map;

/**
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting
 * house 1 with color green, and so on... Find the minimum cost to paint all houses.
 *
 * Leetcode has a very elegant description to this problem's solution. please have a look at it as it
 * gives a very simple solution while analyzing it very well.
 * its available at : https://leetcode.com/problems/paint-house/solution/
 *
 *
 *
 */
public class PaintHouse {

    public static void main(String [] args) {
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        PaintHouse ph = new PaintHouse();
        int cost = ph.minCost(costs);
        System.out.println(cost);
    }

    int numColors = 3;

    /**
     * this is an extremely simple solution to an otherwise seemingly complicated problem.
     * the below is a very elegant approach and isn't very intuitive, but with a lot of practice on DP problems becomes
     * easier to invent
     *
     * start from the last row and move up to the first row while calculating min cost per color choice
     * instead of recursion this works as an iterative solution as min cost can be calculated via below equation
     * imagine two houses 1 & 2
     *
     * 1. cost - color1 choice = cost color 1 of house 1 + min (color choice of house 2 colors 2 & 3).
     * 2. cost - color2 choice = cost color 2 of house 1 + min (color choice of house 2 colors 1 & 3).
     * 3. cost - color3 choice = cost color 3 of house 1 + min (color choice of house 2 colors 1 & 2).
     * min cost is min (1, 2, 3) above.
     *
     * @param costs
     * @return
     */
    public int minCostDP(int[][] costs) {
        if (costs.length ==0) return 0;
        int [] workRow = new int[numColors]; //an array for some background work
        int [] prevRow = new int[numColors]; //an array that tracks min costs so far per the color choices
        int currentRow = costs.length - 1;
        //copy the last costs to prev array and thereafter start from second last row
        System.arraycopy(costs[currentRow], 0, prevRow, 0, costs[currentRow].length);
        while (currentRow > 0) {
            currentRow--;
            //for each color calculate the min cost thus far
            workRow[0] = costs[currentRow][0] + Math.min(prevRow[1], prevRow[2]);
            workRow[1] = costs[currentRow][1] + Math.min(prevRow[0], prevRow[2]);
            workRow[2] = costs[currentRow][2] + Math.min(prevRow[0], prevRow[1]);
            //copy working array to previous array
            System.arraycopy(workRow, 0, prevRow, 0, workRow.length);
        }
        //the total cost so far per color choice is available in the prevRow
        //pick the min cost in the array
        return Math.min(Math.min(prevRow[0], prevRow[1]), prevRow[2]);
    }

    Map<String, Integer> costMap = new HashMap<>();
    int numHouses;
    int [][] costs;

    /**
     * calculates the cost via calling into processCosts method.
     * and returns the min cost of the three colors in question.
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if(costs.length ==0) return 0;
        this.costs = costs;
        numHouses = costs.length;
        int cA = processCosts(0, 0);
        int cB = processCosts(0, 1);
        int cC = processCosts(0, 2);
        return Math.min(Math.min(cA, cB), cC);
    }

    /**
     * this method recurses down from first to last house while calculating
     * the min cost so far to paint the house per each color choice.
     * so as to not repeat the calculations, the costs per house/color are saved in a map for fast
     * retrieval if already calculated previously
     * @param houseNo
     * @param color
     * @return
     */
    int processCosts(int houseNo, int color) {
        String key = getKey(houseNo, color);
        Integer cost = costMap.get(key);
        if (cost != null) {
            return cost;
        }
        if (houseNo == numHouses-1) {
            //base case when this is the last house
            //cost is exactly that of the given house's color, it has no more depencies
            //as its the last house
            cost = costs[houseNo][color];
            costMap.put(key, cost);
            return cost;
        }
        int colorACSoFar = 0;
        int colorBCSoFar = 0;
        if (color == 0) {
            colorACSoFar = processCosts(houseNo+1, 1);
            colorBCSoFar = processCosts(houseNo+1, 2);
        } else if (color ==1) {
            colorACSoFar = processCosts(houseNo+1, 0);
            colorBCSoFar = processCosts(houseNo+1, 2);
        } else if (color ==2) {
            colorACSoFar = processCosts(houseNo+1, 0);
            colorBCSoFar = processCosts(houseNo+1, 1);
        }
        //get the min cost of other two colors for the rest of houses
        int costsSoFar = Math.min(colorACSoFar, colorBCSoFar);
        //add cost for the current color for current house
        costsSoFar += costs[houseNo][color];
        //update cost for house/color in map so next time it doesnt have to be recalculated
        costMap.put(key, costsSoFar);
        return costsSoFar;
    }

    /**
     * its a good design to have a separate method that can return the key represented
     * by the given house no and color no
     * @param houseNo
     * @param colorNo
     * @return
     */
    private String getKey(int houseNo, int colorNo) {
        return houseNo + "_" + colorNo;
    }
}
