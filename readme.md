# Microserviço de Pagamentos

Este é um microserviço dedicado ao gerenciamento de pagamentos para o seu e-commerce. Ele foi desenvolvido utilizando Java com o framework Spring, aproveitando tecnologias como Spring Data, Docker, PostgreSQL, Spring H2 e Spring RabbitMQ.

## Funcionalidades Principais

- **Processamento de Pagamentos**: O microserviço é responsável por processar os pagamentos recebidos pelo e-commerce, garantindo uma transação segura e confiável.

- **Integração com Banco de Dados**: Utiliza o Spring Data para interagir com o banco de dados PostgreSQL, garantindo a persistência e recuperação dos dados de pagamento de forma eficiente.

- **Ambiente Dockerizado**: Todo o ambiente do microserviço, incluindo banco de dados, está contido em containers Docker, facilitando a implantação e escalabilidade.

- **Ambiente de Desenvolvimento com Spring H2**: Durante o desenvolvimento, é possível utilizar o banco de dados em memória H2 fornecido pelo Spring, facilitando o teste e desenvolvimento local.

- **Comunicação Assíncrona com RabbitMQ**: Utiliza o Spring RabbitMQ para comunicação assíncrona entre microserviços, garantindo uma arquitetura resiliente e escalável.

## Pré-requisitos

- Java JDK 8 ou superior
- Docker
- PostgreSQL
- RabbitMQ
- Maven

## Configuração

1. Clone o repositório deste microserviço.

2. Certifique-se de ter o Docker, PostgreSQL e RabbitMQ instalados e em execução.

3. Configure as variáveis de ambiente necessárias, como credenciais de banco de dados e RabbitMQ, conforme necessário.

4. Compile o projeto utilizando o Maven:

```bash
mvn clean install
```

5. Execute o container Docker:

```bash
docker-compose up --build
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