package dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

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
                int changeNumV = changeNum[remaining];
                if (changeNumV != Integer.MAX_VALUE) {
                    changeNumV++;
                }
                changeAmt = Math.min(changeAmt, changeNumV);
            } else {
                int amt = processTopDownCoinChange(coins, remaining, changeNum);
                if (amt !=  Integer.MAX_VALUE) {
                    changeAmt = Math.min(amt+1, changeAmt);
                }
            }
        }
        changeNum[amount] = changeAmt;
        return changeAmt;
    }


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

}
