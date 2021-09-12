package redo;

public class MinDominoRotations {

    public static void main(String [] args) {
        int [] tops  = {1,2,1,1,1,2,2,2};
        int [] bottoms = {2,1,2,2,2,2,2,2};
        MinDominoRotations min = new MinDominoRotations();
        int res = min.minDominoRotations(tops, bottoms);
        System.out.println(res);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {



        int res = calcRots (tops, bottoms, tops[0]);
        if (res != -1 && tops[0] == bottoms[0]) {
            return res;
        }
        int resB =  calcRots (tops, bottoms, bottoms[0]);
        if (res != -1 && resB !=-1) {
            return Math.min(res, resB);
        } else if (res ==-1) {
            return resB;
        } else {
            return res;
        }
    }

    int calcRots (int [] top, int [] bottom, int val) {
        int cntA = 0, cntB = 0;
        for (int i = 0; i < top.length; i++) {
            if (top[i] != val && bottom[i] != val) {
                return -1;
            } else if (top[i] != val) {
                cntA++;
            } else if (bottom[i] != val) {
                cntB++;
            }
        }

        return Math.min(cntA, cntB);
    }
}
