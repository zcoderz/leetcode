package face_book.hard;

import utils.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1263. Minimum Moves to Move a Box to Their Target Location
 * A storekeeper is a game in which the player pushes boxes around
 * in a warehouse trying to get them to target locations.
 *
 * The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
 *
 * Your task is to move the box 'B' to the target position 'T' under the following rules:
 *
 * The character 'S' represents the player. The player can move up, down, left,
 * right in grid if it is a floor (empty cell).
 * The character '.' represents the floor which means a free cell to walk.
 * The character '#' represents the wall which means an obstacle (impossible to walk there).
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box and
 * then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 *
 */
public class BoxMinMoves {


    public static void main (String [] args) {
        char [][] grid = {{'#','#','#','#','#','#','#'},
                          {'#','S','#','.','B','T','#'},
                          {'#','#','#','#','#','#','#'}};
        BoxMinMoves box = new BoxMinMoves();
        int res = box.minPushBox(grid);
        System.out.println(res);
    }

    int [][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int [][] personLoc = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int minPushBox(char[][] grid) {
        int [] state = new int[4]; //first two are person, rest two are box
        int [] target = new int[2];
        int numMoves = 0;
        for (int i =0; i < grid.length; i++) {
            for (int j =0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    state[0] = i;
                    state[1] = j;
                } else if (grid[i][j] == 'B') {
                    state[2] = i;
                    state[3] = j;
                }
                if (grid[i][j] == 'T') {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        Queue<int []> queue = new LinkedList<>();
        queue.add(state);
        Set<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> visited = new HashSet<>();
        //person first , box second
        visited.add(Pair.of(Pair.of(state[0], state[1]), Pair.of(state[2], state[3])));
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i =0; i < sz; i++) {
                state = queue.poll();
                if (state[2]==target[0] && state[3]==target[1]) {
                    return numMoves;
                }
                for (int j =0; j < moves.length ; j++) {
                    int [] move = moves[j];
                    int []  personMove = personLoc[j];
                    int currRow = state[2]; int currCol = state[3];
                    int newRow = currRow+ move[0]; int newCol = currCol + move[1];
                    int personRow = currRow + personMove[0]; int personCol = currCol + personMove[1];
                    if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length ||
                        grid[newRow][newCol] == '#' || personRow < 0 || personRow >= grid.length ||
                            personCol < 0 || personCol >= grid[0].length) {
                        continue;
                    }
                    if (visited.contains(Pair.of(Pair.of(personRow, personCol), Pair.of(newRow, newCol)))) {
                        continue;
                    }
                    boolean traversePerson = traversePerson(grid, state[0], state[1], state[2] , state[3], personRow,
                            personCol);
                    if (traversePerson) {
                        queue.add(new int[] {personRow, personCol, newRow, newCol});
                        visited.add(Pair.of(Pair.of(personRow, personCol), Pair.of(newRow, newCol)));
                    }
                }
            }
            numMoves++;
        }
        return -1;
    }

    boolean traversePerson(char [][] grid, int currPersonRow, int currPersonCol, int boxRow, int boxCol,
                           int targetRow, int targetCol) {
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {currPersonRow, currPersonCol});
        visited[boxRow][boxCol] = true;
        while (!queue.isEmpty()) {
            int [] loc = queue.poll();
            if (loc[0] == targetRow && loc[1] == targetCol) {
                return true;
            }
            for (int [] dir: moves) {
                int newRow = loc[0] + dir[0];
                int newCol = loc[1] + dir[1];
                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length ||
                        grid[newRow][newCol] == '#' || visited[newRow][newCol]) {
                    continue;
                }
                visited[newRow][newCol] = true;
                queue.add(new int[] {newRow, newCol});
            }
        }
        return false;
    }
}
