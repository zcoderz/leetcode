package frequent.general.medium;

/**
 * the O(n) solution below while simple is hard to think about
 * easier approach is via recursion but problem with that is it times out on leet code
 * on the recursive solution, you can track count from an index so that if you fall back on that index
 * you reuse the previously calculated value.
 */
public class DecodeString {

    public static void main(String []args) {
        DecodeString dS = new DecodeString();
        int count = dS.numDecodings("0");
        assert count == 0;
        count = dS.numDecodings("10");
        assert count == 1;
        count = dS.numDecodings("101");
        assert count == 1;
        count = dS.numDecodings("28");
        assert count == 1;

        count = dS.numDecodings("25");
        assert count == 2;

    }


    /**
     * this is a real elegant solution , picked from leet code.
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s== null || s.isEmpty()) {
            return  0;
        }
        int twoBack = 1; //initialize two back to 1
        int oneBack = 0; //initialize one back to 0 or 1 depending on first index
        if (s.charAt(0) != '0') {
            oneBack = 1;
        }
        //loop to end index while looking at one and two before.
        //add one or two before to current index depending on whether they classify
        for (int i =2 ; i <= s.length(); i++ ) {
            int v=0;
            //add prior count if prior is not 0; you cant move from 0 one forward
            if (s.charAt(i-1) != '0') {
                v += oneBack;
            }
            //add two prior if they are between 10 and 26, less than 9 signifies two index before was 0
            //more than 26 means greater than Z and hence doesnt have a mapping
            int val = Integer.parseInt(s.substring(i-2, i));
            if (val > 9 && val < 27) {
                v += twoBack;
            }
            //move the counters forward
            twoBack = oneBack;
            oneBack = v;
        }
        return oneBack;
    }


//    class StackItem {
//        int index;
//        boolean lookedAt;
//        public StackItem(int index, boolean lA) {
//            this.index = index;
//            this.lookedAt = lA;
//        }
//    }
//    Stack<StackItem> stack = new Stack<>();
//    void processDecodingStack() {
//        while (!stack.isEmpty()) {
//            if (stack.peek().lookedAt) {
//                stack.pop(); continue;
//            }
//            stack.peek().lookedAt = true;
//            int index = stack.peek().index;
//            if (index == len) {
//                count++;
//                stack.pop();
//                continue;
//            }
//            if (index > len) {
//                stack.pop();
//                continue; //moved too far right
//            }
//
//            if (str.charAt(index) == '0') {
//                stack.pop();
//                continue; //if you got to 0 char via sequence, its not a valid sequence
//            }
//
//            stack.push(new StackItem(index+1, false));
//
//            if ((index + 2) <= len) {
//                //int val = Integer.parseInt(str.substring(index, index + 2));
//                char ch = str.charAt(index);
//                char ch1 = str.charAt(index+1);
//                int val = ch - '0';
//                val = val * 10;
//                val += ch1 - '0';
//
//                if (val < 27) {
//                    stack.push(new StackItem(index+2, false));
//                }
//            }
//
//
//        }
//    }
//
//    Map<Integer, Integer> theCounts = new HashMap<>();
//    void processDecoding(int index) {
//        if (index == len) {
//            count++;
//            return;
//        }
//        if (index > len) {
//            return; //moved too far right
//        }
//
//        if (str.charAt(index) == '0') {
//            return; //if you got to 0 char via sequence, its not a valid sequence
//        }
//
//        Integer num = theCounts.get(index);
//        if (num != null) {
//             count += num;
//             return;
//        }
//        int increment = count;
//        processDecoding(index+1);
//
//        if ((index+2) <= len) {
//            int val = Integer.parseInt(str.substring(index, index+2));
//            if (val < 27) {
//                processDecoding(index+2);
//            }
//        }
//        increment = count - increment;
//        theCounts.put(index, increment);
//    }

}
