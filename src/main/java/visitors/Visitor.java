package visitors;

import ast.*;

public abstract  class  Visitor {

    // public abstract void visit(Node p);


     public abstract void visit(Prog p);

     public abstract void visit(FuncList f);

     public abstract void visit(Func f);



     public abstract void visit(TypeList t);

     public abstract void visit(ParamsList p);
     public abstract void visit(Param p);

     public abstract void visit(CmdList c);

     public abstract void visit(Print p);

     public abstract void visit(Add a);

     public abstract void visit(Sub a);

     public abstract void visit(Div a);

     public abstract void visit(Mul a);

     public abstract void visit(Rest a);

     public abstract void visit(And a);

     public abstract void visit(GreaterThan a);

     public abstract void visit(LessThan a);

     public abstract void visit(Diff a);

     public abstract void visit(SubUni a);

     public abstract void visit(Neg a);

     public abstract void visit(Eq a);

     public abstract void visit(Int a);

     public abstract void visit(Char a);

     public abstract void visit(Bool a);

     public abstract void visit(FloatAst a);

     public abstract void visit(Iterate a);

     public abstract void visit(If a);

     public abstract void visit(Data a);

     public abstract void visit(DataList a);
     public abstract void visit(DeclList a);

     public abstract void visit(Decl a);


     public abstract void visit(Type t);

     public abstract void visit(Attr a);

     public abstract void visit(LValue l);

     public abstract void visit(New n);
     
     public abstract void visit(ID i);

     public abstract void visit(Read i);

     public abstract void visit(ReturnCMD r);

     public abstract void visit(CallFunction c);

     public abstract void visit(CallFunctionVet c);


     /*public abstract void visit(Add e);
          
     public abstract void visit(Var e);
     public abstract void visit(NInt e);
     public abstract void visit(Call e);
     
     public abstract void visit(Attr e);
     public abstract void visit(Print e);
     public abstract void visit(Block e);
     public abstract void visit(Func f);
     
     public abstract void visit(Param e);
     
     public abstract void visit(TyInt t);
     public abstract void visit(TyVoid t);*/
}
