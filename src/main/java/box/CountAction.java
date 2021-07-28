package box;

public class CountAction implements  ModificationInterface {
    String value;

    public CountAction(String value) {
        this.value = value;
    }

    public String getKey() {
        return null;
    }

    public String getValue() {
        return value;
    }

}
