package ast;

import java.util.HashMap;

public class DataInstance {
    private Type type;
    private HashMap<String, Object> attributes;

    public DataInstance(Type type, HashMap<String, Object> attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    public Type getType() {
        return type;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void put(String name, Object value) {
        attributes.put(name, value);
    }

    public Object get(String name) {
        return attributes.get(name);
    }

    public String toString() {
        return type.toString() + " " + attributes.toString();
    }

}
