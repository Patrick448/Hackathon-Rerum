package application;

import com.javax0.sourcebuddy.Compiler;
import com.javax0.sourcebuddy.Fluent;
import odlAst.Node;
import org.antlr.v4.runtime.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import orm.Entity;
import visitors.JavaGenODLVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {
    public static void writeToFile(String filename, String content) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(content);
            myWriter.close();

            System.out.println("Successfully wrote " + filename);

        } catch (IOException e) {
            System.out.println("An error occurred while trying to write to the file " + filename + ": \n" + e.getMessage());
            e.printStackTrace();
        }
    }


    public static odlParser parseFile(String filename) throws IOException {
        // Create a ANTLR CharStream from a file
        CharStream stream = CharStreams.fromFileName(filename);
        // create a lexer that feeds off of stream
        odlLexer lex = new odlLexer(stream);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lex);
        // create a parser that feeds off the tokens buffer
        odlParser parser = new odlParser(tokens);
        // tell ANTLR to does not automatically build an AST
        parser.setBuildParseTree(false);

        //System.out.println(parser.getNumberOfSyntaxErrors());
        //todo: ver como fazer checagem de erros sintáticos e léxicos.
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.out.println("The program contains syntax errors. Aborting.");
            System.exit(0);
        }

        return parser;
    }

    public static Node getAST(odlParser lp) {
        Node ast = null;
        try {
            ast = lp.odlprog().ast;
            if (ast == null) {
                System.out.println("Error generating abstract syntax tree.");
                System.exit(0);
            }

        } catch (Exception e) {
            System.exit(0);
        }

        return ast;
    }


    public static String readFile(String filename) {
        String content = "";
        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                content += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return content;
    }


    public static Class<?> loadClass(String fileName) throws Compiler.CompileException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String source = readFile(fileName);
        Class<?> loadedClass = com.javax0.sourcebuddy.Compiler.compile(source);
        return loadedClass;
    }

    public static void runMethod(Class<?> c, String methodName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object obj = c.getConstructor().newInstance();
        Method thisMethod = c.getDeclaredMethod(methodName);
        thisMethod.invoke(obj);
    }

    public static Fluent.Compiled compileClasses(List<String> classes, String dir) throws IOException, ClassNotFoundException, Compiler.CompileException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Fluent.CanCompile canCompile = Compiler.java()
                .from(Paths.get(dir + "/" + classes.get(0) + ".java"));

        for (int i = 1; i < classes.size(); i++) {
            canCompile = canCompile.from(Paths.get(dir + "/" + classes.get(i) + ".java"));
        }

        Fluent.Compiled compiled = canCompile.compile();

        compiled.saveTo(Paths.get("./target/generated_classes"));


        return compiled;

    }


    public static void main(String args[]) throws Exception {
        SpringApplication.run(Application.class, args);

        odlParser parser = parseFile("teste_class.odl");
        Node ast = getAST(parser);
        writeToFile("ast.dot", ast.dotString());

        JavaGenODLVisitor javaGenODLVisitor = new JavaGenODLVisitor();
        ast.accept(javaGenODLVisitor);
        String outputDir = "output";

        List<String> classSources = javaGenODLVisitor.getClassSources();
        List<String> classNames = javaGenODLVisitor.getClassNames();

        for (int i = 0; i < classSources.size(); i++) {
            writeToFile(outputDir + "/" + classNames.get(i) + ".java", classSources.get(i));
        }

        Fluent.Compiled compiled = compileClasses(classNames, "output");

        final var loaded = compiled.load();
        Class<?> objClass = loaded.get("generatedodl.C");
        Entity obj = (Entity) loaded.newInstance("generatedodl.C");
        Method thisMethod = objClass.getDeclaredMethod("print");
        obj.setAttr("i", 13);
        System.out.println((obj.getAttr("i")));
        thisMethod.invoke(obj);

        }


    }