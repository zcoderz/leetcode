package design;

import java.util.NoSuchElementException;

/**
 * 251. Flatten 2D Vector
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
 *
 *
 *
 * Example:
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // return 1
 * iterator.next(); // return 2
 * iterator.next(); // return 3
 * iterator.hasNext(); // return true
 * iterator.hasNext(); // return true
 * iterator.next(); // return 4
 * iterator.hasNext(); // return false
 *
 */
public class Vector2D {
    int row, col;
    int[][] v;

    public Vector2D(int[][] v) {
        row = 0;
        col = 0;
        this.v = v;
    }

    private void advanceToNext() {
        while ((col <= v[row].length) && (row + 1 < v.length)) {
            row++;
            col = 0;
        }
    }

    public boolean hasNext() {
        advanceToNext();
        return col < v[row].length;
    }

    public int next() {
        advanceToNext();
        ;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return v[row][col];
    }

    public int nextComplex() {
        while (v.length >= row + 1) {
            col++;
            if (v[row].length > col) {
                return v[row][col];
            }
            row++;
            col = -1;
        }
        return -1;
    }

    public boolean hasNextComplex() {
        int tmpCol = col;
        int tmpRow = row;
        while (v.length >= tmpRow + 1) {
            tmpCol++;
            if (v[tmpRow].length > tmpCol) {
                return true;
            }
            tmpRow++;
            tmpCol = -1;
        }
        return false;
    }
}
