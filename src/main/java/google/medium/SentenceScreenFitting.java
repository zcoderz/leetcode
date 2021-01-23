package google.medium;


/**
 * 418. Sentence Screen Fitting
 * <p>
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given
 * sentence can be fitted on the screen.
 * <p>
 * Note:
 * <p>
 * A word cannot be split into two lines. The order of words in the sentence must remain unchanged. Two consecutive
 * words in a line must be separated by a single space. Total words in the sentence won't exceed 100. Length of each
 * word is greater than 0 and won't exceed 10. 1 ≤ rows, cols ≤ 20,000.
 * <p>
 * This question is much more tricky than what it appears to be for a solid performant solution. problem is that if
 * there are many rows and columns the logic repeats and will become slow.
 * <p>
 * Need a solution that could work for cases with many columns or rows in a performant manner.
 */
public class SentenceScreenFitting {

    public static void main(String[] args) {
        SentenceScreenFitting fitting = new SentenceScreenFitting();
        int rows = 3, cols = 6;
        String[] sentence = {"a", "bcd", "e"};
        int repeats = fitting.wordsTyping(sentence, rows, cols);
        System.out.println(repeats);


        rows = 2;
        cols = 8;
        String[] sentence1 = {"hello", "world"};
        repeats = fitting.wordsTyping(sentence1, rows, cols);
        System.out.println(repeats);


        rows = 8;
        cols = 7;
        String[] sentence2 = {"f", "p", "a"};
        repeats = fitting.wordsTyping(sentence2, rows, cols);
        System.out.println(repeats);
    }

    /**
     * below logic was taken from someone's discussion notes in leetcode. the logic is that for each starting word in
     * sentence you count : - the number of times the sentence would repeat in a single row - what would be the starting
     * word in the next row via the above two calculating the total times the sentence repeats becomes very easy as you
     * can iterate over the rows , tracking next word and sentence repetitions
     *
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] nextIndex = new int[sentence.length];
        int[] numberRepeatsPerWordStar = new int[sentence.length];

        for (int i = 0; i < sentence.length; i++) {
            int currWordIndex = i;
            int repeats = 0;
            int currIndex = 0;

            //loop till you reach the last column to find number of repeats that can go in a single row
            //assuming you started with a certain word. also check what would be the next word to start
            //the next row
            while (currIndex + sentence[currWordIndex].length() <= cols) {
                currIndex += sentence[currWordIndex++].length() + 1;
                if (currWordIndex == sentence.length) {
                    repeats++;
                    currWordIndex = 0;
                }
            }
            nextIndex[i] = currWordIndex;
            numberRepeatsPerWordStar[i] = repeats;
        }
        int totalRepeats = 0;
        int nextWordIndex = 0;
        for (int row = 0; row < rows; row++) {
            totalRepeats += numberRepeatsPerWordStar[nextWordIndex];
            nextWordIndex = nextIndex[nextWordIndex];
        }
        return totalRepeats;
    }

}
