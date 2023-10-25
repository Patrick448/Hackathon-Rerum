package visitors;

import odlAst.*;

public abstract  class ODLVisitor {

    // public abstract void visit(Node p);


    public abstract void visit(ODLProg p);

    public abstract void visit(ClassAst a);

    public abstract void visit(ClassList a);

    public abstract void visit(DeclList a);

    public abstract void visit(Decl a);

    public abstract void visit(Type t);


    public abstract void visit(ID i);

}
