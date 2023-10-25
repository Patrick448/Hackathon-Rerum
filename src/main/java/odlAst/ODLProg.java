package odlAst;

/*
 * Esta classe representa um comando de .
 * Expr
 */

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ODLProg extends Node {

    private Stack<List<Object>> stackReturns;
    private Stack<HashMap<String,Object>> stackVariables;

    private ClassList classList;

    public ODLProg(int l, int c, ClassList classList) {
        super(l, c);
        this.classList = classList;
        this.stackReturns = new Stack<List<Object>>();
        this.stackVariables = new Stack<HashMap<String,Object>>();
    }


    public Stack<List<Object>> getStackReturns() {
        return stackReturns;
    }

    public ClassList getClassList() {
        return classList;
    }

    public void setClassList(ClassList classList) {
        this.classList = classList;
    }

    public Stack<HashMap<String,Object>> getStackVariables() {
        return stackVariables;
    }

    public String toString(){
        String s = "";
        if(classList != null)
            s+= classList.toString() +"\n";



        return "Prog:\n "+s+"\n";
    }

    public String dotString(){
        String s = "graph program {\n";
        s+= getUid() + " [label=Prog]\n";
        if(classList != null){
            s+= getUid()+"--" +classList.getUid()+"\n";
            s+= classList.dotString();
        }



        s+="}";

        return s+"\n";
    }

   /* public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        //if (classList != null) classList.tryInterpret(stackVariables, funcList.getList(), hashDatas, stackReturns, v);
        return 0;
    }*/

    @Override
    public void accept(ODLVisitor v) {
        v.visit(this);
    }

}
