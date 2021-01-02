package trees;

import utils.Pair;

import java.util.HashSet;
import java.util.Set;

public class RoomCleaner {

    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    int [][] moveDirections = { {0,1}, {1,0}, {0,-1}, {-1,0}};

    public void cleanRoom(Robot robot) {
        RoomCleaner cleaner = new RoomCleaner();
        cleaner.backTrack(0, 0, robot, 0);
    }

    void backTrack(int x, int y, Robot robot, int d) {
        robot.clean();
        Pair<Integer, Integer> p = new Pair<>(x, y);
        visited.add(p);

        for (int i =0; i < moveDirections.length; i ++) {
            int newD = (d + i ) % 4;
            int xx = x + moveDirections[newD][0];
            int yy = y + moveDirections[newD][1];

            Pair<Integer, Integer> pNew = new Pair<>(xx, yy);
            if (!visited.contains(pNew) && robot.move()) {
                backTrack(xx, yy, robot, newD);
                back(robot);
            }
            robot.turnRight();
        }
    }

    void back(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

}
