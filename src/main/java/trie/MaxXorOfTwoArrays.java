package trie;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * this is a very interesting application of a trie
 * create a trie of bits and use that to figure out xor
 */
public class MaxXorOfTwoArrays {

    public static void main(String[] args) {
        int[] nums = {5, 8};

        MaxXorOfTwoArrays maxXorOfTwoArrays = new MaxXorOfTwoArrays();
        int maxXor = maxXorOfTwoArrays.findMaximumXOR(nums);
        System.out.println(maxXor);
    }

    class TrieNode {
        Map<Integer, TrieNode> map = new HashMap<>();
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

    int buildTrie(int num, TrieNode node, int bitMask) {
        String binary = Integer.toBinaryString(num | bitMask).substring(1);
        TrieNode xorNode = node;
        int maxXor = 0;
        for (Character ch : binary.toCharArray()) {
            maxXor = maxXor << 1;
            Integer chInt = ch == '0' ? 0 : 1;
            TrieNode newNode = node.map.computeIfAbsent(chInt, k -> new TrieNode());
            Integer opposite = chInt == 0 ? 1 : 0;
            TrieNode nextXorNode;
            nextXorNode = xorNode.map.get(opposite);
            if (nextXorNode == null) {
                nextXorNode = xorNode.map.get(chInt);
            } else {
                maxXor += 1;
            }
            xorNode = nextXorNode;
            node = newNode;
        }
        return maxXor;
    }
}
