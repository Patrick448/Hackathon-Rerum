echo
echo "--------------------TESTES CORRETOS--------------------"
echo

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste0.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste0.j
java -cp JasminCodes teste0

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste1.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste1.j
java -cp JasminCodes teste1

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste1eMeio.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste1eMeio.j
java -cp JasminCodes teste1eMeio

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste2.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste2.j
java -cp JasminCodes teste2

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste3.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste3.j
java -cp JasminCodes teste3

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste4.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste4.j
java -cp JasminCodes teste4

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste5.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste5.j
java -cp JasminCodes teste5

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste6.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste6.j
java -cp JasminCodes teste6

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste7.lan -j -v
vjava -jar jasmin.jar -d JasminCodes/ JasminCodes/teste7.j
java -cp JasminCodes teste7

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste8.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste8.j
java -cp JasminCodes teste8

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste9.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste9.j
java -cp JasminCodes teste9

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste10.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste10.j
java -cp JasminCodes teste10

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste11.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste11.j
java -cp JasminCodes teste11

java -cp .:antlr-4.8-complete.jar LangCompiler testes/semantica/certo/teste12.lan -j -v
java -jar jasmin.jar -d JasminCodes/ JasminCodes/teste12.j
java -cp JasminCodes teste12