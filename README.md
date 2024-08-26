# Teste POL #

## Como rodar ##
- Instalar o PostgreSQL (https://www.postgresql.org/download/)
- Adicionar as propriedades do projeto no arquivo raíz, criar um arquivo nomeado env.properties com as variáveis de ambiente (DB_DATABASE, DB_USER, DB_PASSWORD)
- Deve-se criar uma base de dados no PostgreSQL, usando pgadmin ou via terminal (utilizei o nome pol)
- Ao iniciar a base de dados o Liquibase será responsável por gerar todos os dados necessários, como tabelas, colunas, etc.

## Requisições HTTP ##
- Para cadastrar um novo processo, POST  ```/api/cases```, usando o seguinte formato JSON no envio do body `{"caseNumber": 12345}`
- Para acessar todos os processos, GET ```/api/cases```
- Para cadastrar um ou mais réus em um processo existente, POST ```/api/cases/{id_do_processo}/defendants```, enviando JSON no body um array contendo os nomes, como por exemplo: `{
    "names": ["John Doe", "Jane Smith", "Alice Johnson"]
}`
- Para deletar um processo, DELETE ```api/cases/{id_do_processo}```

### Tecnologias e ferramentas utilizadas ###
- Java (Spring Boot)
- PostgreSQL
- Liquibase
- JUnit
- Spring initializer (https://start.spring.io/)
