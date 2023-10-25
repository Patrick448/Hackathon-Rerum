package odlAst;

import visitors.ScopeVisitor;
import visitors.ODLVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Type extends Node {
      
      private String l;
      private int dim;

      public Type(int l, int c, String name){
           super(l,c);
           this.l = name;
      }
      
      public String getName(){ 
        return l;
      }

      public String getFullName(){
        if(dim > 0){
          return this.toString();
        }
        return l;
      }

      public int getDim() {
          return dim;
      }

      //@Override
      public String toString(){
        String s = l;
        if(dim > 0)
        {
          int i = dim;
          while(i>0)
          {
            s+="[]";
            i = i-1;
          }
        }
        return s; 
      }

      public void addDimension(){
        this.dim++;
      }

        @Override
        public String dotString(){
            String dimString = "";
            if(dim > 0)
            {
              for(int i = 0; i < dim; i++)
              {
                dimString += "[]";
              }
            }

            String s = getUid() + " [label=\""+ this.getClass().getSimpleName()+"<"+ getName() +">"+ dimString+"\"]\n";
            return s;
        }
      
      /*  public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
          return variables.peek().get(l);
      }*/

		@Override
		public void accept(ODLVisitor v) { v.visit(this);}

}
