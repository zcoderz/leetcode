package box;

public class GetAction implements ModificationInterface{
    String key;

    public GetAction(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return null;
    }
}
