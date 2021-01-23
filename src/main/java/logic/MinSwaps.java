package logic;

/**
 * find the min number of swaps needed to readjust the array such that all 1s in the array are next to each other
 */
public class MinSwaps {


    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 0};
        MinSwaps min = new MinSwaps();
        int ct = min.minSwaps(arr);
        System.out.println(ct);
    }

    /**
     * this is a really simple approach once you see it. but coming up with this logic straight off is tricky!
     *
     * @param data
     * @return
     */
    public int minSwaps(int[] data) {
        //the below variable tracks number of 1s in the array
        int countOnes = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                countOnes++;
            }
        }
        //count the movingMaxOnes in the segment denoted by first N indexes , where N = countOnes
        int movingMaxOnes = 0;
        for (int i = 0; i < countOnes; i++) {
            if (data[i] == 1) movingMaxOnes++;
        }
        int maxOnes = movingMaxOnes;
        //keep iterating right till you reach the end of the array while tracking the max
        //number of contiguous 1s seen in a segment denoted by countOnes
        int len = data.length;
        int right = countOnes;
        int left = 0;
        while (right < len) {
            movingMaxOnes += data[right++];
            movingMaxOnes -= data[left++];
            //keep track of maxOnes found as you iterate across the array
            maxOnes = Math.max(maxOnes, movingMaxOnes);
        }

        //number of swaps equals to number of elements in the segment that contained max ones that are not 1
        return countOnes - maxOnes;
    }

}
