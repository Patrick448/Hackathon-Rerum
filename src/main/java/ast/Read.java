package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import visitors.Visitor;
import visitors.ScopeVisitor;
import java.util.List;

public class Read extends Node {
      
      private LValue vl; 
      
      public Read(int l, int c, LValue v){
           super(l,c);
           this.vl  = v;
      }
      
      public LValue getLValue(){ return vl;}
      
      //@Override
      public String toString(){
         return   "read " + vl.toString(); 
      }

      public String dotString(){
        String s = getUid() + " [label=\""+this.getClass().getSimpleName()+"\"]\n";
     
        if(vl != null){
           s+= getUid() +"--"+vl.getUid()+"\n"; 
           s+= vl.dotString();  
        }
        
        return s;
    }
    public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
        //System.out.println("---");
        Scanner keyboard = new Scanner(System.in);
        String read = keyboard.nextLine();
        //keyboard.close();
        vl.accept(v);
        String type = v.getStack().pop();
        //System.out.println(v.getCurrentScope());
        Object readObject = null;
       

        if(type.equals("Bool")){
            try{
                readObject = (String)read;
                if(!readObject.equals("true")&&!readObject.equals("false"))
                {
                    System.out.println("Error at line " + vl.getLine() + ":" + vl.getCol() + ": Unable to convert read value to "+ type);
                    System.exit(0);
                }
            }catch(Exception e){
                System.out.println("Error at line " + vl.getLine() + ":" + vl.getCol() + ": Unable to convert read value to "+ type);
                System.exit(0);
            }

        }else if(type.equals("Int")){
            try{
                readObject = Integer.parseInt(read);
            }catch(Exception e){
                System.out.println("Error at line " + vl.getLine() + ":" + vl.getCol() + ": Unable to convert read value to "+ type);
                System.exit(0);
            }
        }else if(type.equals("Float")){
            try{
                readObject = Float.parseFloat(read);
            }catch(Exception e){
                System.out.println("Error at line " + vl.getLine() + ":" + vl.getCol() + ": Unable to convert read value to "+ type);
                System.exit(0);
            }
        }else{
            readObject = (String)read.substring(0,1);
        }
        vl.attribute(readObject, variables, v);
        return read;
    }

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
