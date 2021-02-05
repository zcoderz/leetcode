package frequent.medium;

/**
 * 528. Random Pick with Weight
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 *
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3],
 * the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)
 * while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 *
 * More formally, the probability of picking index i is w[i] / sum(w).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0.
 * Since there is only one single element on the array the only option is to return the first element.
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
        StringBuilder builder = null;
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
