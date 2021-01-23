package strings;

public class RabinKarpSubString {

    int prime = 101;

    public static void main(String[] args) {
        RabinKarpSubString search = new RabinKarpSubString();
        String a = "abcdefghi";
        String b = "def";
        boolean found = search.subStringFound(b, a);
        System.out.println(found);

        b = "dei";
        found = search.subStringFound(b, a);
        System.out.println(found);
    }

    public boolean subStringFound(String strToFind, String strToSearchFrom) {
        int strToFindLen = strToFind.length();
        int stringToFindHash = createHash(strToFind.toCharArray(), strToFindLen);
        int mainStringHash = createHash(strToSearchFrom.toCharArray(), strToFindLen);
        int j = strToFindLen;
        int i = 0;
        double lastPowerPrime = Math.pow(prime, strToFindLen - 1);
        //rolling hash
        while (j < strToSearchFrom.length()) {
            if (mainStringHash == stringToFindHash) {
                if (validate(strToFind, strToSearchFrom, i)) {
                    return true;
                }
            }
            mainStringHash -= strToSearchFrom.charAt(i); //remove chat at first index
            mainStringHash /= prime; //divide by prime to roll the prior value forward
            //add char at new index
            mainStringHash += lastPowerPrime * strToSearchFrom.charAt(j);
            j++;
            i++;
        }
        return false;
    }

    /**
     * hashes may match while actual string is diff, so match the actual string char by char to confirm
     *
     * @param strToFind
     * @param strOrigString
     * @param startIndex
     * @return
     */
    boolean validate(String strToFind, String strOrigString, int startIndex) {
        for (int i = 0; i < strToFind.length(); i++) {
            if (strToFind.charAt(i) != strOrigString.charAt(i + startIndex)) {
                return false;
            }
        }
        return true;
    }

    /**
     * simple algo to create a hash for the char array
     *
     * @param charArr
     * @param len
     * @return
     */
    int createHash(char[] charArr, int len) {
        int hash = 0;
        for (int i = 0; i < len; i++) {
            hash += (charArr[i]) * Math.pow(prime, i);
        }
        return hash;
    }
}
