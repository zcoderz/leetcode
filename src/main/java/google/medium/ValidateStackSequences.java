package google.medium;

import java.util.Stack;

/**
 * 946. Validate Stack Sequences
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been
 * the result of a sequence of push and pop operations on an initially empty stack.
 *
 *
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * IMP-1: Simple question that requires using stacks appropriately to validate behavior
 */
public class ValidateStackSequences {

    public static void main(String [] args) {
         int [] pushed = {1,2,3,4,5};
         int [] popped = {4,5,3,2,1};

        //int [] pushed = {2,1,0};
        //int [] popped = {1,2,0};

        ValidateStackSequences validateStackSequences = new ValidateStackSequences();
        boolean isValid = validateStackSequences.validateStackSequences(pushed, popped);
        System.out.println(isValid);
    }

    /**
     * repeat until end of push array reached :
     *   compare top of stack if non empty with the popped current index
     *   if match, pop element from stack. and move pop index forward.
     *   else if not match or empty add new element to stack from push array while moving push index forward.
     *
     * while stack is not empty
     *   check top of stack against pop array while moving pop index forward
     *
     * at end check if end of pop index has been reached. if it has then its indeed a valid sequence of push and pop
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i =0; i < pushed.length; ) {
            if (!stack.isEmpty() && popped[popIndex] == stack.peek()) {
                stack.pop();
                popIndex++;
            } else {
                stack.push(pushed[i++]);
            }
        }

        while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
            stack.pop(); popIndex++;
        }
        return popIndex == popped.length;
    }
}
