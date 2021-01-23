package misc;

public class Celebrity {

    public int findCelebrity(int n) {
        int celeberity = 0;
        for (int i = 0; i < n; i++) {
            if (knows(celeberity, i)) {
                celeberity = i;
            }
        }
        if (validate(celeberity, n)) {
            return celeberity;
        }
        return -1;
    }

    boolean validate(int celebrity, int n) {
        for (int i = 0; i < n; i++) {
            if (i == celebrity) continue;
            if (!knows(i, celebrity) || knows(celebrity, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
