package ast;

/*
 * Esta classe representa um comando de atribuição.
 * ID = Expr
 */
 
import java.util.HashMap; 
import java.util.Stack;

import visitors.ScopeVisitor;
import visitors.Visitor;

import java.util.List;

public class Attr extends Node {
      
      private LValue lValue;
      private Expr e; 
      
      public Attr(int l, int c, LValue lValue, Expr e){
           super(l, c);
           this.lValue = lValue;
           this.e  = e;
      }
      
      public LValue getLValue(){ return lValue;} 
      public Expr getExp(){   return e; }
      
      public String toString(){
          return lValue.toString() + " = " + e.toString();
      }

     public String dotString(){
        String s = getUid() + " [label= \"=\"]\n";
        s+= getUid() +"--"+lValue.getUid()+"\n";
        s+=lValue.dotString();
        s+= getUid() +"--"+e.getUid()+"\n";
        s+=e.dotString();
        
        return s;
    }

    public List findDataInstace(LValue aux, Boolean vet, Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v)
    {
          Stack<LValue> stack_aux = new Stack<LValue>();
          stack_aux.push(aux);
          while(aux.getLValue()!=null)
          {
               aux = aux.getLValue();
               stack_aux.push(aux);
          }
          List lt =  (List)variables.peek().get(stack_aux.peek().getID().getName());
          stack_aux.pop();
          int i=0;
          while(i<stack_aux.size())
          {
               if((stack_aux.size() != 1 || vet))
               {
                    lt = (List)lt.get((Integer)stack_aux.peek().getExpr().tryInterpret(variables, functions, datas, returns, v));
               }
               stack_aux.pop();
          }
          return lt;
    }

     public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
          Object value = e.interpret(variables, functions, datas, returns, v); 
          lValue.attribute(value, variables, v);
          return 0;
     }

     @Override
     public void accept(Visitor v) {v.visit(this);}   
}