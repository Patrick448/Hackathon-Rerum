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

public class Rest extends BinOP {
      public Rest(int lin, int col, Expr l, Expr r){
           super(lin,col,l,r);
      }
      
      //@Override
      public String toString(){
         String s = getLeft().toString();
         if(getLeft() instanceof Mul || getLeft() instanceof Add || getRight() instanceof Sub || getRight() instanceof Div || getRight() instanceof Rest){
            s += "(" + s + ")";
         }
         String ss = getRight().toString();
         if( getRight() instanceof Add || getRight() instanceof Sub ){
            ss = "(" + ss+ ")";
         }
         return   s + " % " + ss;
      }

      public String dotString(){
         String s = getUid() + " [label= \"*\"]\n";
         s+= getUid() +"--"+getLeft().getUid()+"\n";
         s+=getLeft().dotString();
         s+= getUid() +"--"+getRight().getUid()+"\n";
         s+=getRight().dotString();
         
         return s;
      }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
         if(getLeft().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Integer") && getRight().tryInterpret(variables, functions, datas, returns, v).getClass().getSimpleName().equals("Integer"))
         {
            return (Integer)getLeft().tryInterpret(variables, functions, datas, returns, v) % (Integer)getRight().tryInterpret(variables, functions, datas, returns, v);
         }
         return 0;
      }

	@Override
	public void accept(Visitor v) {v.visit(this);}
}
