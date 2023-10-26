
# Geração de Código para persistência de dados com uso da ODL

O objetivo do projeto é a geração de código para persistência de dados com uso da ODL. A linguagem ODL escolhida como referência foi a EYEDB ODL (https://www.eyedb.org/wp-content/uploads/documentation/manual/html/ODL/node2.html)

Projeto criado com Apache Maven 3.6.3 utilizado Java na versão 19.0.2.

Para execultar o projeto basta rodar ./run.sh no diretorio principal

Os comandos aceitos pelo terminal são:


* load nomeDoArquivo.odl: comando para fazer o load do arquivo na linguagem ODL
* create noemDaClasse: comando para criação da classe previamente carregada
* insert nomeDaClasse objeto: comando responsável por inserir no banco de dados o objeto *objeto* na classe *nomeDaClasse*
* delete nomeDaClasse id: comando responsável por deletar no banco de dados o objeto com o id *id* na classe *nomeDaClasse*
* select nomeDaClasse id: comando para selecionar o objeto de id *id* na classe *nomeDaClasse*
* selectall nomeDaClasse: comando para selecionar todos os objetos da classe *nomeDaClasse*
* update nomeDaClasse id objeto: comando para atualizar o objeto de id *id* da classe *noemDaClasse* pelo novo objeto *objeto*


Nomes: Maria Eduarda de Medeiros Simonassi, Patrick Canto de Carvalho, Wellington Pereira Silva