package redo;

public class MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        int total = 0;
        for (int val : cardPoints) {
            total += val;
        }
        k = cardPoints.length - k;
        int minPossible = Integer.MAX_VALUE;
        int curr = 0;
        for (int i=0; i < cardPoints.length; i++) {
            curr += cardPoints[i];
            if (i >= k) {
                curr -= cardPoints[i - k];
            }
            if (i >= k-1) {
                minPossible = Math.min(curr, minPossible);
            }
        }
        return total - minPossible;
    }

}
