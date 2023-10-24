package ast;

/*
 * Esta classe representa uma expressão de soma.
 * Expr + Expr
 */

import visitors.Visitor;
import java.util.HashMap; 
import java.util.Stack;

import visitors.ScopeVisitor;

import java.util.List;

public class Add extends BinOP {

      public Add(int lin, int col, Expr l, Expr r){
           super(lin,col,l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         if(getLeft() instanceof Add || getLeft() instanceof Sub ){
            s = "(" + s + ")";
         }
         return   s + " + " + getRight().toString();
      }

      public String dotString(){
         String s = getUid() + " [label= \"+\"]\n";
         s+= getUid() +"--"+getLeft().getUid()+"\n";
         s+=getLeft().dotString();
         s+= getUid() +"--"+getRight().getUid()+"\n";
         s+=getRight().dotString();
         
         return s;
     }
      
     public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
      if(getLeft().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Integer") && getRight().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Integer"))
         {
            return (Integer)getLeft().tryInterpret(variables, functions, datas, returns, v) + (Integer)getRight().tryInterpret(variables, functions, datas, returns, v);
         }
         else if(getLeft().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Float") && getRight().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Float"))
         {
            return (Float)getLeft().tryInterpret(variables, functions, datas, returns, v) + (Float)getRight().tryInterpret(variables, functions, datas, returns, v);
         }
         return 0;
      }

      @Override
      public void accept(Visitor v) { v.visit(this);}
      
}
