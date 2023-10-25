package ast;

/*
 * Esta classe representa um comando de .
 * Expr
 */
import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class ODLProg extends Node {

    private HashMap<String, Data> hashDatas;
    private Stack<List<Object>> stackReturns;
    private Stack<HashMap<String,Object>> stackVariables;

    private ClassList classList;
    private FuncList funcList;

    public ODLProg(int l, int c, ClassList classList) {
        super(l, c);
        this.classList = classList;
        this.hashDatas = new HashMap<String, Data>();
        this.stackReturns = new Stack<List<Object>>();
        this.stackVariables = new Stack<HashMap<String,Object>>();
    }

    public HashMap<String, Data> getHashDatas() {
        return hashDatas;
    }

    public Stack<List<Object>> getStackReturns() {
        return stackReturns;
    }


    public FuncList getFuncList() {
        return funcList;
    }

    public Stack<HashMap<String,Object>> getStackVariables() {
        return stackVariables;
    }

    public String toString(){
        String s = "";
        if(classList != null)
            s+= classList.toString() +"\n";
        if(funcList != null)
            s+= funcList.toString()+"\n";


        return "Prog:\n "+s+"\n";
    }

    public String dotString(){
        String s = "graph program {\n";
        s+= getUid() + " [label=Prog]\n";
        if(classList != null){
            s+= getUid()+"--" +classList.getUid()+"\n";
            s+= classList.dotString();
        }
        if(funcList != null) {
            s+= getUid()+"--" +funcList.getUid()+"\n";
            s+= funcList.dotString();
        }


        s+="}";

        return s+"\n";
    }

    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns,ScopeVisitor v){
        if (classList != null) classList.tryInterpret(stackVariables, funcList.getList(), hashDatas, stackReturns, v);
        if (funcList != null) funcList.tryInterpret(stackVariables, funcList.getList() , hashDatas, stackReturns, v);
        return 0;
    }

    @Override
    public void accept(Visitor v) {
        //todo: implement
    }

}
