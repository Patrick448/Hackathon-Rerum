package ast;

import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;


public class CallFunction extends Expr {
      
      private ID id;
      private ExprList e;
      private LValueList l; 
      
      public CallFunction(int l, int c, ID i, ExprList e, LValueList v){
           super(l, c);
           this.l = v;
           this.e  = e;
           this.id = i;
      }
      
      public LValueList getLValueList(){ return l;} 
      public ExprList getExpList(){   return e; }
      public ID getId(){return id;}
      
      public String toString(){
          String s = id.toString() + "(";
          if(e!=null)
          {
            s+= e.toString();
          }
          s+= ")";
          if(l!=null)
          {
            s+= "<" + l.toString() + ">";
          }
          return s;
      }

     public String dotString(){
        String s = getUid() + " [label= \"CallFunction\"]\n";
        s+= getUid() +"--"+id.getUid()+"\n";
        s+=id.dotString();
        if(e!=null)
        {
            s+= getUid() +"--"+e.getUid()+"\n";
            s+=e.dotString();
        }
        if(l!=null)
        {
            s+= getUid() +"--"+l.getUid()+"\n";
            s+=l.dotString();
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
                 f.getValuesParams().put(n.getId().getName(),e.getList().get(i).tryInterpret(variables, functions, datas, returns, v));
                 i++;
            }
        }
    }

    private void put_returns_value(Stack<HashMap<String,Object>> variables, Stack<List<Object>> returns, ScopeVisitor v)
    {
        int i=0;
        for(LValue lv : l.getList())
        {
            lv.attribute(returns.peek().get(i), variables, v);
            i+=1;
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
                    if(f.getParams().getParamsList().size()==e.getList().size())
                    {
                        while(count<f.getParams().getParamsList().size())
                        {
                            e.getList().get(count).accept(v);
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
                            if(l!=null)
                            {
                                put_returns_value(variables, returns, v);
                                if(!returns.isEmpty())
                                {
                                    returns.pop();
                                }
                            }
                            break;
                        }
                    }
                }
                else if(e==null)
                {
                    f.tryInterpret(variables, functions, datas, returns, v);
                    if(l!=null)
                    {
                        put_returns_value(variables, returns, v);
                        if(!returns.isEmpty())
                        {
                            returns.pop();
                        }
                    }
                    break;
                }
            }
        }
        return 0;
    }

    @Override
    public void accept(Visitor v) {v.visit(this);}
}
