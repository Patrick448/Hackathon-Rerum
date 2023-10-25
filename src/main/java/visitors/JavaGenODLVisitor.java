package visitors;

import odlAst.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JavaGenODLVisitor extends ODLVisitor{

    private Stack<ST> codeStack = new Stack<>();
    private STGroup groupTemplate;
    private String generatedCode;


    public JavaGenODLVisitor(){
        groupTemplate = new STGroupFile("../src/main/java/template/odl.stg");
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    @Override
    public void visit(ODLProg p) {
        ST template = groupTemplate.getInstanceOf("prog");

        if(p.getClassList()!=null)
        {
            p.getClassList().accept(this);
            template.add("classList", codeStack.pop());
        }

        this.generatedCode = template.render();
       // this.generatedCode = "ssssss";
    }

    @Override
    public void visit(ClassAst a) {
        ST template = groupTemplate.getInstanceOf("class");
        a.getId().accept(this);
        if(a.getDeclList()!=null)
        {
            a.getDeclList().accept(this);
            template.add("declist", codeStack.pop());
        }
        template.add("name", codeStack.pop());

        codeStack.push(template);

    }

    @Override
    public void visit(ClassList a) {
        ST template = groupTemplate.getInstanceOf("classList");

        for(ClassAst c : a.getList()){
            c.accept(this);
            template.add("classes", codeStack.pop());
        }

        codeStack.push(template);
    }

    @Override
    public void visit(DeclList a) {
        ST template = groupTemplate.getInstanceOf("declist");
        List<ST> decls = new ArrayList<ST>();

        for(Decl d:a.getList()){
            d.accept(this);
            decls.add(codeStack.pop());
        }

        template.add("decls", decls);
        codeStack.push(template);
    }

    @Override
    public void visit(Decl a) {
        ST template = groupTemplate.getInstanceOf("decl");
        a.getId().accept(this);
        a.getType().accept(this);
        template.add("type", codeStack.pop());
        template.add("name", codeStack.pop());
        codeStack.push(template);
    }


    @Override
    public void visit(Type t) {
        ST template = groupTemplate.getInstanceOf("raw_text");
        template.add("text", t.getName());
        codeStack.push(template);

    }

    @Override
    public void visit(ID i) {
        ST template = groupTemplate.getInstanceOf("raw_text");
        template.add("text", i.getName());
        codeStack.push(template);
    }
}
