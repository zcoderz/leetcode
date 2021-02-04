package dynamic;

import java.util.Stack;

/**
 * 55. Jump Game
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * IMP-1 : dynamic programming is tricky. this is a good question to practice.
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        CanJump jump = new CanJump();
        Boolean canDo = jump.canJump(nums);
        System.out.println(canDo);
    }

    public boolean canJump(int[] nums) {
        int lastIndex = nums.length - 1;

        for (int i = lastIndex - 1; i <= 0; i--) {
            if ((nums[i] + i) >= lastIndex) {
                lastIndex = i;
            }
        }

        return lastIndex == 0;
    }

    public boolean canJumpSlow(int[] nums) {
        int len = nums.length - 1;

        return processJump(nums, 0, len);

    }

    boolean processJump(int[] nums, int currIndex, int length) {

        Stack<Integer> stack = new Stack<>();
        int jumpLimit = nums[currIndex];
        for (int i = 1; i <= jumpLimit && jumpLimit <= length; i++) {
            stack.push(currIndex + i);
        }


        while (!stack.isEmpty()) {
            currIndex = stack.pop();

            if (currIndex == length) {
                return true;
            }

            jumpLimit = nums[currIndex];
            for (int i = 1; i <= jumpLimit && jumpLimit <= length; i++) {
                stack.push(currIndex + i);
            }

        }

        return false;
    }

    // Pair class
    public static class Pair<U, V> {
        public final U first;        // first field of a Pair
        public final V second;    // second field of a Pair

        // Constructs a new Pair with specified values
        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        // Factory method for creating a Typed Pair instance
        public static <U, V> Pair<U, V> of(U a, V b) {
            // calls private constructor
            return new Pair<>(a, b);
        }

        @Override
        // Checks specified object is "equal to" current object or not
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            // call equals() method of the underlying objects
            if (!first.equals(pair.first))
                return false;
            return second.equals(pair.second);
        }

        @Override
        // Computes hash code for an object to support hash tables
        public int hashCode() {
            // use hash codes of the underlying objects
            return 31 * first.hashCode() + second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

}
