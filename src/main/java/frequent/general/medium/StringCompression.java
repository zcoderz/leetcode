package frequent.general.medium;

public class StringCompression {

    public static void main(String [] args) {

        //char [] ch = {'a','a', 'b', 'b', 'b', 'c'};
        char [] ch = {'a', 'b', 'b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b', 'c'};
        StringCompression strComp = new StringCompression();
        int i = strComp.compressFast(ch);
        for (int j = 0; j < i; j++) {
            System.out.print(ch[j]);
        }
    }

    /**
     * the method compresses chars by reducing repeats
     * it uses O(1) space where it leverages the space in orig array for comrpession
     * @param chars
     * @return
     */
    public int compressFast(char[] chars) {
        int priorNo = 0;
        int priorIndex = 0;
        char priorChar = chars[0];

        for (char ch : chars) {
            if (ch != priorChar) {
                priorIndex = processCharCopy(chars, priorIndex, priorNo, priorChar);
                priorNo = 0;
                priorChar = ch;
            }
            priorNo++;
        }
        priorIndex = processCharCopy(chars, priorIndex, priorNo, priorChar);
        return priorIndex;
    }

    /**
     * copy chars into orig array
     * @param chars
     * @param priorIndex
     * @param priorNo
     * @param priorChar
     * @return
     */
    int processCharCopy(char[] chars, int priorIndex, int priorNo, char priorChar) {
        chars[priorIndex++] = priorChar;
        if (priorNo > 1) {
            String strInt = Integer.toString(priorNo);
            for(int j = 0; j < strInt.length();j++) {
                chars[priorIndex++] = strInt.charAt(j);
            }
        }
        return priorIndex;
    }
}
