package face_book.medium;

import utils.graph.generic.UnionFindNode;

import java.util.*;

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
