package odlAst;

import visitors.ODLVisitable;
import visitors.ScopeVisitor;
import visitors.Visitable;
import visitors.ODLVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public abstract class Node implements ODLVisitable {
      
      private int line,col, uid;
      
      public Node(int l, int c){
           line = l;
           col = c;
           this.uid = IdGenerator.getNextId();
      }

      public String dotString(){
            String s = getUid() + " [label=\""+this.getClass().getSimpleName()+"\"]\n";
            return s;
        }

        
      
      public int getLine(){ return line;}
      public int getCol(){ return col;}  
      public int getUid(){ return uid;}

     /* public Object tryInterpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
            try{
                  return this.interpret(variables, functions, datas, returns, v);
            }
            catch(Exception e){
                  System.out.println("Error at line " + this.getLine() + ":" + this.getCol());
                  e.printStackTrace(System.out);
                  System.exit(0);

                  return null;
            }
      }*/


      @Override
      public abstract void accept(ODLVisitor v);
      

}
