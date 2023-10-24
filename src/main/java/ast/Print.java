package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.HashMap; 
import java.util.Stack;
import java.util.List;
 
public class Print extends Node{
      
      private Expr e; 
      
      public Print(int l, int c, Expr e){
           super(l,c);
           this.e  = e;
      }
      
      public Expr getExpr(){ return e;}
      
      //@Override
      public String toString(){
         return   "print " + e.toString(); 
      }

      public String dotString(){
        String s = getUid() + " [label=\""+this.getClass().getSimpleName()+"\"]\n";
     
        if(e != null){
           s+= getUid() +"--"+e.getUid()+"\n"; 
           s+= e.dotString();  
        }
        
        return s;
    }
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
      Object object = e.tryInterpret(variables, functions, datas, returns, v);
      if(object == null)
      {
         System.out.print("null");
         return 0;
      }
      String className = object.getClass().getSimpleName();
      
      if(className.equals("Integer"))
         {
            System.out.print("" + object);
         }
         else if(className.equals("Float"))
         {
            System.out.print("" + object);
         }
         else if(className.equals("Boolean"))
         {
            System.out.print("" + object);
         }
         else if(className.equals("String"))
         {
            if(object.equals("\\n"))
            {
               System.out.print("\n");
            }
            else if(object.equals("\\t"))
            {
               System.out.print("\t");
            }
            else if(object.equals("\\b"))
            {
               System.out.print("\b");
            }
            else if(object.equals("\\r"))
            {
               System.out.print("\r");
            }
            else if(object.equals("\\\\"))
            {
               System.out.print("\\");
            }
            else if(object.equals("\\'"))
            {
               System.out.print("'");
            }

            else
            {
               String s = (String)object;
               System.out.print("" + s);
            }
         }

         else if(className.equals("DataInstance")){
            System.out.print(object);
         }

         else if(className.equals("ArrayList")){
            System.out.print(object);
         }
         return 0;
      }

      @Override
      public void accept(Visitor v) { v.visit(this);}
}
