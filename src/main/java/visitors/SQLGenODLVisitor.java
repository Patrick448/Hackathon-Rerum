package visitors;

import odlAst.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SQLGenODLVisitor extends ODLVisitor{

    private Stack<ST> codeStack = new Stack<>();
    private STGroup groupTemplate;
    private String generatedCode;

    private List<String> classSources = new ArrayList<>();
    private List<String> classNames= new ArrayList<>();


    public SQLGenODLVisitor(){
        groupTemplate = new STGroupFile("template/sql.stg");
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public String popStack(){
        return codeStack.pop().render();
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public List<String> getClassSources() {
        return classSources;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public Stack<ST> getCodeStack() {
        return codeStack;
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

        if(a.getExtended() != null){
            a.getExtended().accept(this);
            template.add("extends", codeStack.pop());
        }

        template.add("name", codeStack.pop());

        classSources.add(template.render());
        classNames.add(a.getId().getName());
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
        ST template;

        if(t.getName().equals("oid")){
            template = groupTemplate.getInstanceOf("oid");
        }else if(t.getName().equals("byte")) {
            template = groupTemplate.getInstanceOf("byte");
        }else if(t.getName().equals("char")) {
            template = groupTemplate.getInstanceOf("char");
        }else if(t.getName().equals("short")) {
            template = groupTemplate.getInstanceOf("short");
        }else if(t.getName().equals("int")) {
            template = groupTemplate.getInstanceOf("int");
        }else if(t.getName().equals("long")) {
            template = groupTemplate.getInstanceOf("long");
        }else if(t.getName().equals("double")) {
            template = groupTemplate.getInstanceOf("double");
        }
        else {
            template = new ST(t.getName());
        }
        codeStack.push(template);

    }

    @Override
    public void visit(ID i) {
        ST template = groupTemplate.getInstanceOf("raw_text");
        template.add("value", i.getName());
        codeStack.push(template);
    }
}
