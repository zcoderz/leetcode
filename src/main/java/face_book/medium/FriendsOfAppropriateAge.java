package face_book.medium;

import java.util.Arrays;

/**
 * 825. Friends Of Appropriate Ages
 * There are n persons on a social media website.
 * You are given an integer array ages where ages[i] is the age of the ith person.
 *
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 *
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * Otherwise, x will send a friend request to y.
 *
 * Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
 *
 * Return the total number of friend requests made.
 *
 *
 *
 * Example 1:
 *
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 */
public class FriendsOfAppropriateAge {

    public static void main(String [] args) {
        FriendsOfAppropriateAge friends = new FriendsOfAppropriateAge();
        int [] arr = {16,17, 18};
        int ct = friends.numFriendRequests(arr);
        System.out.println(ct);
    }

    public int numFriendRequests(int[] ageArr) {
        int [] ages = new int[121];
        Arrays.fill(ages, 0);
        int maxAge = 0;
        for (int age : ageArr) {
            maxAge = Math.max(maxAge, age);
            ages[age]++;
        }

        int [] ageSum = new int[121];
        Arrays.fill(ageSum, 0);
        ageSum[0] = ages[0];
        for (int i =1; i < 121; i++) {
            ageSum[i] = ageSum[i-1] + ages[i];
        }

        int res = 0;

        for (int i = 15 ; i <= maxAge; i++) {
            int sum = ageSum[i] - ageSum[i/2 + 7];
            int val = sum * ages[i] - ages[i];
            res += val;
        }
        return res;
    }


}
