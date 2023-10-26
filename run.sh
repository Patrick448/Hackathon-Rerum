mvn clean
mvn package
jar_file="target/compiler_lang-1.0-SNAPSHOT-jar-with-dependencies.jar"
java -jar target/compiler_lang-1.0-SNAPSHOT-jar-with-dependencies.jar


# Container onde o banco esta rodando
#container_name="hackathon-rerum"

# Tempo limite para aguardar criação do container
#timeout=20

# Inicia tempo
#start_time=$(date +%s)

#while true; do
#    if docker ps --filter "name=$container_name" --format "{{.Names}}" | grep -q "$container_name"; then
#        echo "$container_name está em execução."
#        sleep 5
#        #JAVA_HOME=/home/patrick/.jdks/corretto-19.0.2 -jar "$jar_file" teste_class.odl -w dot -Tpng ast.dot > ast.png
#        break
#    fi
#
#    current_time=$(date +%s)
#    elapsed_time=$((current_time - start_time))
#
#    if [ $elapsed_time -ge $timeout ]; then
#        echo "Tempo limite atingido."
#        exit 1
#    fi
#
#    sleep 1
#done

