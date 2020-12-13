package frequent.general.hard;

import utils.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * this is a great problem to learn.
 */
public class RobotRoomCleaner {


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

    //mark visited cells so you dont go in a circular loop
    Set<Pair<Integer, Integer>> visited = new HashSet<>();

    //create a set of moves in a circular direction
    int [] rowOffset = {-1, 0, 1, 0};
    int [] colOffset = {0, 1, 0, -1};

    /**
     * main method
     * @param robot
     */
    public void cleanRoom(Robot robot) {
        int iRow =0, iCol = 0;
        recurseClean(iRow, iCol, robot, 0);
    }

    /**
     * method cleans room and recurse
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
        for (int i =0 ; i < 4; i++) {
            //this one is tricky but extremely smart, avoids having to write a bunch of direction management code
            //you basically start with orig direction and rotate 4 more directions on it. therefore, your starting
            //point is direction passed as param. since it could be any of 0,1,2,3 you mod by 4 to get next 4 directions
            int newDir = (direction + i ) % 4;
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
     * @param robot
     */
    void stepBackRobot (Robot robot) {
        robot.turnRight();
        robot.turnRight();//change direction
        robot.move();//move back
        robot.turnRight();//change direction again so that direction is same as what you started with
        robot.turnRight();
    }
}
