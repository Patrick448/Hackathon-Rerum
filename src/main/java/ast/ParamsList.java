package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class ParamsList extends Node {

    private List<Param> paramsList;

    public ParamsList(int l, int c, Param first) {
        super(l, c);
        paramsList = new ArrayList<Param>();
        if (first != null)
            paramsList.add(first);
    }

    public void addNode(Param p) {
        paramsList.add(p);
    }

    public List<Param> getParamsList() {
        return paramsList;
    }

    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }

    @Override
    public String dotString() {
        String s = getUid() + " [label=\""+ this.getClass().getSimpleName()+"\"]\n";
        for (Param p : paramsList) {
            s += getUid() + "--" + p.getUid() + "\n";
            s += p.dotString();
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "";
        for (Param p : paramsList) {
            s += p.toString() + ", ";
        }
        return s;
    }

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
    
}
