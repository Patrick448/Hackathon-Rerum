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

public class FloatAst extends Expr {
      
      private float l;
     
      public  FloatAst(int l, int c, float v){
           super(l,c);
           this.l = v;
      }
      
      public float getValue(){ return l;}
      
      //@Override
      public String toString(){
         return   "" + l ; 
      }

      public String dotString(){
            String s = getUid() + " [label=\"Float<"+l+">\"]\n";
            
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
