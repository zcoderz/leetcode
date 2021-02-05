package frequent.medium;


/**
 * 528. Random Pick with Weight
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 *
 * We need to call the function pickIndex() which randomly returns an integer in the
 * range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array.
 * For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)
 * while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 *
 * More formally, the probability of picking index i is w[i] / sum(w).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
 *
 * interesting problem . asks to return an index where weight of the index is denoted in array.
 * so the index with higher weights should have higher probability of being returned and vice versa
 *
 * IMP-1 : Common question with a distinct idea
 */
public class RandomPickByWeight {

    public static void main( String [] args) {
        int [] w = {1, 2, 4, 5, 7, 8, 9, 11};
        RandomPickByWeight randPickByW = new RandomPickByWeight(w);
        while(true) {
            int j = randPickByW.pickIndex();
            System.out.println(j);
        }
    }

    int[] aggWeights;
    int sum;
    public RandomPickByWeight(int[] w) {
        //add the aggregated weights into a new array.
        //note that this array will be sorted as weights are positive
        aggWeights = new int[w.length];
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            aggWeights[i] = sum;
        }
        this.sum = sum;
    }

    public int pickIndex() {
        //get a random number and search for its index in the array
        double rand = Math.random() * sum;
        int low = 0; int hi= aggWeights.length-1;

        while (low < hi) {
            int mid = low + ((hi-low) / 2); //the conversion from double to int will round the double down to int
            if (aggWeights[mid] < rand) {
                //important to move low one ahead of the mid instead of modifying condition to move head down.
                //that's because the above logic rounds down the mid and we could stay in a loop
                //if we dont advance low.
                low = mid+1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }


}
