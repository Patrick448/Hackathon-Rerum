package ast;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.List;

public class FuncList extends Node{
    
    private List<Func> list;

    public FuncList(int l, int c, Func data) {
          super(l, c);
          list = new ArrayList<Func>();
          list.add(data);
    }

    
    public void addNode(Func n) {
        list.add(n);
    }

    public List<Func> getList() {
        return list;
    }

    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        if(functions.size()==0)
        {
            for(Func n : list)
            {
                functions.add(n);
            }
        }
        for(Func n : functions)
        {
            if(n.getId().getName().equals("main") && n.getParams()==null && n.getReturns()==null)
            {
                n.tryInterpret(variables, functions, datas, returns, v);
                break;
            }
        }
        return 0;
    }

    public String dotString(){
        String s = getUid() + " [label=FuncList]\n";

        for(Node n : list) {
            s+= getUid() +"--"+n.getUid()+"\n";
            s+= n.dotString();
        }

        return s;
    }

    public String toString() {
        String s = "FuncList:{";

        for(Node n : list) {
            s+= n.toString() + ", ";
        }
        s+="}";

        return s;
    }

    @Override
      public void accept(Visitor v) { v.visit(this);}

}
