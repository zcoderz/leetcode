package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 1244. Design A Leaderboard
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
 * If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
 * It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 *
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 * simple implementation of a ladder board using a hash map and a sorted array.
 */
public class Leaderboard {

    Map<Integer, Person> personMap = new HashMap<>();
    ArrayList<Integer> scores = new ArrayList<>();

    public Leaderboard() {
    }

    public static void main(String[] args) {
        Leaderboard board = new Leaderboard();
        board.addScore(1, 13);
        board.addScore(2, 93);
        board.addScore(3, 84);
        board.addScore(4, 6);
        board.addScore(5, 89);
        board.addScore(6, 31);
        board.addScore(7, 7);
        board.addScore(8, 1);
        board.addScore(9, 98);
        board.addScore(10, 42);
        int score = board.top(5);
        System.out.println(score);
        board.reset(1);
        board.reset(2);
        board.addScore(3, 76);
        board.addScore(4, 68);
        score = board.top(1);
        System.out.println(score);
        board.reset(3);
        board.reset(4);
        board.addScore(2, 70);
        board.reset(2);

        System.out.println(score);
    }

    /**
     * Interesting idea to keep
     *
     * @param playerId
     * @param score
     */
    public void addScore(int playerId, int score) {
        Person person = personMap.get(playerId);
        if (person != null) {
            int personScore = Collections.binarySearch(scores, person.score);
            scores.remove(personScore);
            person.score += score;
        } else {
            person = new Person();
            person.personId = playerId;
            person.score = score;
        }
        personMap.put(playerId, person);
        int id = Collections.binarySearch(scores, person.score);
        if (id < 0) {
            id = (id + 1) * -1;
        }
        scores.add(id, person.score);
    }

    /**
     * get sum of top k scores
     *
     * @param k
     * @return
     */
    public int top(int k) {
        int score = 0;
        for (int i = scores.size() - 1; i >= 0 && k > 0; k--, i--) {
            score += scores.get(i);
        }
        return score;
    }

    /**
     * remove player id from the lader board
     *
     * @param playerId
     */
    public void reset(int playerId) {
        Person person = personMap.get(playerId);
        personMap.remove(playerId);
        int score = Collections.binarySearch(scores, person.score);
        scores.remove(score);
    }

    public static class Person {
        int personId;
        int score;
    }
}
