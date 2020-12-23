package dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * question asks for min number of coins needed to reach the desired amount
 */
public class CoinChange {

    public static void main(String [] args) {

        int [] coins = {2};

        int amount = 3;

        CoinChange coinChange = new CoinChange();
        int change = coinChange.coinChange(coins, amount);
        System.out.println(change);

    }

    public int coinChange(int[] coins, int amount) {
        int [] changeNum = new int[amount+1];
        Arrays.fill(changeNum, 0);
        Arrays.sort(coins);
        return processTopDownCoinChange(coins, amount, changeNum);
    }

    /**
     * this is a simple solution for the problem.
     * start finding next smallest # for each value 0...N.
     *
     * build each next value using the already calculated smaller values
     *
     * repeat until you reach the desired amount
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeBottomUp(int[] coins, int amount) {
        int [] changeNum = new int[amount+1];
        Arrays.sort(coins);
        Arrays.fill(changeNum, amount+1);
        for (int i = 0; i <= amount; i++) {
            if (i==0) {
                changeNum[i] = 0;
            } else {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] > i) break; // no need to process coins greater than the amount

                    int theCoin = coins[j];
                    int priorIndex = i - theCoin;
                    int priorMin = changeNum[priorIndex];
                    int newMin = priorMin+1;
                    changeNum[i] = Math.min(changeNum[i], newMin);
                }
            }
        }
        return changeNum[amount] == amount+1 ? -1 : changeNum[amount];
    }

    /**
     * Top down solution is more complex to understand than bottom up.
     *
     * @param coins
     * @param amount
     * @param changeNum
     * @return
     */
    public int processTopDownCoinChange(int [] coins, int amount, int [] changeNum) {
        if (amount == 0) {
            changeNum[0] = 0;
            return 0;
        }

        int changeAmt = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (coin > amount) {
                break;
            }
            int remaining = amount - coin;
            if (changeNum[remaining] != 0) {
                //if an amount for the given change is already calculated use it.
                int changeNumV = changeNum[remaining];
                if (changeNumV != Integer.MAX_VALUE) {
                    changeNumV++;
                }
                //set amount as min of the given and that calculated so far
                changeAmt = Math.min(changeAmt, changeNumV);
            } else {
                //if amount is not calculated recuse down.
                int amt = processTopDownCoinChange(coins, remaining, changeNum);
                if (amt !=  Integer.MAX_VALUE) {
                    changeAmt = Math.min(amt+1, changeAmt);
                }
            }
        }
        changeNum[amount] = changeAmt;
        return changeAmt;
    }


}
