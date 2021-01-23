package design;

import utils.Pair;

import java.util.LinkedList;

/**
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
