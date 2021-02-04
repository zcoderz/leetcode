package frequent.hard;

import utils.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 489. Robot Room Cleaner
 * Given a robot cleaner in a room modeled as a grid.
 *
 * Each cell in the grid can be empty or blocked.
 *
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 *
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Example:
 *
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 *
 * IMP-1: This is a great problem to practice.
 */
public class RobotRoomCleaner {


    //mark visited cells so you dont go in a circular loop
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    //create a set of moves in a circular direction
    int[] rowOffset = {-1, 0, 1, 0};
    int[] colOffset = {0, 1, 0, -1};

    /**
     * main method
     *
     * @param robot
     */
    public void cleanRoom(Robot robot) {
        int iRow = 0, iCol = 0;
        recurseClean(iRow, iCol, robot, 0);
    }

    /**
     * method cleans room and recurse
     *
     * @param row
     * @param col
     * @param robot
     * @param direction
     */
    void recurseClean(int row, int col, Robot robot, int direction) {
        //mark room as visited
        Pair<Integer, Integer> spot = new Pair<>(row, col);
        visited.add(spot);
        robot.clean(); //clean room

        //do 4 moves , forward, right, down, left. since they are circular you just keep turning right
        for (int i = 0; i < 4; i++) {
            //this one is tricky but extremely smart, avoids having to write a bunch of direction management code
            //you basically start with orig direction and rotate 4 more directions on it. therefore, your starting
            //point is direction passed as param. since it could be any of 0,1,2,3 you mod by 4 to get next 4 directions
            int newDir = (direction + i) % 4;
            int newRow = row + rowOffset[newDir];
            int newCol = col + colOffset[newDir];
            Pair<Integer, Integer> newSpot = new Pair<>(newRow, newCol);
            if (!visited.contains(newSpot) && robot.move()) {
                //continue recurse if its a valid move
                recurseClean(newRow, newCol, robot, newDir);
                //very important, change back to starting direction that was passed in to the above recurse method
                stepBackRobot(robot);
            }
            //change directions
            robot.turnRight();
        }
    }

    /**
     * move back to starting cell
     *
     * @param robot
     */
    void stepBackRobot(Robot robot) {
        robot.turnRight();
        robot.turnRight();//change direction
        robot.move();//move back
        robot.turnRight();//change direction again so that direction is same as what you started with
        robot.turnRight();
    }

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();

        public void turnRight();

        // Clean the current cell.
        public void clean();
    }
}
