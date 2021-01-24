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
 */
public class NewGame21 {

    public static void main(String [] args) {
        NewGame21 game = new NewGame21();
        double res = game.new21Game(21, 17, 10);
        System.out.println(res);
    }

    public double new21Game(int N, int K, int W) {

        //game stops when we are at K
        //at K-1 there are W possible points that can be made
        //so max points possible are k-1 +1
        if ((N >= K -1 + W)) {
            return 1;
        }
        //prob of one move
        double oneProbability = 1 / (W + 0.0);
        double [] probabilities = new double [K+W];
        probabilities[0] = 1;
        double previousProb = 0;
        for (int i = 1; i <= K; i++) {
            previousProb +=   (i-W-1) >= 0 ?  -probabilities[i-W-1] : 0  + probabilities[i-1];
            probabilities[i]= previousProb * oneProbability;
        }
        double res = probabilities[K];
        for (int i =K+1; i <= N; i++) {
            previousProb -= (i-W-1) >= 0 ? probabilities[i-W-1] : 0;
            probabilities[i] = previousProb * oneProbability;
            res += probabilities[i];
        }
        return res;
    }
}
