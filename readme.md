# Microserviço de Pagamentos

> Esse projeto está sendo construindo enquanto estudo outras tecnologias. E mais, é um projeto no qual estou testando algumas formas diferentes de construir meus projetos.
> Logo, eu sei cada conceito que apliquei aqui, estou apenas pensando um pouco fora da caixa e testando.
> Afinal DDD, TDD, BBB (rsrs) e qualquer outra abordagem, NÃO SÃO BALAS DE PRATA!

Este é um microserviço dedicado ao gerenciamento de pagamentos para o seu e-commerce.

## Dúvidas
- **Por que tu não usa módulos gradle**?
  - Não vejo necessidade pra esse projeto.
- **Por que não usa: domain, infraestructure, e afins?**
  - Não curto essa divisão.
- **E porque...**
  - Como comentei acima, estou testando novas formas de codificar.

## Funcionalidades Principais

- **Processamento de Pagamentos**: O microserviço é responsável por processar os pagamentos recebidos pelo e-commerce, garantindo uma transação segura e confiável.

- **Integração com Banco de Dados**: Utiliza o Spring Data para interagir com o banco de dados PostgreSQL, garantindo a persistência e recuperação dos dados de pagamento de forma eficiente.

- **Ambiente Dockerizado**: Todo o ambiente do microserviço, incluindo banco de dados, está contido em containers Docker, facilitando a implantação e escalabilidade.

- **Ambiente de Desenvolvimento com Spring H2**: Durante o desenvolvimento, é possível utilizar o banco de dados em memória H2 fornecido pelo Spring, facilitando o teste e desenvolvimento local.

- **Comunicação Assíncrona com RabbitMQ**: Utiliza o Spring RabbitMQ para comunicação assíncrona entre microserviços, garantindo uma arquitetura resiliente e escalável.

## Tópicos abordados nesse projeto
- **Arquitetura**
- **DDD**
  - Entities
  - Aggregates
  - Value Objects (geralmente uso a nomeclatura 'primitives')
  - Identifiers
  - BaseRepositoryContract, InMemoryRepositoryContract, InMemoryRepository (implementação)
  - Pagination Entity
    - Search Direction Entity
  - Artifacts
    - **Contém toda a lógica genéria e purista de um conceito dentro do domímio (ou subdomínio)**
      - https://socadk.github.io/design-practice-repository/artifact-templates/DPR-StrategicDDDContextMap.html
      - https://www.ibm.com/docs/en/rsas/7.5.0?topic=overview-artifacts
      - https://www.ibm.com/docs/en/engineering-lifecycle-management-suite/lifecycle-management/7.0.1?topic=artifacts-
  - Modules ou Bounded Contexts?
    - https://www.isaqb.org/blog/ddd-confusion-bounded-subdomain-context-module-or-what/
  - Domain Repository, Domain Repository Contract, App Repository, App Repository Contract
  - Domain Exception
- **TDD**
  - Test First
  - Arrange, Act, Assert (Given / When / Should)
  - Unit Tests
  - Integrated Tests
  - e2e tests (_WIP_)
- **Clean Architecture / Hexagonal (do meu jeito de fazer)**
  - **Como estou usando CQRS (do meu jeito de fazer)**
    - Command Handler (mesmo que usecase);
    - Query Handler (mesmo que usecase)
- **CQRS**
  - Query Handlers
  - Queries
  - Command Handlers
  - Commands
- **API Docs**
  - OpenAPI
  - Swagger
- **Testes**
  - JUnit, Mockito
  - WebMvcTest, JacksonTest, DataJpaTest
  - Custom Annotations
  - @MockMVC, @MockBean, @Mock, @InjectMock, @BeforEeach
  - Serialização e Desserialização com Jackson e Object Mapper

## Tecnologias

- Java
- Spring Boot
- Spring Profiles
- Spring AMQP (_WIP_)
- Spring Data JPA
- Flyway / Migrations (_WIP_)
- Gradle
- Docker
- Kubernetes (_WIP_)
- Postgres
- H2 Database
- RabbitMQ (_WIP_)
- JUnit, Mockito
- Rest Template (_WIP_)
- TestContainers (_WIP_)
- CI / CD (_WIP_)
- Keycloak  / Custom Identity Service (_WIP_)
- ElasticStack, DataDog, OpenTelemetry (_WIP_)
- Spring Cloud Gateway (_WIP_)

## Configuração

1. Clone o repositório deste microserviço.

2. Certifique-se de ter o Docker, PostgreSQL e RabbitMQ instalados e em execução.

3. Configure as variáveis de ambiente necessárias, como credenciais de banco de dados e RabbitMQ, conforme necessário.

4. Compile o projeto utilizando o Maven:

```bash
gradle
```

5. Suba os containers:

```bash
docker compose up -d
```

Isso iniciará o microserviço de pagamentos juntamente com o banco de dados PostgreSQL e o serviço RabbitMQ.

## Uso

Após a configuração e inicialização bem-sucedidas, o microserviço estará pronto para receber solicitações de pagamento do seu e-commerce. Certifique-se de integrar corretamente o seu e-commerce com este microserviço para processar pagamentos de forma eficaz.

## Contribuição

Contribuições são bem-vindas! Se você encontrar problemas, bugs ou desejar adicionar novos recursos, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

Este README oferece uma visão geral do seu microserviço de pagamentos e fornece instruções básicas sobre como configurar, executar e contribuir para o projeto. Certifique-se de personalizá-lo de acordo com as necessidades específicas do seu projeto e da sua equipe.
