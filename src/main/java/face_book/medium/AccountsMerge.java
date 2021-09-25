package face_book.medium;

import utils.graph.generic.UnionFindNode;

import java.util.*;

/**
 * 227. Basic Calculator II
 * Medium
 *
 * 2869
 *
 * 424
 *
 * Add to List
 *
 * Share
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 *
 */
public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, UnionFindNode<String>> emailToUnionMap = new HashMap<>();
        Map<String, String> emailToNameMap = new HashMap<>();
        for (List<String> list: accounts) {
            Iterator<String> iter = list.listIterator();
            String name = iter.next();
            UnionFindNode<String> priorUnion = null;
            while (iter.hasNext()) {
                String email = iter.next();
                emailToNameMap.put(email, name);
                UnionFindNode<String> union = emailToUnionMap.get(email);
                if (union == null) {
                    union = new UnionFindNode<>(email);
                    emailToUnionMap.put(email, union);
                }
                if (priorUnion != null) {
                    priorUnion.union(union);
                } else {
                    priorUnion = union;
                }
            }
        }
        Map<String, LinkedList<String>> accountMap = new HashMap<>();
        for(String email: emailToNameMap.keySet()) {
            UnionFindNode<String> unionFindNode = emailToUnionMap.get(email);
            String parent = unionFindNode.find().getValue();
            LinkedList<String> emails = accountMap.getOrDefault(parent, new LinkedList<>());
            emails.add(email);
            accountMap.put(parent, emails);
        }
        List<List<String>> ret = new ArrayList<>();
        for (LinkedList<String> emails: accountMap.values()) {
            String email = emails.get(0);
            String name = emailToNameMap.get(email);
            Collections.sort(emails);
            emails.addFirst(name);
            ret.add(emails);
        }
        return ret;
    }

}
