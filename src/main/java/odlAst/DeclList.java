package odlAst;

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DeclList extends Node {
    
    private List<Decl> list;

    public DeclList(int l, int c, Decl decl) {
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

    /*
    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }*/

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
    public void accept(ODLVisitor v) {
        v.visit(this);
    }

}
