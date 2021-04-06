package frequent.medium;

/**
 * 518. Coin Change 2
 *
 * Question asks to find number of combinations of coins that can be used to calculate a given amount
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * IMP-1: Common question
 */
public class CoinChangeTwo {

    public static void main(String [] args) {
        CoinChangeTwo cChg = new CoinChangeTwo();
        int [] coins = {1, 2, 5};
        int num = cChg.change(5, coins);
        System.out.println(num);
    }


    /**
     * this is a simple but fairly complicated solution to come up with without practice
     *
     * essentially it builds the # of possibilities for each coin from bottom up
     * while aggregating past possibilities with previous coins to the updated possibilities with new coin
     *
     * this logic works extremely elegantly.
     *
     * its a very complicated and slow problem to solve if you recuse down the possibilities.
     * @param amount
     * @param coins
     * @return
     */
    int change(int amount, int [] coins) {
        int [] change = new int[amount+1];
        change[0]=1;
        for (int coin : coins) {
            for(int i =coin; i <= amount; i++) {
                change[i] += change[i-coin];
            }
        }
        return change[amount];
    }

}
