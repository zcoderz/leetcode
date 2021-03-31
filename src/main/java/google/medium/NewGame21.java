package google.medium;

/**
 * 837. New 21 Game
 * Alice plays the following game, loosely based on the card game "21".
 *
 * Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw,
 * she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is
 * independent and the outcomes have equal probabilities.
 *
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 *
 * Example 1:
 *
 * Input: N = 10, K = 1, W = 10
 * Output: 1.00000
 * Explanation:  Alice gets a single card, then stops.
 * Example 2:
 *
 * Input: N = 6, K = 1, W = 10
 * Output: 0.60000
 * Explanation:  Alice gets a single card, then stops.
 * In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 * Example 3:
 *
 * Input: N = 21, K = 17, W = 10
 * Output: 0.73278
 *
 * IMP-1 : This is a good practice question on probabilities, should definitely practice
 * TODO - repeat!
 */
public class NewGame21 {

    public static void main(String [] args) {
        NewGame21 game = new NewGame21();
        double res = game.new21Game(21, 17, 10);
        System.out.println(res);
    }

    /**
     * At the first look this problem appears deceptively simple.
     * but the way its formulated it gets tricky to simplify.
     * The below logic was mostly sourced from this discussion on leetcode. its the best discussion on this problem
     * that i could find
     * https://leetcode.com/problems/new-21-game/discuss/132503/My-take-on-how-to-reach-at-Solution
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        //game stops when we are at K
        //at K-1 there are W possible points that can be made
        //so max points possible are k-1 +w
        if ((N >= K -1 + W)) {
            return 1;
        }
        //prob of one move
        double oneProbability = 1 / (W + 0.0);
        double [] probabilities = new double [K+1];
        //this is the base case for probability and hence needs to be set as 1
        //for example probability to get a score of 1 is 1 * 1/W
        probabilities[0] = 1;
        double previousProb = 0;
        //for 1-K
        //p[i] = (p[i-1] + p[i-2] + p[i-3]+...........+ p[i-W]) * oneProbability
        //this is a sliding window approach, so you drop the i-W-1 probability, when i-W-1 >=0
        for (int i = 1; i <= K; i++) {
            previousProb +=   ((i-W-1) >= 0 ?  -probabilities[i-W-1] : 0)  + probabilities[i-1];
            probabilities[i]= previousProb * oneProbability;
        }
        double res = probabilities[K];
        double val;



        //the below condition covers cases from K+1 to N where N is at most K+W-2 , as when N >= K+W-1 the
        //probability is 100% as covered by the first condition at start of the method

        //in simpler english: this case is covering the probability where you are starting with K-1 or less points
        //and in one move crossing K points

        //logic is similar to above where you calculate probability of getting to X where x > k as
        //p[i] = (p[K-1] + p[K-2] + .... + p[i - W]) * 1/W;
        //this is because you cant reach i from i >= K because the game would already have finished if that was true
        for (int i = K+1; i <= N; i++) {
            previousProb -= ((i-W-1) >= 0 ? probabilities[i-W-1] : 0);
            val = previousProb * oneProbability;
            res += val;
        }
        return res;
    }
}
