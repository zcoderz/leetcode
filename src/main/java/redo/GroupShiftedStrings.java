package redo;

import java.util.*;

public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mapWords = new HashMap<>();
        for (String str: strings) {
            StringBuilder charDelta =  new StringBuilder();
            for (int i =0; i < str.length(); i++) {
                int prior = i ==0? str.length()-1 : i -1;
                char currC = str.charAt(i);
                char priorC = str.charAt(prior);
                int diff = currC - prior;
                if (diff < 0) {
                    diff = 'z' - priorC + currC - 'a' + 1;
                }
                charDelta.append(diff);
            }
            List<String> strRes = mapWords.getOrDefault(charDelta.toString(), new ArrayList<>());
            strRes.add(str);
            mapWords.put(charDelta.toString(), strRes);
        }
        List<List<String>> retList = new ArrayList<>();
        for (List<String> res: mapWords.values()) {
            Collections.sort(res);
            retList.add(res);
        }
        return retList;
    }
}
