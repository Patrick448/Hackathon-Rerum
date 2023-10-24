package ast;

/*
 * Esta classe representa uma expressão de Multiplicação.
 * Expr * Expr
 */
import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class And extends BinOP {
      public And(int lin, int col, Expr l, Expr r){
           super(lin,col,l,r);
      }
      
      //@Override
      public String toString(){
         String s = getLeft().toString();
         if(getLeft() instanceof And){
            s = "(" + s + ")";
         }
         return   s + " && " + getRight().toString();
      }

      public String dotString(){
         String s = getUid() + " [label= \"&&\"]\n";
         s+= getUid() +"--"+getLeft().getUid()+"\n";
         s+=getLeft().dotString();
         s+= getUid() +"--"+getRight().getUid()+"\n";
         s+=getRight().dotString();
         
         return s;
      }

      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns,ScopeVisitor v){
         if(getLeft().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Boolean") && getRight().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Boolean"))
         {
            return (boolean)getLeft().tryInterpret(variables, functions, datas, returns, v) && (boolean)getRight().tryInterpret(variables, functions, datas, returns, v);
         }
         return false;
      }

      @Override
      public void accept(Visitor v) {
         v.visit(this);
      }
}
