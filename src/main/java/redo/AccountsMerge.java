package redo;

import utils.graph.generic.UnionFindNode;

import java.util.*;

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, UnionFindNode<String>> mapEmailToGroup = new HashMap<>();
        Map<String, String> emailToNameMap = new HashMap<>();
        for (List<String> acctInfo: accounts) {
            String name = acctInfo.get(0);
            List<String> emails = acctInfo.subList(1, acctInfo.size());
            UnionFindNode<String> priorUnion = null;
            for (String email : emails) {
                emailToNameMap.put(email, name);
                UnionFindNode<String> unionCurrEmail = mapEmailToGroup.get(email);
                if (unionCurrEmail == null) {
                    unionCurrEmail = new UnionFindNode<>(email);
                }
                if (priorUnion == null) {
                    priorUnion = unionCurrEmail;
                }
                priorUnion.union(unionCurrEmail);
                mapEmailToGroup.put(email, unionCurrEmail);
            }
        }

        Map<String, LinkedList<String>> emailGroups = new HashMap<>();

        for (String email: emailToNameMap.values()) {
            String parentEmail = mapEmailToGroup.get(email).find().getValue();
            LinkedList<String> emails = emailGroups.getOrDefault(parentEmail, new LinkedList<>());
            emails.add(email);
            emailGroups.put(parentEmail, emails);
        }

        List<List<String>> ret = new LinkedList<>();
        for (Map.Entry<String, LinkedList<String>> emailG : emailGroups.entrySet()) {
            String name = emailToNameMap.get(emailG.getKey());
            Collections.sort(emailG.getValue());
            emailG.getValue().addFirst(name);
            ret.add(emailG.getValue());
        }
        return ret;
    }
}
