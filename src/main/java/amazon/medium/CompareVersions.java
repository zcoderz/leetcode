package amazon.medium;

/**
 * 165. Compare Version Numbers
 * this is a great question. shows that thinking thoroughly in the beginning of solving a problem is so critical.
 *
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may
 * contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right,
 * with the leftmost revision being revision 0, the next revision being revision 1,
 * and so on. For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using
 * their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index, then treat the revision as 0. For example,
 * version 1.0 is less than version 1.1 because their revision 0s are the same,
 * but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 *
 *
 * Example 1:
 *
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 *
 * IMP-1
 */
public class CompareVersions {

    public static void main(String[] args) {
        String strA = "1.0";
        String strB = "1.0.0";

        CompareVersions ver = new CompareVersions();
        System.out.println(ver.compareVersion(strA, strB));

    }

    /**
     * below is an extremely simple but elegant solution for the problem it could be a lot more complicated with many
     * more conditions if the problem wasn't simplified as below
     * <p>
     * because versions could be constructed as 1.0.0.0.0.0.0.1 & 1.0.0.1 or they can be constructed as 1.0.0.0.0.10 vs
     * 1.0.0.0.0.9 or they could be 1.1.0 and 1.1.0.0.0.0 which are same you need a way where you : 1. divide the
     * version into chunks split on '.' 2. do integer comparison instead of string comparison 10 > 9 but doesnt work
     * with string comparison 3. pad the version with smaller chunks with a 0 when its chunk terminates. this idea is
     * key to simplifying this otherwise fairly complicated problem.
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        int oneSz = s1.length;
        int twoSz = s2.length;
        int maxSz = Math.max(oneSz, twoSz);

        for (int i = 0; i < maxSz; i++) {
            int a = i < oneSz ? Integer.parseInt(s1[i]) : 0;
            int b = i < twoSz ? Integer.parseInt(s2[i]) : 0;
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
