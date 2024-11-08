# 📖 Desafio Pitang API

Este projeto é uma API RESTful desenvolvida para o Desafio Pitang, que permite o cadastro, login, e gerenciamento de usuários e carros.


## 💻 Tecnologias Utilizadas

- **Java 21**: JDK utilizada no projeto.
- **Angular**: Front-end do projeto.
- **Spring Boot**: Framework Java para desenvolvimento de APIs REST.
- **Spring Security**: Para autenticação e segurança, usando JWT (JSON Web Tokens).
- **Hibernate / JPA**: Para gerenciamento de banco de dados.
- **H2**: Banco de dados em memória.
- **Lombok**: Para reduzir boilerplate code em classes Java.
- **JUnit**: Para testes unitários.
- **Git**: Para versionamento.


## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 21
- Maven

### Passos para rodar

1. **Clone o repositório**:
    ```bash
    git clone PROJETO
    ```

2. **Acesse o diretório do projeto**:
    ```bash
    cd desafio-pitang-api
    ```

3. **Compile e execute o projeto**:

    ```bash
    mvn spring-boot:run
    ```

4. **A API estará disponível** no endereço:
    ```
    http://localhost:8080/api
    ```


## 📜 Estórias de Usuário

### 1. **Autenticação e gerenciamento de Perfis** 👤

- **<ins>1.1. Autenticação**: Como novo usuário, quero me cadastrar no sistema informando meus dados pessoais para criar um perfil e acessar a aplicação.
- **<ins>1.2. Ver Meu Perfil**: Como usuário autenticado, quero ver meu perfil completo para verificar e acompanhar minhas informações.
- **<ins>1.3. Atualizar Usuário**: Como usuário autenticado, quero atualizar os dados pessoais de um usuário para manter o perfil sempre atualizado.
- **<ins>1.4. Visualizar Usuários**: Como usuário, quero ver uma lista de todos os usuários cadastrados e visualizar os detalhes de um usuário específico para gerenciar as informações de cada um.
- **<ins>1.5. Remover Usuário**: Como usuário, quero remover um usuário específico do sistema para manter os dados dos usuários atualizados e organizados.


### 2. **Gerenciamento de Carros** 🚗

- **<ins>2.1. Registrar Carro no Perfil**: Como usuário autenticado, quero registrar um carro no meu perfil para ter os dados do meu veículo associados a mim.
- **<ins>2.2. Visualizar Carros**: Como usuário autenticado, quero poder listar: todos os carros, apenas os meus carros e visualizar as informações de um deles para ter completas sobre ele.
- **<ins>2.3. Atualizar um Carro**: Como usuário autenticado, quero atualizar as informações de um carro para manter o carro sempre atualizado.
- **<ins>2.4. Remover Carro do Perfil**: Como usuário autenticado, quero remover um carro específico do meu perfil para que minhas informações de veículos estejam sempre atualizadas.


## 🔗 Endpoints

### 1. **Autenticação** 🔐

- **📬 POST <ins>/api/signin**: Realiza o login do usuário e retorna um token JWT.
    - **Body**:
        ```json
        {
          "login": "usuario@exemplo.com",
          "password": "senha"
        }
        ```

- **📬 POST <ins>/api/users**: Realiza o cadastro de um novo usuário.
    - **Body**:
        ```json
        {
          "firstName": "Hello",
          "lastName": "World",
          "email": "hello@world.com",
          "birthday": "1990-05-01",
          "login": "hello.world",
          "password": "h3ll0",
          "phone": "988888888",
          "cars": [
             {
             "year": 2018,
             "licensePlate": "PDV-0625",
             "model": "Audi",
             "color": "White"
             }
          ]
        }

        ```

### 2. **Usuários** 👥

- **🫴 GET <ins>/api/users**: Retorna todos os usuários.
- **🫴 GET <ins>/api/users/{id}**: Retorna um usuário específico pelo ID.
- **🔄 PUT <ins>/api/users/{id}**: Atualiza as informações de um usuário.
    - **Body**:
        ```json
        {
          "id": "id-gerado-automaticamente",
          "firstName": "Hello",
          "lastName": "World",
          "email": "hello@world.com",
          "birthday": "1990-05-01",
          "login": "hello.world",
          "password": "h3ll0",
          "phone": "988888888",
          "cars": [
             {
             "year": 2018,
             "licensePlate": "PDV-0625",
             "model": "Audi",
             "color": "White"
             }
          ]
        }
        ```
- **❌ DELETE <ins>/api/users/{id}**: Deleta um usuário específico.

### 3. **Carros** 🚙

- **📬 POST <ins>/api/cars**: Cadastra um carro para o usuário autenticado.
    - **Body**:
        ```json
        {
        "year": 2018,
        "licensePlate": "PDV-0625",
        "model": "Audi",
        "color": "White"
        }
        ```

- **🫴 GET <ins>/api/cars**: Retorna todos os carros do usuário autenticado.
- **🫴 GET <ins>/api/cars/{id}**: Retorna um carro específico do usuário autenticado.
- **🔄 PUT <ins>/api/cars/{id}**: Atualiza as informações de um carro.
    - **Body**:
        ```json
        {
        "id": "id-gerado-automaticamente",
        "year": 2018,
        "licensePlate": "PDV-0625",
        "model": "Audi",
        "color": "White"
        }
        ```
- **❌ DELETE <ins>/api/cars/{id}**: Deleta um carro.

## 🔬 Testes

O projeto inclui testes unitários para garantir o funcionamento correto das principais funcionalidades. Para rodar os testes:
```bash
mvn test
```


## Contato

**🙋🏻‍♂️ Autor**: André Cahú Melo  
**✉️ E-mail**: andrecahu28@gmail.com  
**⚡ GitHub**: [https://github.com/andrecahu](https://github.com/andrecahu)
