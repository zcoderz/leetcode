package frequent.medium;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters
 * from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 * Example 1:
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 *
 * IMP-3: Common question
 */
public class RemoveAdjacentDuplicates {

    public static void main(String [] args) {
        String s= "pbbcggttciiippooaais";
        RemoveAdjacentDuplicates remAdjDups = new  RemoveAdjacentDuplicates();
        String out = remAdjDups.removeDuplicates(s, 2);
        System.out.println(out);
    }

    /**
     * the method removes duplicates from a string . duplicate is denoted when same char repeats k times.
     * it does the removal exactly when k is reached.
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {
        StringBuilder builder;
        boolean dupExists = true;
        while (dupExists) {
            int count = 0;
            char prior = 'Z';
            dupExists = false;
            builder = new StringBuilder();
            for (int i =0; i < s.length(); i++) {
                char c = s.charAt(i);
                builder.append(c);
                count = c == prior ? count + 1 : 1;
                if (count == k && c == prior) {
                    builder.delete(builder.length() - count, builder.length());
                    dupExists = true;
                    count = 0;
                }
                prior = c;
            }
            s = builder.toString();
        }
        return s;
    }
}
