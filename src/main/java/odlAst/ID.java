package odlAst;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ID extends Node{
      
      private String l;
     
      public ID(int l, int c, String name){
          super(l,c);
          this.l = name;
      }
      
      public String getName(){ return l;}
      
      //@Override
      public String toString(){
         return   l; 
      }

            
      public String dotString(){
        String s = getUid() + " [label=\"ID<"+l+">\"]\n";
        
        return s;
    }

  /*  public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
      return variables.peek().get(l);
    }*/

    @Override
    public void accept(ODLVisitor v) {v.visit(this);}
}
