package recursion;

/**
 * 779. K-th Symbol in Grammar
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each
 * occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 *
 * IMP-3: This is a simple question but one that requires careful analysis
 */
public class KthSymbol {

    public static void main(String [] args) {
        KthSymbol symbol = new KthSymbol();
        int j = symbol.kthGrammar(4, 8);
        System.out.println(j);
    }

    /**
     * below is a more expressive solution for the  faster algo in code below
     * both N & K start from 1
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar(int n, int k) {
        if (n==1) {
            //per the requirements of question the data starts from first row with a 0
            return 0;
        }
        //as you recurse up into a parent row, the kth digit is based in the parent row on parent value at index
        //(K+1) / 2
        int parent = kthGrammar(n-1, (k+1)/2);
        if (parent ==0) {
            if ((k%2)==0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if ((k%2)==0) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    /**
     * This is a tricky one! have to find the pattern in which the bit dependencies change from one row to next
     * draw it out on a white board and establish the below relation
     *
     * if parent is 1 , and k is odd then 1, else 0
     * if parent is 0 , and k is odd then 0, else 1
     * the above can be handled by (~k & 1) , & 1 is similar to %2 , finding whether even or odd
     * mixing with ^ recurse to handle
     *
     * (k + 1)/2 because the given bit depends on its parent which is represented by (k + 1 / 2)
     *
     * this can be established as (~k & 1) ^ parent (n-1, (k+1) /2 )
     * @param n
     * @param k
     * @return
     */
    public int kthGrammarFast(int n, int k) {
        if (n==1) {
            return 0;
        }
        return (~k & 1) ^ kthGrammarFast(n-1, (k+1)/2);
    }



    //this solution just times out.....too much memory and time for large n
    String strBegin = "0";
    public int kthGrammarSlow(int n, int k) {
        if (n==1) {
            return findK(k);
        }

        StringBuilder builder = new StringBuilder();
        for (int i =0 ; i < strBegin.length(); i++) {
            if(strBegin.charAt(i) == '0') {
                builder.append("01");
            } else {
                builder.append("10");
            }
        }
        strBegin = builder.toString();
        return kthGrammar(n-1, k);
    }

    private int findK(int k) {
        if (k > strBegin.length()) {
            return -1;
        }
        if (strBegin.charAt(k-1) == '0') {
            return 0;
        } else {
            return 1;
        }
    }

}
