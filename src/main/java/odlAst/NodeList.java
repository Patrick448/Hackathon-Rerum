package odlAst;

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class NodeList extends Node {
    
    Node id;
    List<Node> list;

    public NodeList(int l, int c, Node data) {
          super(l, c);
          list = new ArrayList<Node>();
          list.add(data);
    }

    
    public void addNode(Node n) {
        list.add(n);
    }

    /*@Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }*/

    public String toString() {
        String s = "";

        for(Node n : list) {
            s+= n.toString() + ", ";
        }

        return s;
    }


	@Override
	public void accept(ODLVisitor v) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'accept'");
	}

}
