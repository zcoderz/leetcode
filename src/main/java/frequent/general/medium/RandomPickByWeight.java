package frequent.general.medium;


/**
 * interesting problem . asks to return an index where weight of the index is denoted in array.
 * so the index with higher weights should have higher probability of being returned and vice versa
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
