package trie;

import utils.TrieNode;

import java.util.*;

public class WordSearchTwo {

    public static void main(String [] args) {
//        char [][] board =       {{'o','a','a','n'},
//                {'e','t','a','e'},
//                {'i','h','k','r'},
//                {'i','f','l','v'}};
//        String [] words = {"oath","pea","eat","rain"};
        char [][] board =       {{'a'}};
        String [] words = {"a"};

        WordSearchTwo ws = new WordSearchTwo();
        List<String> fWords = ws.findWords(board, words);
        for (String word : fWords) {
            System.out.println(word);
        }
    }

    List<String> wordsFound = new ArrayList<>();



    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        root.buildTrie(words);

        int maxRows = board.length;
        if (maxRows ==0) return wordsFound;
        int maxCols = board[0].length;
        if (maxCols==0) return wordsFound;

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
        int [][]moves = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        board[row][col] = '#';

        for (int [] move : moves) {
            int tmpRow = row + move[0];
            int tmpCol = col + move[1];

            if (tmpRow >= 0 && tmpCol >= 0 && tmpRow < maxRows && tmpCol < maxCols) {
                char boardCharNew = board[tmpRow][tmpCol];
                TrieNode cNode = node.getChildNode(boardCharNew);
                if (cNode != null) {
                    traverseBoard(board, cNode, tmpRow, tmpCol);
                    if (cNode.getChildChars().isEmpty()) {
                        node.removeChar(boardCharNew);
                        //break;
                    }
                }
            }
        }
        board[row][col] = boardChar;

        //if (node.getChildChars().isEmpty()) {
        //   parent.removeChar(boardChar);
        //}
    }

}
