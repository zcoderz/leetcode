package recursion;

public class NQueens {

    int count = 0; //used to track number of possible placements
    int [] cols ;
    int [] hills;
    int [] dips;
    int n;

    public int totalNQueens(int n) {
        this.n = n;
        //this array is used to indicate that one row or column is blocked
        //when a queen is in a row, col, one entire row and one entire column is blocked
        //hence a single variable tracking that one row and col are blocked suffices
        cols  = new int[n];
        //this is to handle down sloping diagonals, the delta n added is to offset so that we dont get negative index
        dips = new int[2*n];  //row - col + n
        //this is to handle upward sloping diagonals
        hills = new int[2*n]; // row + col
        recurse(0); //recuse starting at one row and col
        return count;
    }

    /**
     * recuse to find number of possible placement for queen
     * @param row
     */
    void recurse(int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (canMove(row, col)) {
                cols[col] = 1;
                hills[row+col] = 1;
                dips[row-col+n] = 1;
                recurse(row+1);
                cols[col] = 0;
                hills[row+col] = 0;
                dips[row-col+n] = 0;
            }
        }
    }

    /**
     * indicates whether a queen can be placed in a given row and col
     * @param row
     * @param col
     * @return
     */
    boolean canMove(int row, int col) {
        int num = cols[col] + hills[row+col] + dips[row-col+n];
        return num ==0;
    }
}
