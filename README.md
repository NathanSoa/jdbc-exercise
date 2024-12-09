# Exercício Capítulo 15 JDBC

- Para rodar o projeto é necessário ter o docker instalado em sua máquina, rode o comando ``docker ps`` em um terminal para verificar;
- Caso não tenha o docker instalado, siga as instruções do site oficial: https://docs.docker.com/get-docker/;
- Para inicializar o banco, basta abrir um terminal na raiz do projeto, e rodar ``docker-compose up``;
- O arquivo Dockerfile irá criar a imagem do banco de dados, bem como fazer a inicialização do nosso modelo de dados;

# Objetivo do exercício

- O objetivo é criar algumas ações no nosso banco de dados simulando um mini Jira super simplificado, todas elas devem utilizar a API JDBC nativa do Java;
- Todas as implementações que devem ser feitas estão na interface [SystemRepository](src/main/java/org/example/jdbccap15/repository/SystemRepository.java)
- Para agilizar o teste do nosso código, estamos utilizando um projeto Spring, mas nenhuma parte do código Spring é relevante para a nossa certificação.
- Por esse motivo, todo o resto do código já está pronto.