package frequent.general.medium;

import java.util.Arrays;

/**
 * fun question. check if robot's movements are bounded.
 * i,e move the robot by strokes, check if after x iterations the robot is within a bound or not
 */
public class IsRobotBounded {

    public static void main(String [] args) {
        IsRobotBounded bounded = new IsRobotBounded();
        boolean isBounded = bounded.isRobotBounded("GGLLGG");
        assert isBounded;
        Arrays.fill(bounded.robotDirMovements, 0);
        bounded.currDir=0;
        isBounded = bounded.isRobotBounded("GG");
        assert !isBounded;
        Arrays.fill(bounded.robotDirMovements, 0);
        bounded.currDir=0;
        isBounded = bounded.isRobotBounded("GL");
        assert isBounded;
        Arrays.fill(bounded.robotDirMovements, 0);
        bounded.currDir=0;

    }

    int [] robotDirMovements = new int[4];
    int currDir = 0;

    /**
     * idea is that you process the movement 1-4 times , and if the movement is bounded
     * at end of one of those iterations you would get back to start
     *
     * this is because between 1-4 movements , the bot has to come back to start if indeed its bounded
     * for example
     * GL, repeat 4 times to get to start
     * GGLLGG once to start
     * GLGL twice to start
     * GRGRGR 4 times to start
     *
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        for (int i =0; i < 4; i++) {
            processMovement(instructions);
            boolean isB = isBoundedState();
            if (isB) return true;
        }
        return false;
    }

    /**
     * processes the movement specified in the instructions
     * records movement in each of the array indexes denoted by the given direction
     * @param instructions
     */
    void processMovement(String instructions) {
        for (int i =0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            //we use the array as a circular loop, so if we move past the end we move back to front or back
            switch (ch) {
                case 'R' -> currDir = (currDir + 1) % 4;
                case 'L' -> {
                    currDir = currDir - 1;
                    if (currDir == -1) currDir = 3;
                }
                case 'G' -> robotDirMovements[currDir]++;
            }
        }
    }

    /**
     * the method returns true if you are in bounded state
     * basically ensures that north south and east west exactly offset each other
     * @return
     */
    boolean isBoundedState() {
        int nCount =0;
        int eCount =0;

        nCount += robotDirMovements[0];
        eCount += robotDirMovements[1];
        nCount -= robotDirMovements[2];
        eCount -= robotDirMovements[3];

        return (nCount==0 && eCount==0);
    }
}
