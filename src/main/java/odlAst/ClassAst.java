package odlAst;

/*
 * Esta classe representa um comando de .
 *
 */

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ClassAst extends Node {

    private Type id;
    private DeclList declList;
    private Type extended;

    public ClassAst(int l, int c, Type id, Type extended, DeclList declList) {
        super(l, c);
        this.id = id;
        this.declList = declList;
        if(extended != null) {
            this.extended = extended;
        }
    }

    public Type getId() {
        return id;
    }

    public DeclList getDeclList() {
        return declList;
    }

    /*@Override
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        return 0;
    }*/

    public Type getExtended() {
        return extended;
    }

    public void setExtended(Type extended) {
        this.extended = extended;
    }

    public String dotString(){
        String s = getUid() + " [label= \""+this.getClass().getSimpleName()+"\"]\n";
        s+= getUid() +"--"+id.getUid()+"\n";
        s+=id.dotString();

        if(declList != null){
            s+= getUid() +"--"+declList.getUid()+"\n";
            s+=declList.dotString();
        }

        if(extended != null){
            s+= getUid() +"--"+extended.getUid()+"\n";
            s+=extended.dotString();
        }


        return s;
    }



    @Override
    public void accept(ODLVisitor v) {
        v.visit(this);
    }

    public String toString() {
        String declListString = declList != null ? declList.toString() : "";

        return "Data: {" + id + " {" + declListString + "}}";
    }



}
