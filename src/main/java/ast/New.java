package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

public class New extends Expr {
    private Type type;
    private Expr e;

    public New(int l, int c, Type type, Expr e) {
        super(l, c);
        this.type = type;
        this.e = e;
    }

    public Expr getExpr() {
        return e;
    }

    public Type getType() {
        return type;
    }
    
    @Override
    public Object interpret(Stack<HashMap<String, Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v) {

        HashMap<String, Object> attributes = new HashMap<String, Object>();
        if(type.getName().equals("Int") || type.getName().equals("Float") || type.getName().equals("Char") || type.getName().equals("Bool"))
        {
            if(e==null){
                Integer instance = 0;
                return instance;
            }
            else{
                int i=0;
                List<Object> instance_list= new ArrayList<Object>();
                while(i < (Integer)e.tryInterpret(variables, functions, datas, returns, v))
                {
                    instance_list.add(null);
                    i++;
                }
                return instance_list;
            }
        }
        else{
            for(Decl d : datas.get(type.getName()).getDeclList().getList()){
                attributes.put(d.getId().getName(), null);
            }

            if(e==null){
                DataInstance instance = new DataInstance(type, attributes);
                return instance;
            }
            else{
                int i=0;
                List<DataInstance> instance_list= new ArrayList<DataInstance>();
                while(i < (Integer)e.tryInterpret(variables, functions, datas, returns, v))
                {
                    instance_list.add(null);
                    i++;
                }
                return instance_list;
            }
        }
    }

    @Override
    public String dotString() {
        String s = getUid() + " [label= \"" + this.getClass().getSimpleName() + "\"]\n";
        s += getUid() + "--" + type.getUid() + "\n";
        s += type.dotString();

        if (e != null) {
            s += getUid() + "--" + e.getUid() + "\n";
            s += e.dotString();
        }

        return s;
    }

    public String toString() {
        String s = "new " + type.toString();
        if(e != null) {
            s+= " [" + e.toString() + ']';
        }
        return s;
    }

    @Override
    public void accept(Visitor v) {v.visit(this);}
}
