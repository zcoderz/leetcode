package microsoft;

public class MinimumDeletionCostToAvoidRepeatingLetters {

    public static void main(String [] args) {
        MinimumDeletionCostToAvoidRepeatingLetters min = new MinimumDeletionCostToAvoidRepeatingLetters();
        String s = "aabaa";
        int [] cost = {1,2,3,4,1};
        int totalC = min.minCost(s, cost);
        System.out.println(totalC);

    }
    public int minCost(String s, int[] cost) {
        int priorCost = 0;
        char priorChar = 'a';
        int runningCost = 0;

        for (int i =0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            int costCurr = cost[i];
            if (i != 0 && currChar == priorChar) {
                if (priorCost < costCurr) {
                    runningCost += priorCost;
                    priorCost = costCurr;
                } else {
                    runningCost += costCurr;
                }
            } else {
                priorChar = currChar;
                priorCost = costCurr;
            }
        }

        return runningCost;
    }

}
