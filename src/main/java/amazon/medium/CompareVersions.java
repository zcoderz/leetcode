package amazon.medium;

/**
 * this is a great question.
 * shows that thinking thoroughly in the beginning of solving a problem is so critical.
 *
 */
public class CompareVersions {

    public static void main(String [] args) {
        String strA = "1.0";
        String strB = "1.0.0";

        CompareVersions ver = new CompareVersions();
        System.out.println(ver.compareVersion(strA, strB));

    }

    /**
     * below is an extremely simple but elegant solution for the problem
     * it could be a lot more complicated with many more conditions if the problem wasn't simplified as below
     *
     * because versions could be constructed as 1.0.0.0.0.0.0.1 & 1.0.0.1
     * or they can be constructed as 1.0.0.0.0.10 vs 1.0.0.0.0.9
     * or they could be 1.1.0 and 1.1.0.0.0.0 which are same
     * you need a way where you :
     * 1. divide the version into chunks split on '.'
     * 2. do integer comparison instead of string comparison 10 > 9 but doesnt work with string comparison
     * 3. pad the version with smaller chunks with a 0 when its chunk terminates. this idea is key to simplifying this
     * otherwise fairly complicated problem.
      * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String [] s1 = version1.split("\\.");
        String [] s2 = version2.split("\\.");

        int oneSz = s1.length;
        int twoSz = s2.length;
        int maxSz = Math.max(oneSz, twoSz);

        for (int i =0; i < maxSz; i++) {
            int a = i < oneSz ? Integer.parseInt(s1[i]) : 0;
            int b = i < twoSz ? Integer.parseInt(s2[i]) : 0;
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
