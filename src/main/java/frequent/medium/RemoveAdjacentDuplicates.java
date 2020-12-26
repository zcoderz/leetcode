package frequent.medium;

/**
 * remove duplicates from a string
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
