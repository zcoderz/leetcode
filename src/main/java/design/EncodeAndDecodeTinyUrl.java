package design;

import java.util.HashMap;
import java.util.Map;

/**
 * 535. Encode and Decode TinyURL
 * Medium
 *
 * 789
 *
 * 1650
 *
 * Add to List
 *
 * Share
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL
 * such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 */
public class EncodeAndDecodeTinyUrl {
    public static void main(String [] args) {
        EncodeAndDecodeTinyUrl encode = new EncodeAndDecodeTinyUrl();
        String url = "https://leetcode.com/problems/design-tinyurl";
        String val = encode.encodeHash(url);
        String url2 = encode.decodeHash(val);

        System.out.println(url);
        System.out.println(url2);
    }

    //count of the distinct url request
    long count = 0;
    //base62 char mappings
    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //hash map for look ups
    Map<String, String> map62 = new HashMap<>();
    Map<String, String> mapHash = new HashMap<>();

    /**
     * uses the hash of the long url to encode a short string
     * @param longUrl
     * @return
     */
    public String encodeHash(String longUrl) {
        int hash = Math.abs(longUrl.hashCode());
        StringBuilder origKey = new StringBuilder(convertBase10To62(hash));
        String key = origKey.toString();
        int offset = 0;
        //if there is a conflict roll the key until you find a unique key
        while (mapHash.containsKey(key)) {
            if ((offset % 62) ==0) {
                origKey.append("Z");
                offset=0;
            }
            key = origKey.toString() + chars.charAt(offset);
            offset++;
        }
        mapHash.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    /**
     * convert the short url to its equivalent long url
     * @param shortUrl
     * @return
     */
    public String decodeHash(String shortUrl) {
        return mapHash.get(shortUrl.replace("http://tinyurl.com/", ""));
    }

    /**
     * decode's the long url to equivalent short url
     * @param longUrl
     * @return
     */
    public String encode62(String longUrl) {
        String key = convertBase10To62(++count);
        map62.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    /**
     * uses the mapping to return the equivalent long url
     * @param shortUrl
     * @return
     */
    public String decode62(String shortUrl) {
        return map62.get(shortUrl.replace("http://tinyurl.com/", ""));
    }

    /**
     * converts the val to base 62 string
     * @param val
     * @return
     */
    public String convertBase10To62(long val) {
        StringBuilder sb = new StringBuilder();
        while (val > 0) {
            //find 62 bit based char corresponding to the current mod value
            sb.append(chars.charAt((int) (val % 62)));
            val /= 62; //divide the value by 62
        }
        return sb.toString();
    }
}
