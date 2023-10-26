import com.javax0.sourcebuddy.Compiler;
import com.javax0.sourcebuddy.Fluent;
import org.antlr.v4.runtime.*;

import java.io.*;

import odlAst.*;
import orm.Entity;
import visitors.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.*;
import java.sql.*;

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

	public static Fluent.Compiled compileClasses(List<String> classes, String dir) throws IOException, ClassNotFoundException, Compiler.CompileException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		Fluent.CanCompile canCompile = Compiler.java()
				.from(Paths.get(dir +"/"+ classes.get(0) + ".java"));

		for(int i = 1; i < classes.size(); i++){
			canCompile = canCompile.from(Paths.get(dir +"/"+ classes.get(i) + ".java"));
		}

		Fluent.Compiled compiled = canCompile.compile();

		 compiled.saveTo(Paths.get("./target/generated_classes"));


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

		return compiled;

	}


	public static void main(String args[]) throws Exception {

		String url = "jdbc:postgresql://localhost:5432/postgres";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "1234");
		Connection conn = DriverManager.getConnection(url, props);


		/*Statement st = conn.createStatement();
		st.executeQuery("CREATE TABLE accounts (\n" +
				"\tuser_id serial PRIMARY KEY,\n" +
				"\tusername VARCHAR ( 50 ) UNIQUE NOT NULL,\n" +
				"\tpassword VARCHAR ( 50 ) NOT NULL,\n" +
				"\temail VARCHAR ( 255 ) UNIQUE NOT NULL,\n" +
				"\tcreated_on TIMESTAMP NOT NULL,\n" +
				"        last_login TIMESTAMP \n" +
				");");
		st.close();*/

		//insert de teste
		/*INSERT INTO C (b, c, s, i, l, d)
VALUES (B'00001010', 'X', 32767, 2147483647, 9223372036854775807, 3.14159);
*/

		List<Map<String, Object>> resultList = new ArrayList<>();

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM C");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		while (rs.next()) {
			Map<String, Object> rowMap = new HashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				Object columnValue = rs.getObject(i);
				rowMap.put(columnName, columnValue);
			}
			resultList.add(rowMap);
		}

		rs.close();
		st.close();


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
				SQLGenODLVisitor sqlGenODLVisitor = new SQLGenODLVisitor();

				JavaGenODLVisitor javaGenODLVisitor = new JavaGenODLVisitor(sqlGenODLVisitor);
				ast.accept(javaGenODLVisitor);
				String outputDir = "output";

				List<String> classSources = javaGenODLVisitor.getClassSources();
				List<String> classNames = javaGenODLVisitor.getClassNames();

				for(int i = 0; i < classSources.size(); i++){
					writeToFile(outputDir+"/" + classNames.get(i) + ".java", classSources.get(i));
				}

				Fluent.Compiled compiled = compileClasses(classNames, "output");

				final var loaded = compiled.load();
				Class<?> objClass = loaded.get("generatedodl.C");
				Entity obj = (Entity)loaded.newInstance("generatedodl.C");
				Method thisMethod = objClass.getDeclaredMethod("print");
				obj.setAttr("i", 13);
				System.out.println((obj.getAttr("i")));
				thisMethod.invoke(obj);
				//obj.create(conn);


				List<Map<String, Object>> resultSelectAll = obj.selectAll(conn);

				for (Map<String, Object> rowMap : resultSelectAll) {
					for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
						String columnName = entry.getKey();
						Object columnValue = entry.getValue();
						System.out.println(columnName + ": " + columnValue);
					}
					System.out.println();
				}


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