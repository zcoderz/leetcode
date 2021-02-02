package google.medium;

/**
 * 1138. Alphabet Board Path
 *
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 *
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.
 * You may return any path that does so.
 *
 * This is an easy solution with one caveat all col, row moves arent valid. the last row has only first col valid
 */
public class AlphabtBoardPath {

    public static void main(String[] args) {
        AlphabtBoardPath path = new AlphabtBoardPath();
        String pathS = path.alphabetBoardPath("leet");
        System.out.println(pathS);

    }

    public String alphabetBoardPath(String target) {
        int currentLoc = 0;
        StringBuilder builder = new StringBuilder();
        for (Character ch : target.toCharArray()) {
            int index = getCharIndex(ch);
            if (index == currentLoc) {
                builder.append("!");
            } else {
                String moves = getMoves(currentLoc, index);
                builder.append(moves).append("!");
            }
            currentLoc = index;
        }
        return builder.toString();
    }

    int getCharIndex(Character ch) {
        return ch - 'a';
    }

    String getMoves(int currLoc, int newLoc) {
        int numCols = 5;
        int currRow = currLoc / numCols;
        int currCol = currLoc % numCols;

        int newRow = newLoc / numCols;
        int newCol = newLoc % numCols;
        StringBuilder moves = new StringBuilder();
        while ((newRow != currRow) || (newCol != currCol)) {

            if ((newRow > currRow) && isValid(currRow+1, currCol)) {
                moves.append("D");
                currRow++;
            } else if ((newRow < currRow) && isValid(currRow-1, currCol)) {
                moves.append("U");
                currRow--;
            }

            if ((newCol > currCol) && isValid(currRow, currCol+1)) {
                moves.append("R");
                currCol++;
            } else if ((newCol < currCol) && isValid(currRow, currCol-1)) {
                moves.append("L");
                currCol--;
            }

        }
        return moves.toString();
    }

    public boolean isValid(int row, int col) {
        if (row < 0 || row > 5) return false;

        if (col < 0 || col > 5) return false;

        if (row ==5 && col > 0) return false;

        return true;
    }
}
