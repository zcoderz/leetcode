package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1423. Maximum Points You Can Obtain from Cards
 * There are several cards arranged in a row, and each card has an associated number of points
 * The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first
 * will maximize your total score. The optimal strategy is to take the three cards on the right,
 * giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 *
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 *
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 * Example 4:
 *
 * Input: cardPoints = [1,1000,1], k = 1
 * Output: 1
 * Explanation: You cannot take the card in the middle. Your best score is 1.
 * Example 5:
 *
 * Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * Output: 202
 *
 * Below solution runs through the combinations of possible aggregations either from left or right
 * for the first K cards to calculate the largest possible aggregate value.
 *
 * It uses memorization to track the max value at each left right index and thus avoids repeats.
 * Nevertheless the calculation is high compute. See the solution in class MaximumCardPointsFast for a simpler
 * and faster solution.
 */
public class MaximumCardPoints {

    public static void main(String [] args) {
        int [] arr = {1,79,80,1,1,1,200,1};
        MaximumCardPoints max = new MaximumCardPoints();
        int score = max.maxScore(arr, 3);
        System.out.println(score);
    }

    int max = 0;
    int totalCards;
    int [] cardPoints;
    double offSet = Math.pow(10,5);

    Map<Integer, Integer> maxMap = new HashMap<>();
    public int maxScore(int[] cardPoints, int k) {
        this.cardPoints = cardPoints;
        totalCards = cardPoints.length;
        processCardPoints(0, cardPoints.length-1,  k, 0);
        return max;
    }

    void processCardPoints(int leftIndex, int rightIndex , int maxIndexes, int totalSoFar) {
        max = Math.max(totalSoFar, max);
        int level = (leftIndex + totalCards-rightIndex-1);

        if (level == maxIndexes) {
            return ;
        }

        int indexNo = (int) (rightIndex*offSet+leftIndex);
        Integer remainMax = maxMap.get(indexNo);
        if (remainMax != null) {
            max = Math.max(totalSoFar + remainMax, max);
            return;
        }

        processCardPoints(leftIndex +1, rightIndex, maxIndexes, totalSoFar+cardPoints[leftIndex]);
        processCardPoints(leftIndex, rightIndex-1, maxIndexes, totalSoFar+cardPoints[rightIndex]);

        maxMap.put(indexNo, max-totalSoFar);
    }
}
