package util;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Symbol> map;

    public SymbolTable() {
	map = new HashMap<String, Symbol>();
    }

    public void put(String key, Symbol value) {
	map.put(key, value);
    }

    public Symbol lookup(String key) {
	return map.get(key);
    }
}
