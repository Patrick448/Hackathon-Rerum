package odlAst;

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ClassList extends Node {

    ID id;
    List<ClassAst> list;
    int uid = IdGenerator.getNextId();

    public ClassList(int l, int c, ClassAst data) {
          super(l, c);
          list = new ArrayList<ClassAst>();
          list.add(data);
    }

    
    public void addNode(ClassAst n) {
        list.add(n);
    }

    /*@Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, ClassAst> datas, Stack<List<Object>> returns, ScopeVisitor v){
        for(ClassAst n : list)
        {
            datas.put(n.getId().getName(), n); 
        }
        return 0;
    }
*/
    public String dotString(){
        String s = getUid() + " [label=ClassList]\n";

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

    public List<ClassAst> getList() {
        return list;
    }


    @Override
    public void accept(ODLVisitor v) {
        v.visit(this);
    }

    /*
    @Override
    public Object interpret(Stack<HashMap<String, Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v) {
        return null;
    }*/

}
