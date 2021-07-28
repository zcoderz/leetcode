package box;

public class SetAction implements  ModificationInterface {
    String key;
    String value;

    public SetAction(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
