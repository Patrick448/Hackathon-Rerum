java -jar compiler_lang-1.0-SNAPSHOT-jar-with-dependencies.jar teste_class.odl -w
dot -Tpng ast.dot > ast.png

JAVA_HOME=/home/patrick/.jdks/corretto-19.0.2 mvn package


## ANOTAÇÕES
- [ ] Conectar no banco de dados
-[ ] ORM
  - [ ] create
  - [ ] insert
  - [ ] update
  - [ ] delete
  - [ ] select all
-[x] herança com extends

