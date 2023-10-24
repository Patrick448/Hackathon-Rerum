package ast;

/*
 * Esta classe representa uma expressão de soma.
 * Expr + Expr
 */
 
import java.util.HashMap; 
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class If extends Node {
      
      private Node teste;
      private Node thn;
      private Node els;
      
      public If(int lin, int col, Node teste, Node thn, Node els){
           super(lin,col);
           this.teste = teste;
           this.thn = thn;
           this.els = els;
      }
      
            
      public If(int lin, int col, Node teste, Node thn){
           super(lin,col);
           this.teste = teste;
           this.thn = thn;
           this.els = null;
      }
      
      public String toString(){
         String s = teste.toString();
         String sthn =  thn.toString();
         String sels =  els != null ? " : " + els.toString(): "" ;
         if(thn instanceof If){
             sthn = "(" + sthn + ")";
         }
         if(els != null && els instanceof If){
             sels = "(" + sels + ")";
         }
         s += " ? " + sthn + sels ;
         return  s; 
      }

      public String dotString(){
         String s = getUid() + " [label= \""+this.getClass().getSimpleName()+"\"]\n";
         s+= getUid() +"--"+teste.getUid()+"\n";
         s+=teste.dotString();

         s+= getUid() +"--"+thn.getUid()+"\n";
         s+=thn.dotString();

         if(els != null){
             s+= getUid() +"--"+els.getUid()+"\n";
             s+=els.dotString();
         }

         return s;
      }

      public Node getTeste(){
         return teste;
      }

      public Node getThn(){
         return thn;
      }

      public Node getEls(){
         return els;
      }
      
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        boolean n = (boolean)teste.tryInterpret(variables, functions, datas, returns, v);  
        if(n){
              return thn.tryInterpret(variables, functions, datas, returns, v);
        }
        else if(els !=null){
            return els.tryInterpret(variables, functions, datas, returns, v);
        }
        return n;
      }


    @Override
    public void accept(Visitor v) {v.visit(this);}
      
}
