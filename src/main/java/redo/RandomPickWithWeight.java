package redo;

import java.util.Random;

public class RandomPickWithWeight {

    public static void main(String [] args) {
        int [] vals = {3,14,1,7};
        RandomPickWithWeight random = new RandomPickWithWeight(vals);
        int index = random.pickIndex();
        System.out.println(index);
    }

    int [] weights;
    int sum = 0;
    Random rn = new Random();
    public RandomPickWithWeight(int[] w) {
        weights = new int[w.length];
        for (int i =0; i < w.length; i++) {
            sum += w[i];
            weights[i] = sum;
        }
    }

    public int pickIndexLinear() {
        double val = sum * Math.random();
        int i = 0;
        for (; i < weights.length; i++) {
            if (weights[i] > val) {
                return i;
            }
        }
        return i;
    }


    public int pickIndex() {
        Random random = new Random();
        int val = 1 + random.nextInt(weights[weights.length - 1]);
        int lo = 0; int hi = weights.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if (val == weights[mid]) {
                return mid;
            } else if (weights[mid] > val) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }
        return lo;
    }
}
