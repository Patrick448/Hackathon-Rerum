package ast;

/*
 * Esta classe representa um comando de .
 * 
 */
import java.util.HashMap;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.List;

public class Data extends Node {

      private Type id;
      private DeclList declList;

      public Data(int l, int c, Type id, DeclList declList) {
            super(l, c);
            this.id = id;
            this.declList = declList;
      }

      public Type getId() {
          return id;
      }

      public DeclList getDeclList() {
          return declList;
      }

      @Override
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
            return 0;
      }

      public String dotString(){
            String s = getUid() + " [label= \""+this.getClass().getSimpleName()+"\"]\n";
            s+= getUid() +"--"+id.getUid()+"\n";
            s+=id.dotString();

            if(declList != null){
                  s+= getUid() +"--"+declList.getUid()+"\n";
                  s+=declList.dotString();
            }

   
            return s;
        }

      public String toString() {
            String declListString = declList != null ? declList.toString() : "";

          return "Data: {" + id + " {" + declListString + "}}";
      }

      @Override
      public void accept(Visitor v) { v.visit(this);}

}
