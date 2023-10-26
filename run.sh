mvn clean

JAVA_HOME=/home/patrick/.jdks/corretto-19.0.2 mvn package

jar_file="target/compiler_lang-1.0-SNAPSHOT-jar-with-dependencies.jar"

#docker-compose up -d

/home/patrick/.jdks/corretto-19.0.2/bin/java -jar "$jar_file" teste_class.odl -w dot -Tpng ast.dot > ast.png
