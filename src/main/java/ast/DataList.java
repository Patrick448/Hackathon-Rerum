package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class DataList extends Node {
    
    ID id;
    List<Data> list;
    int uid = IdGenerator.getNextId();

    public DataList(int l, int c, Data data) {
          super(l, c);
          list = new ArrayList<Data>();
          list.add(data);
    }

    
    public void addNode(Data n) {
        list.add(n);
    }

    @Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        for(Data n : list)
        {
            datas.put(n.getId().getName(), n); 
        }
        return 0;
    }

    public String dotString(){
        String s = getUid() + " [label=DataList]\n";

        for(Node n : list) {
            s+= getUid() +"--"+n.getUid()+"\n";
            s+= n.dotString();
        }

        return s;
    }

    public String toString() {
        String s = "DataList:{";

        for(Node n : list) {
            s+= n.toString() + ", ";
        }
        s+="}";

        return s;
    }

    public List<Data> getList() {
        return list;
    }


    @Override
    public void accept(Visitor v) {
         v.visit(this);
    }

}
