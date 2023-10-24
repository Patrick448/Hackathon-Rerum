package util;

public class ScopeTable {

    private int scope_level;

    private SymbolTable[] scopes;

    public ScopeTable() {
	scope_level = 0;
	scopes = new SymbolTable[8];
	scopes[scope_level] = new SymbolTable();
    }

    public int getLevel() {
	return scope_level;
    }
    
    public void put(String key, Symbol value) {
	scopes[scope_level].put(key, value);
    }
    
    public int push() {
	++scope_level;
	if(scope_level == scopes.length) {
	    SymbolTable[] aux = new SymbolTable[2*scopes.length];
	    for(int i = 0; i < scopes.length; ++i)
		aux[i] = scopes[i];
	    scopes = aux;
	}
	scopes[scope_level] = new SymbolTable();
	return scope_level;
    }

    public int pop() {
	scope_level--;
	return scope_level;
    }

    public Pair<Symbol, Integer> search(String key) {
	int level = scope_level;
	while(level >= 0) {
	    Symbol s = scopes[level].lookup(key);
	    if(s != null)
		return new Pair<Symbol, Integer>(s, new Integer(level));
	    level--;
	}
	return null;
    }
}
