package google.medium;


/**
 * 1007. Minimum Domino Rotations For Equal Row
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile
 * with two numbers from 1 to 6 - one on each half of the tile.)

 * We may rotate the ith domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 * Example 1:
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2,
 * as indicated by the second figure.
 *
 *
 * This is an interesting problem where it can be simplified a whole lot via good focus as in the below code. This
 * solution is from leetcode.
 *
 * IMP-2: This is a nice question to practice
 */
public class MinDominoRotations {

    public static void main(String[] args) {
        int[] arrA = {1, 2, 1, 1, 1, 2, 2, 2};
        int[] arrB = {2, 1, 2, 2, 2, 2, 2, 2};
        MinDominoRotations min = new MinDominoRotations();
        int rots = min.minDominoRotations(arrA, arrB);
        System.out.println(rots);
    }

    public int minDominoRotations(int[] arrA, int[] arrB) {
        if (arrA.length == 0) {
            return 0;
        }
        //if a solution is found by choosing the first element in arrayA then return
        //else try with first element in arrayB
        int rotA = validate(arrA, arrB, arrA[0]);
        if (rotA != -1 || arrA[0] == arrB[0]) return rotA;
        return validate(arrA, arrB, arrB[0]);
    }

    /**
     * the approach to the solution is that for a domino to occur all elements in either of the arrays must be same and
     * elements can only be swapped by an element in the other array at the same index
     *
     * based on above given all indexes must be same, you can
     *
     * 1. pick element from array 1 at index 0 (could be any index) count how many min rotations are needed to make
     * elements same in either of arrays. if this returns a solution, it is a valid solution.
     * 2. do same as 1 but pick element from array 2 (at the same index as 1) 3. neither 1 or 2 returns a valid
     * solution and then you return -1
     *
     * @param arrA
     * @param arrB
     * @return
     */
    int validate(int[] arrA, int[] arrB, int val) {
        int countA = 0, countB = 0;
        for (int i = 0; i < arrA.length; i++) {
            if (arrA[i] != val && arrB[i] != val) {
                return -1;
            }
            if (arrA[i] != val) countA++;
            if (arrB[i] != val) countB++;
        }
        return Math.min(countA, countB);
    }
}
