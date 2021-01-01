package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * simple implementation of a ladder board using a hash map and a sorted array.
 *
 */
public class Leadderboard {

    public static void main(String [] args) {
        Leadderboard board = new Leadderboard();
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

    public static class Person {
        int personId;
        int score;
    }

    Map<Integer, Person> personMap = new HashMap<>();
    ArrayList<Integer> scores = new ArrayList<>();

    public Leadderboard() {
    }

    /**
     * update score for player
     * @param playerId
     * @param score
     */
    public void addScore(int playerId, int score) {
        Person person = personMap.get(playerId);
        if (person != null) {
            int id = Collections.binarySearch(scores, person.score);
            scores.remove(id);
            person.score += score;
        } else {
            person = new Person(); person.personId = playerId; person.score = score;
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
     * @param k
     * @return
     */
    public int top(int k) {
        int score = 0;
        for (int i = scores.size()-1; i >= 0 && k > 0; k--, i--) {
            score += scores.get(i);
        }
        return score;
    }

    /**
     * remove player id from the lader board
     * @param playerId
     */
    public void reset(int playerId) {
        Person person = personMap.get(playerId);
        personMap.remove(playerId);
        int id = Collections.binarySearch(scores, person.score);
        scores.remove(id);
    }
}
