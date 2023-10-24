package ast;

/*
 * 
 * 
 */
 
import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class Iterate extends Node {
      
      private Expr condition;
      private Node cmd;
      
      public Iterate(int lin, int col, Expr condition, Node cmd){
           super(lin,col);
           this.condition = condition;
           this.cmd = cmd;
      }
      
      
      public String toString(){
         String condStr = condition.toString();
         String cmdStr =  cmd.toString();
         String s = "";

         s += "iterate (" + condStr +") " + cmdStr ;
         return  s; 
      }

      public String dotString(){
         String s = getUid() + " [label= \""+this.getClass().getSimpleName()+"\"]\n";
         s+= getUid() +"--"+condition.getUid()+"\n";
         s+=condition.dotString();

         s+= getUid() +"--"+cmd.getUid()+"\n";
         s+=cmd.dotString();

         return s;
      }

      public Expr getCondition(){
         return condition;
      }

      public Node getCmd(){
         return cmd;
      }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        int i = (Integer)condition.tryInterpret(variables, functions, datas, returns, v);
        while(i>0){
          	cmd.tryInterpret(variables, functions, datas, returns, v);
            i--;
        }
        return 0;
      }


      @Override
      public void accept(Visitor v) {v.visit(this);}
      
}
