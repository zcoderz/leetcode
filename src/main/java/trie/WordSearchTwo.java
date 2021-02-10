package trie;

import utils.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * IMP-1: Excellent practice problem
 * Leet code has a great explanation for it : https://leetcode.com/problems/word-search-ii/solution/
 */
public class WordSearchTwo {

    List<String> wordsFound = new ArrayList<>();
    int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        char [][] board =       {{'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
        String [] words = {"oath","pea","eat","rain"};
//        char[][] board = {{'a'}};
//        String[] words = {"a"};

        WordSearchTwo ws = new WordSearchTwo();
        List<String> fWords = ws.findWords(board, words);
        for (String word : fWords) {
            System.out.println(word);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        root.buildTrie(words);

        int maxRows = board.length;
        if (maxRows == 0) return wordsFound;
        int maxCols = board[0].length;
        if (maxCols == 0) return wordsFound;

        for (int iRow = 0; iRow < maxRows; iRow++) {
            for (int iCol = 0; iCol < maxCols; iCol++) {
                char boardChar = board[iRow][iCol];
                TrieNode cNode = root.getChildNode(boardChar);
                if (cNode != null) {
                    traverseBoard(board, cNode, iRow, iCol);
                }
            }
        }

        return wordsFound;
    }

    void traverseBoard(char[][] board, TrieNode node, int row, int col) {
        char boardChar = board[row][col];
        if (node.getWord() != null) {
            wordsFound.add(node.getWord());
            node.setWord(null);
        }

        int maxRows = board.length;
        int maxCols = board[0].length;


        board[row][col] = '#';

        for (int[] move : moves) {
            int tmpRow = row + move[0];
            int tmpCol = col + move[1];

            if (tmpRow >= 0 && tmpCol >= 0 && tmpRow < maxRows && tmpCol < maxCols) {
                char boardCharNew = board[tmpRow][tmpCol];
                TrieNode cNode = node.getChildNode(boardCharNew);
                if (cNode != null) {
                    traverseBoard(board, cNode, tmpRow, tmpCol);
                    if (cNode.getChildChars().isEmpty()) {
                        //Optimization
                        //Gradually prune the nodes in Trie during the backtracking.
                        //The idea is motivated by the fact that the time complexity of
                        //the overall algorithm depends on the size of
                        //the Trie. For a leaf node in Trie, once we traverse it (i.e. find a matched word),
                        //we would no longer need to traverse it again.
                        //As a result, we could prune it out from the Trie.
                        //Gradually, those non-leaf nodes could become leaf nodes later
                        //since we trim their children leaf nodes.
                        //This pruning measure could reduce up to 50% of the running time
                        //for the test cases of the online judge.
                        node.removeChar(boardCharNew);
                    }
                }
            }
        }
        board[row][col] = boardChar;
    }

}
