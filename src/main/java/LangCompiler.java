import com.javax0.sourcebuddy.Compiler;
import com.javax0.sourcebuddy.Fluent;
import org.antlr.v4.runtime.*;

import java.io.*;

import odlAst.*;
import visitors.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Scanner;


public class LangCompiler {

	public static void writeToFile(String filename, String content) {
		try {
			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(content);
			myWriter.close();

			System.out.println("Successfully wrote " + filename );

		} catch (IOException e) {
			System.out.println("An error occurred while trying to write to the file " +filename+": \n" + e.getMessage());
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
		if(parser.getNumberOfSyntaxErrors()>0){
			System.out.println("The program contains syntax errors. Aborting.");
			System.exit(0);
		}

		return parser;
	}

	public static Node getAST(odlParser lp){
		Node ast = null;
		try
		{
			ast = lp.odlprog().ast;
			if(ast == null){
				System.out.println("Error generating abstract syntax tree.");
				System.exit(0);
			}

		}
		catch(Exception e){
			System.exit(0);
		}

		return ast;
	}

	/*public static ScopeVisitor semanticAnalysis(Node ast){
		ScopeVisitor scope = new ScopeVisitor();
		((Prog)ast).accept(scope);
		String analise = scope.getStack().pop();

		if(analise.equals("Error"))
		{
			System.out.println("The program contains semantic errors. Aborting.");
			System.exit(0);
		}
		return scope;
	}*/


	public static String readFile(String filename){
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

	public static void test(String[] classes, String dir) throws IOException, ClassNotFoundException, Compiler.CompileException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		String sourceFirstClass = """
         package com.sb.demo;
 
          public class FirstClass {
              public String a() {
                  return "x";
            }
          }""";


		String source = readFile("output/C.java");


		final var compiled = Compiler.java()
				.from(Paths.get("output/C.java"))
				.from(Paths.get("output/C2.java"))
				.compile();

		 compiled.saveTo(Paths.get("./target/generated_classes"));
		 compiled.stream().forEach(bc -> System.out.println(Compiler.getBinaryName(bc)));
		 final var loaded = compiled.load();
		 Class<?> objClass = loaded.get("generatedodl.C");
		 Object obj = loaded.newInstance("generatedodl.C");
		 //loaded.stream().forEach(klass -> System.out.println(klass.getSimpleName()));
		 final var compiler = loaded.reset();
		 final var sameCompiler = compiled.reset();
		Method thisMethod = objClass.getDeclaredMethod("print");
		thisMethod.invoke(obj);

		/*String sourceFirstClass = """
          package com.sb.demo;
 
          public class FirstClass {
              public String a() {
                  return "x";
            }
          }""";
		 final var compiled = Compiler.java()
		         .from("com.sb.demo.FirstClass", sourceFirstClass)
		         .from(Paths.get("src/test/java"))
		         .compile();
		 compiled.saveTo(Paths.get("./target/generated_classes"));
		 compiled.stream().forEach(bc -> System.out.println(Compiler.getBinaryName(bc)));
		 final var loaded = compiled.load();
		 Class<?> firstClassClass = loaded.get("com.sb.demo.FirstClass");
		 Object firstClassInstance = loaded.newInstance("com.sb.demo.FirstClass");
		 loaded.stream().forEach(klass -> System.out.println(klass.getSimpleName()));
		 final var compiler = loaded.reset();
		 final var sameCompiler = compiled.reset();*/

	}


	public static void main(String args[]) throws Exception {

		if(args.length >=2){
			if(args.length >=3 && args[2].equals("-v")){
				System.out.println("\nFile: " + args[0]);
			}
			
			odlParser  parser = parseFile(args[0]);
			Node ast = getAST(parser);
			writeToFile("ast.dot", ast.dotString());
			//ScopeVisitor scope = semanticAnalysis(ast);

			if(args[1].equals("-i")){
				//ast.tryInterpret(null, null, null, null, scope);

			}
			else if(args[1].equals("-w")){
				test(null, null);

				//Class<?> c = loadClass("output/C2.java");
				//runMethod(c, "print");
				//ast.tryInterpret(null, null, null, null, scope);


				/*JavaGenODLVisitor javaGenODLVisitor = new JavaGenODLVisitor();
				ast.accept(javaGenODLVisitor);
				String javaCode = javaGenODLVisitor.getGeneratedCode();
				String fileName = "output";
				String outputPath = "output/" + fileName + ".java";
				writeToFile(outputPath, javaCode);*/
			}
			else if(args[1].equals("-s")){
				String[] splitName = args[0].split("/");
				String fileName = splitName[splitName.length-1];
				fileName = fileName.split("\\.")[0];
				//JavaGenVisitor javaGenVisitor = new JavaGenVisitor(scope, fileName);
				//ast.accept(javaGenVisitor);

				String outputPath = "JavaCodes/" + fileName + ".java";
				//writeToFile(outputPath, javaGenVisitor.getGeneratedCode());
			}
			else if(args[1].equals("-j")){
				/*String[] splitName = args[0].split("/");
				String fileName = splitName[splitName.length-1];
				fileName = fileName.split("\\.")[0];
				JasminGenVisitor jasminGenVisitor = new JasminGenVisitor(scope, fileName);
				ast.accept(jasminGenVisitor);

				String outputPath = "JasminCodes/" + fileName + ".j";
				writeToFile(outputPath, jasminGenVisitor.getGeneratedCode());*/
			}
		}else{
			System.out.println("O sistema espera 2 argumetos obrigatorios, sendo:");
			System.out.println("O 1º: Arquivo a ser executado");
			System.out.println("O 2º: Qual o metodo de compilação ou interpretação quer usar");
			System.out.println("\t -i : intepretador");
			System.out.println("\t -s : codigo em Java");
			System.out.println("\t -j : cogido em Jasmin");
			System.exit(0);
		}
	}


}