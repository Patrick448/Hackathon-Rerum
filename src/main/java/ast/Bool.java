package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */ 
import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class Bool extends Expr {
      
      private boolean l;
     
      public  Bool(int l, int c, boolean v){
           super(l,c);
           this.l = v;
      }
      
      public boolean getValue(){ return l;}
      
      //@Override
      public String toString(){
         return   "" + l ; 
      }

      public String dotString(){
            String s = getUid() + " [label=\"Bool<"+l+">\"]\n";
            
            return s;
        }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
            return l;
      }

      @Override
      public void accept(Visitor v) {
         v.visit(this);
      }
}
