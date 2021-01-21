package google.medium;


/**
 * 351. Android Unlock Patterns
 *
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by
 * connecting the dots in a specific sequence, forming a series of joined line segments where each segment's
 * endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both
 * of the following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through any other dot, the other
 * dot must have previously appeared in the sequence. No jumps through non-selected dots are allowed.
 * Here are some example valid and invalid unlock patterns:
 *
 *
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2,
 * but dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5,
 * but dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1
 * and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1
 * and 9 meets the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid
 * lock screen that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other,
 * or the order of the dots is different.
 *
 */
public class AndroidUnlockPatterns {

    public static void main(String [] args) {
        AndroidUnlockPatterns android = new AndroidUnlockPatterns();
        int num = android.numberOfPatterns(1, 1);
        System.out.println(num);

        num = android.numberOfPatterns(1, 2);
        System.out.println(num);

        num = android.numberOfPatterns(1, 4);
        System.out.println(num);
    }

    /**
     * this is a very clever solution and was presented by someone in leetcode discussion
     * some things to note are that the question doesnt explicitly specify that knight moves are legal. but they are
     * marking coordinates between source and dest that are skipped simplifies the problem a lot
     *
     * also note that the matrix is symmetric so we dont need to check all coordinates
     *
     * @param m
     * @param n
     * @return
     */
    public int numberOfPatterns(int m, int n) {
        //skipped indicates indices between two coordinates that are skipped
        int [][] skipped = new int[10][10];
        skipped[1][3] = skipped[3][1] = 2;
        skipped[1][7] = skipped[7][1] = 4;
        skipped[7][9] = skipped[9][7] = 8;
        skipped[3][9] = skipped[9][3] = 6;
        skipped[1][9] = skipped[9][1] = skipped[7][3] = skipped[3][7] = skipped[4][6] =
                skipped[6][4] = skipped[2][8] = skipped[8][2] = 5;

        boolean [] visited = new boolean[10];

        int numCombinations = 0;
        for (int i =m ; i <= n; i++) {
            //due to the symmetry of a 3x3 matrix we only need to check 1st , second and fifth coordinates
            numCombinations += processDFS(1, visited, i-1, skipped) * 4;
            numCombinations += processDFS(2, visited, i-1, skipped) * 4;
            numCombinations += processDFS(5, visited, i-1, skipped);
        }

        return numCombinations;
    }

    /**
     * this is a simple dfs approach to calculate number of combinations until no remaining are left
     * @param index
     * @param visited
     * @param remaining
     * @param skipped
     * @return
     */
    int processDFS(int index, boolean [] visited, int remaining, int [][] skipped) {
        if (remaining ==0) {
            return 1;
        }

        //so we dont visit this coordinate again mark as visited
        visited[index] = true;
        int num = 0;
        for (int i =1; i < 10 ; ++i) {
            //the check validates that the desired coordinate has not been visited
            //and that if there is a skip between this coordinate and the next the skip has been visited
            if ((!visited[i]) && (skipped[index][i] ==0 || visited[skipped[index][i]])) {
               num += processDFS(i, visited, remaining-1, skipped);
            }
        }

        //back track and un-mark the visited coordinate
        visited[index] = false;
        return num;
    }

}
