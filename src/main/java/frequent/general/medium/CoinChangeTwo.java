package frequent.general.medium;

/**
 * Question asks to find number of combinations of coins that can be used to calculate a given amount
 *
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
