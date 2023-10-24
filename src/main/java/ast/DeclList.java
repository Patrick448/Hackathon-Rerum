package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class DeclList extends Node {
    
    private List<Decl> list;

    public DeclList(int l, int c,Decl decl) {
          super(l, c);
          list = new ArrayList<Decl>();
          list.add(decl);
    }

    
    public void addNode(Decl n) {
        list.add(n);
    }

    public List<Decl> getList() {
        return list;
    }

    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }

    public String dotString(){
        String s = getUid() + " [label=DeclList]\n";

        for(Node n : list) {
            s+= getUid() +"--"+n.getUid()+"\n";
            s+= n.dotString();
        }

        return s;
    }

    public String toString() {
        String s = "";

        for(Node n : list) {
            s+= n.toString() + ",";
        }
        return s;
    }


    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
