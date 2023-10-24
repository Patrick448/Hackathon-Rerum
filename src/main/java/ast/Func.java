package ast;

/*
 * Esta classe representa um comando de .
 * Expr
 */

import visitors.Visitor;
import visitors.ScopeVisitor;

import java.util.HashMap;
import java.util.Stack;
import java.util.List;

public class Func extends Node{

      private ID id;
      private ParamsList params;
      private TypeList returns;
      private CmdList cmdList;
      private HashMap<String, Object> valuesParams;

      public Func(int l, int c, ID id, ParamsList params, TypeList returns, CmdList cmdList) {
            super(l, c);
            this.id = id;
            this.params = params;
            this.returns = returns;
            this.cmdList = cmdList;
            this.valuesParams = new HashMap<String, Object>();
        }

      public ParamsList getParams() {
          return params;
      }

      public TypeList getReturns() {
          return returns;
      }

      public ID getId() {
          return id;
      }

      public CmdList getCmdList() {
          return cmdList;
      }
      
      public HashMap<String, Object> getValuesParams() {
          return valuesParams;
      }

      @Override
      public Object interpret(Stack<HashMap<String,Object>> variables, List<Func> functions, HashMap<String, Data> datas, Stack<List<Object>> returns, ScopeVisitor v){
            Integer count = 0;
		    String s_p = "(";
		    if(params!=null)
		    {
	    		for(Param p : params.getParamsList())
		    	{
			    	String p_type = p.getType().getFullName();
				    if(count>0)
				    {
					    s_p = s_p + "," + p_type;
					}
                    else
                    {
				        s_p = s_p + p_type;
                    }
				    count = count+1;
			    }
			}
		    s_p = s_p + ")";

            int scopeFunc_before = v.getScopeFunc();
            int level_before = v.getLevel();
            v.setLevel(0);
            v.setScopeFuncByName(id.getName()+s_p);
            HashMap<String, Object> valuesParamsUnique = new HashMap<String, Object>(valuesParams);
            variables.push(valuesParamsUnique);
            Object o = 0;
            if(cmdList != null) 
            {
                o = cmdList.tryInterpret(variables, functions, datas, returns, v);
            };
            variables.pop();
            valuesParams.clear();
            v.setLevel(level_before);
            v.setScopeFunc(scopeFunc_before);
            return o;
      }

      public String dotString(){
            String s = getUid() + " [label=\""+ this.getClass().getSimpleName()+"\"]\n";
            
            s+= getUid() +"--"+id.getUid()+"\n";
            s+=id.dotString();

            if(cmdList != null){
               s+= getUid() +"--"+cmdList.getUid()+"\n"; 
               s+= cmdList.dotString();  
            }

            if(params != null){
               s+= getUid() +"--"+params.getUid()+"\n"; 
               s+= params.dotString();  
            }

            if(returns != null){
               s+= getUid() +"--"+returns.getUid()+"\n"; 
               s+= returns.dotString();  
            }

            
            return s;
        }

      public String toString() {
            String paramsStr = params != null ? params.toString() : "";
            String returnsStr = returns != null ? returns.toString() : "";
            String cmdListStr = cmdList != null ? cmdList.toString() : "";

            

          return "Func " + id.toString() + "(" + paramsStr + ") ret (" +returnsStr+ ") {" + cmdListStr + "}";
      }

    @Override
      public void accept(Visitor v) { v.visit(this);}

}
