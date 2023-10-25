import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Vocabulary;
import java.io.FileInputStream;

import odlAst.*;
import visitors.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;



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
				//ast.tryInterpret(null, null, null, null, scope);
				JavaGenODLVisitor javaGenODLVisitor = new JavaGenODLVisitor();
				ast.accept(javaGenODLVisitor);
				System.out.println(javaGenODLVisitor.getGeneratedCode());
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