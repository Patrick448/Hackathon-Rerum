package ast;

import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class Param extends Node{
    private ID id;
    private Type type;

    public Param(int l, int c, ID id, Type type) {
        super(l, c);
        this.id = id;
        this.type = type;
    }

    public ID getId() {
        return id;
    }

    public Type getType() {
        return type;
    }
    
    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }

    @Override
    public String dotString(){
        String s = getUid() + " [label=\""+ this.getClass().getSimpleName()+"\"]\n";
        
        s+= getUid() +"--"+id.getUid()+"\n";
        s+=id.dotString();

        s+= getUid() +"--"+type.getUid()+"\n"; 
        s+= type.dotString();  
        
        
        return s;
    }
    
    public String toString(){
        String s = id.toString() + "::" + type.toString();
        
        return s;
    }

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
