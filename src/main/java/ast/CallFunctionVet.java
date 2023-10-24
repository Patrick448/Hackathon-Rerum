package ast;

import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;


public class CallFunctionVet extends Expr {
      
      private ID id;
      private ExprList le;
      private Expr e; 
      
      public CallFunctionVet(int l, int c, ID i, ExprList le, Expr e){
           super(l, c);
           this.le = le;
           this.e  = e;
           this.id = i;
      }
      
      public Expr getLExp(){ return e;} 
      public ExprList getExpList(){   return le; }
      public ID getId(){return id;}
      
      public String toString(){
        String s = id.toString() + "(";
        if(le!=null)
        {
          s+= le.toString();
        }
        s+= ")";
        if(e!=null)
        {
          s+= "[" + e.toString() + "]";
        }
        return s;
      }

     public String dotString(){
        String s = getUid() + " [label= \"CallFunction\"]\n";
        s+= getUid() +"--"+id.getUid()+"\n";
        s+=id.dotString();
        if(le!=null)
        {
            s+= getUid() +"--"+le.getUid()+"\n";
            s+=le.dotString();
        }
        if(e!=null)
        {
            s+= getUid() +"--"+e.getUid()+"\n";
            s+=e.dotString();
        }
        
        return s;
    }

    private void put_params_value(Func f, Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v)
    {
        int i=0;
        if(f.getParams() != null)
        {
            for(Param n : f.getParams().getParamsList())
            {
                f.getValuesParams().put(n.getId().getName(),le.getList().get(i).tryInterpret(variables, functions, datas, returns, v));
                i++;
            }
        }
    }
      
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        for(Func f : functions)
        {
            if(f.getId().getName().equals(id.getName()))
            {
                if(f.getParams()!=null)
                {
                    Boolean t_parameters_equal = true;
                    int count = 0;
                    if(f.getParams().getParamsList().size()==le.getList().size())
                    {
                        while(count<f.getParams().getParamsList().size())
                        {
                            le.getList().get(count).accept(v);
                            String e_type = v.getStack().pop();
                            if(!e_type.equals(f.getParams().getParamsList().get(count).getType().getFullName()))
                            {
                                t_parameters_equal = false;
                                break;
                            }
                            count = count+1;
                        }
                        if(t_parameters_equal)
                        {
                            put_params_value(f, variables, functions, datas, returns, v);
                            f.tryInterpret(variables, functions, datas, returns, v);
                            int max = returns.peek().size();
                            if((Integer)e.tryInterpret(variables, functions, datas, returns, v)<max)
                            {
                                return returns.peek().get((Integer)e.tryInterpret(variables, functions, datas, returns, v));
                            }
                        }
                    }
                }
                else if(e==null)
                {
                    f.tryInterpret(variables, functions, datas, returns, v);
                    int max = returns.peek().size();
                    if((Integer)e.tryInterpret(variables, functions, datas, returns, v)<max)
                    {
                        return returns.peek().get((Integer)e.tryInterpret(variables, functions, datas, returns, v));
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public void accept(Visitor v) {v.visit(this);}
}
