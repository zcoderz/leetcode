package google;

import java.util.ArrayList;
import java.util.List;

/**
 * 271. Encode and Decode Strings
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network
 * and is decoded back to the original list of strings.
 *
 */
public class EncodeAndDecodeStrings {

    public static void main(String [] args) {
        EncodeAndDecodeStrings encAndDecode = new EncodeAndDecodeStrings();
        List<String> list = new ArrayList<>();
        list.add("hello"); list.add("sam"); list.add("world");
        String strRes = encAndDecode.encode(list);
        List<String> parsed = encAndDecode.decode(strRes);
        System.out.println(parsed);
    }

    // Encodes a list of strings to a single string.
    public String encode(List<String> strings) {
        if (strings.isEmpty()) {
            return "0,";
        }
        StringBuilder string = new StringBuilder();
        //first write out the length of the list
        string.append(strings.size()).append(",");
        for (String str: strings) {
            //write length of each string followed by a sentinel and then the actual string
            string.append(str.length()).append(",").append(str);
        }
        return string.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strings = new ArrayList<>();
        int index = 0;
        //read the number of items in the list, this is to check if list size is 0 or otherwise
        StringBuilder length = new StringBuilder();
        while (Character.isDigit(s.charAt(index))) {
            length.append(s.charAt(index++));
        }
        int len = Integer.parseInt(length.toString());
        if (len ==0) {
            return strings;
        }
        index++;
        //read next element, add it to list and move on..
        while (index < s.length()-1) {
            length = new StringBuilder();
            while (Character.isDigit(s.charAt(index))) {
                length.append(s.charAt(index++));
            }
            index++;
            len = Integer.parseInt(length.toString());
            strings.add(s.substring(index, index+len));
            index = index + len;
        }
        return strings;
    }

}
