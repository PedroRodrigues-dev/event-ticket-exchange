# Para garantir os melhores resultados irei documentar todo o processo de desenvolvimento

## Inicialização do projeto usando a plataforma https://start.spring.io

### ASDF

- Para garantir que o sistema se mantenha sendo executado na versão adequada,
  implementei o ASDF para gestão de versões do Gradle e do java, as respectivas
  versões podem ser vistas no arquivo .tool_versions

### Bibliotecas incluidas

- Spring Web: Para criar o projeto web e os endpoints RESTful.
- Spring Data JPA: Para mapear as entidades com o banco de dados.
- Flyway Migration: Para gerenciar as migrações de banco de dados.
- MySQL Driver: Para conectar com o banco de dados MySQL.
- Spring Boot DevTools: Para facilitar o desenvolvimento.
- Spring Boot Test: Para realizar os testes com JUnit.

### Docker compose para o uso do mysql

- Ao rodar um docker compose up -d seu mysql 8.0 estará configurado para rodar corretamente
  e com a criação automátida da base de dados necessária para a população realisada pelas migrations

### Execução

- Para simplificação preparei para os usuários de vscode um arquivo launch.json para que os pacotes da aplicação
  sejam compilados e a aplicação seja executada pelo debugger do vscode, facilitando o processo de debug da aplicação,
  caso não deseje utilizar desta maneira o sistema pode ser executado pelo seguinte comando: ./gradlew bootRun
