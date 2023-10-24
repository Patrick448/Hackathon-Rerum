package ast;

import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class Null extends Expr {
      
      public  Null(int l, int c){
           super(l,c);
      }
      
      public Object getValue(){ return null;}
      
      //@Override
      public String toString(){
         return   "" + null ; 
      }

      public String dotString(){
            String s = getUid() + " [label=\"null\"]\n";
            
            return s;
        }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas,  Stack<List<Object>> returns, ScopeVisitor v)
      {
        return 0;
      }

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'accept'");
	}
}