package design;

import utils.Pair;

import java.util.LinkedList;

/**
 * 353. Design Snake Game
 * Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.
 *
 * When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 *
 * SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
 * int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.
 *
 * Input
 * ["SnakeGame", "move", "move", "move", "move", "move", "move"]
 * [[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
 * Output
 * [null, 0, 0, 1, 1, 2, -1]
 *
 * Explanation
 * SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
 * snakeGame.move("R"); // return 0
 * snakeGame.move("D"); // return 0
 * snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears
 *                      // at (0, 1).
 * snakeGame.move("U"); // return 1
 * snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
 * snakeGame.move("U"); // return -1, game over because snake collides with border
 *
 * Simple question. check the modularity in the code.
 */
public class SnakeGame {

    int cols;
    int rows;
    int[][] food;
    int foodIndex = 0;
    //direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
    int[] rowMoves = {-1, 0, 0, 1};
    int[] colMoves = {0, -1, 1, 0};
    LinkedList<Pair<Integer, Integer>> snake = new LinkedList<>();

    public SnakeGame(int width, int height, int[][] food) {
        cols = width;
        rows = height;
        this.food = food;
        Pair<Integer, Integer> start = new Pair<>(0, 0);
        snake.add(start);
    }

    public static void main(String[] args) {
        int[][] food = {{0, 1}};
        SnakeGame snakeGame = new SnakeGame(2, 2, food);
        snakeGame.move("R");
        snakeGame.move("D");
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over when snake crosses the screen boundary
     * or bites its body.
     */
    public int move(String direction) {
        int dir = 0;
        switch (direction) {
            case "U" -> dir = 0;
            case "L" -> dir = 1;
            case "R" -> dir = 2;
            case "D" -> dir = 3;
        }

        Pair<Integer, Integer> head = snake.getFirst();
        int newR = head.first + rowMoves[dir];
        int newCol = head.second + colMoves[dir];

        if (newR < 0 || newR >= rows || newCol < 0 || newCol >= cols) {
            return -1;
        }

        if (checkHittingSnake(newR, newCol)) {
            return -1;
        }

        if (!checkFood(newR, newCol)) {
            removeLastTailSq();
        }
        Pair<Integer, Integer> newHead = new Pair<>(newR, newCol);
        snake.addFirst(newHead);
        return foodIndex;
    }

    /**
     * remove snake's tail
     */
    void removeLastTailSq() {
        snake.removeLast();
    }

    /**
     * check whether the snake found the food
     *
     * @param newR
     * @param newC
     * @return
     */
    boolean checkFood(int newR, int newC) {
        //if already at end dont check as it will throw an index out of bound exception
        if (foodIndex >= food.length) return false;

        int[] foodL = food[foodIndex];
        if (newR == foodL[0] && newC == foodL[1]) {
            foodIndex++;
            return true;
        }
        return false;
    }

    /**
     * check that the snake is hitting its tail as it moves forward
     *
     * @param row
     * @param col
     * @return
     */
    boolean checkHittingSnake(int row, int col) {
        Pair<Integer, Integer> tail = snake.getLast();
        for (Pair<Integer, Integer> pair : snake) {
            if (pair.equals(tail)) {
                break;
            }
            if (pair.first == row && pair.second == col) {
                return true;
            }
        }
        return false;
    }
}
