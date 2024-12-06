# Para garantir os melhores resultados irei documentar todo o processo de desenvolvimento.

### Inicialização do projeto usando a plataforma https://start.spring.io.

### ASDF

- Para garantir que o sistema se mantenha sendo executado na versão adequada,
  implementei o ASDF para gestão de versões do Gradle e do java, as respectivas
  versões podem ser vistas no arquivo .tool_versions.

### Bibliotecas incluidas

- Spring Web: Para criar o projeto web e os endpoints RESTful.
- Spring Data JPA: Para mapear as entidades com o banco de dados.
- Flyway Migration: Para gerenciar as migrações de banco de dados.
- MySQL Driver: Para conectar com o banco de dados MySQL.
- Spring Boot DevTools: Para facilitar o desenvolvimento.
- Spring Boot Test: Para realizar os testes com JUnit.

### Docker compose para o uso do mysql

- Ao rodar um docker compose up -d seu mysql 8.0 estará configurado para rodar corretamente
  e com a criação automátida da base de dados necessária para a população realisada pelas migrations.
- Recomendo que utilize este container, para essa aplicação efetuar as migrações de dados adequadas,
  é necessário que os dados da pasta "data" passada no teste estejam em "/var/lib/mysql-files/data"
  para poupar tempo fiz o mapeamento e a configuração completa disso no docker compose.

### Execução

- Para simplificação preparei para os usuários de vscode um arquivo launch.json para que os pacotes da aplicação
  sejam compilados e a aplicação seja executada pelo debugger do vscode, facilitando o processo de debug da aplicação,
  caso não deseje utilizar desta maneira o sistema pode ser executado pelo seguinte comando: ./gradlew bootRun.

### Abstrações

- Evitei abstrações para garantir que o sistema a longo prazo possa sofrer alterações referentes a cada regra de negócio específica,
  então não criei nenhum tipo de AbstractService ou AbstractController, devido a isso foi necessário repetir código mas garante boa
  manutenção a longo prazo.

### Paginação

- Devido ao grande volume de dados vi que seria de suma importância uma boa paginação então implementei uma paginação baseada no
  Pageable, não fiz implementações de buscas não paginadas pois para as regras de negócio que planejei isto não faria sentido.

### Testes

- Utilizei o SpringBootTest junto a Mockito para fazer todos os testes de todos os métodos de getById e getAll da aplicação,
  todos os dados utilizados foram ficticios usados por meio do Mockito para garantir que qualquer ambiente que execute o teste
  possa constatar se a aplicação está funcionando plenamente sem fazer qualquer alteração no banco de dados.
- Para garantir que neste teste tenha sido abrangido vários cenários, os primeiros testes realizei após o desenvolvimento, após
  a primeira versão pronta apliquei o TDD para a inclusão da paginação nos controllers.

### Promoções

- As promoções foram desenvolvidas em Listings baseando-se em eventos na data atual ou em datas sequentes que ainda não tenham
  ventido todos os seus ingreços disponíveis, limitado em 10 como o solicitado.

### Documentação

- Para auxiliar na usabilidade criei uma request collection no insomnia e inplementei um swagger na aplicação para a rota /swagger-ui/index.html.
- Inclui um diagrama do banco de dados da aplicação e um diagrama do planejamento do gitflow para o versionamento e distribuição
  de pacotes do projeto.

### Etapas concluidas

- (Concluido) Projete um esquema normalizado e crie um script MySQL DDL correspondente.
- (Concluido) Prepare uma v1 migração versionada Flyway que encapsula o script DDL de criação de esquema.
- (Concluido) Preparar uma migração com versão v2 Flyway que preencha o banco de dados das exportações de dados fornecidas no diretório de dados.
- (Pendente) Crie um Gradle build que invoca Flyway e executa suas migrações.
- (Concluido) Adicione o wrapper Gradle e envie-o para o repositório de origem.
- (Concluido) Realize a confecção das entidades que representem as tabelas geradas na etapa anterior
- (Concluido) Use [JPA] para a criação de repositorios das tabelas/entidades
- (Concluido) Para facilitar a revisão, envie o código-fonte gerado para a fonte repositório.
- (Concluido) Exponha dois endpoints de API RESTful somente leitura para cada uma das entidades que você criado nas Partes 1 e 2.
- (Concluido) Escreva um ou mais testes jUnit que exercitem e validem seu serviço método(s).
- (Concluido) Implementar um endpoint de serviço da web que receba três solicitações parâmetros como entrada:
  data de contexto (obrigatório)
  ID da categoria do evento (opcional)
  cidade do evento (opcional)
- (Concluido) Implemente um ou mais testes jUnit para exercitar e validar seu implementação.
