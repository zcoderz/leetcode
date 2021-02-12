package search.binary_search;

/**
 * 744. Find Smallest Letter Greater Than Target
 *
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
 * find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 *
 */
public class SmallestLetterGreaterThanTarget {

    public static void main(String[] args) {
        SmallestLetterGreaterThanTarget sTest = new SmallestLetterGreaterThanTarget();
        char[] test = {'c', 'f', 'j'};
        char c = sTest.nextGreatestLetter(test, 'j');
        System.out.println(c);
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        // see  that high is one past the end of string so if the char to find is the last char in string
        // we would wrap around to first char
        int high = letters.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return letters[low % letters.length]; //wrap to first char if went past the end
    }
}
