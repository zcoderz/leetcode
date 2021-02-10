package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
 *
 * Follow up: Could you do this in O(n) runtime?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 *
 * IMP-1: Very important question to practice. The solution is unique and elegant.
 */
public class MaxXorOfTwoArrays {

    public static void main(String[] args) {
        int[] nums = {5, 8};

        MaxXorOfTwoArrays maxXorOfTwoArrays = new MaxXorOfTwoArrays();
        int maxXor = maxXorOfTwoArrays.findMaximumXOR(nums);
        System.out.println(maxXor);
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int len = Integer.toBinaryString(maxNum).length();
        int bitMask = 1 << len;
        int maxXor = 0;
        for (int num : nums) {
            int max = buildTrie(num, root, bitMask);
            maxXor = Math.max(maxXor, max);
        }
        return maxXor;
    }

    /**
     * this is a very clever approach to calc max xor on an array of numbers
     *
     * @param num
     * @param node
     * @param bitMask
     * @return
     */
    int buildTrie(int num, TrieNode node, int bitMask) {
        //this is so that each number has the same number of bits
        String binary = Integer.toBinaryString(num | bitMask).substring(1);
        TrieNode xorNode = node;
        int maxXor = 0;
        //this is a great idea. traverse the path of the opposite bits
        //update an integer that reflects the path of xor bits calculated so far
        for (Character ch : binary.toCharArray()) {
            maxXor = maxXor << 1; //left shift at each iteration so the bit is accounted at its correct final index
            Integer chInt = ch == '0' ? 0 : 1;
            //create the new node if it doesn't already exist
            TrieNode newNode = node.map.computeIfAbsent(chInt, k -> new TrieNode());
            Integer opposite = chInt == 0 ? 1 : 0;
            TrieNode nextXorNode;
            //retrieve the opposite node
            nextXorNode = xorNode.map.get(opposite);
            if (nextXorNode == null) {
                //if next opposite was missing traverse the path of current bit
                nextXorNode = xorNode.map.get(chInt);
            } else {
                //add 1 to maxOr to reflect the increase
                maxXor += 1;
            }
            //update pointers for next iteration
            xorNode = nextXorNode;
            node = newNode;
        }
        return maxXor;
    }

    class TrieNode {
        Map<Integer, TrieNode> map = new HashMap<>();
    }
}
