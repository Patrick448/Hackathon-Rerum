package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */ 
import java.util.HashMap; 
import java.util.Stack;

import java.util.List;

import visitors.ScopeVisitor;
import visitors.Visitor;

public class Char extends Expr {
      
      private String l;
     
      public  Char(int l, int c, String v){
           super(l,c);
           this.l = v.substring(1,v.length()-1);
      }
      
      public String getValue(){ return l;}
      
      //@Override
      public String toString(){
         return   "" + l ; 
      }

      public String dotString(){
            String s = getUid() + " [label=\"Char<"+l+">\"]\n";
            
            return s;
        }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
            return l;
      }

      @Override
      public void accept(Visitor v) {v.visit(this);}
}
