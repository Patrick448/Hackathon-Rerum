java -jar compiler_lang-1.0-SNAPSHOT-jar-with-dependencies.jar teste_class.odl -w
dot -Tpng ast.dot > ast.png

JAVA_HOME=/home/patrick/.jdks/corretto-19.0.2 mvn package


## ANOTAÇÕES
-[ ] Conectar no banco de dados
-[ ] ORM
  - [ ] create
  - [ ] insert
  - [ ] update
  - [ ] delete
  - [ ] select all
-[x] herança com extends

- byte	(1-byte integer) -> byte -> bit(8)
- char	(1-byte character) -> char -> char(1)
- short	(2-byte integer) -> short -> smallint
- int	(4-byte integer) -> int -> integer
- long	(8-byte integer) -> long -> bigint
- double	(8-byte floating point) -> double -> double precision
- oid	(8-byte internal object identifier) -> long -> bigint/bigserial
- enum	(4-byte integer)*
