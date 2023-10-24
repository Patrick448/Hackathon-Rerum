echo
echo "--------------------TESTES CORRETOS--------------------"
echo

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste0.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste1.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste1eMeio.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste2.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste3.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste4.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste5.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste6.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste7.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste8.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste9.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste10.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste11.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste12.lan -i -v

echo
echo "--------------------TESTES ERRADOS--------------------"
echo

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/attrAND.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/attrFALSE.cmd -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/data0.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/data1.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/data2.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/data3.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/function_call_expr.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/function_call_ret_use.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/function0.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/function1.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/if_oneCMD.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/if_teste.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/ifelse_oneCMD.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/instanciate.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/instanciate1.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/main_args.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/main_missing.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/parameters.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/parameters1.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/parameters2.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/return.lan -i -v
java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/errado/teste8.lan -i -v

