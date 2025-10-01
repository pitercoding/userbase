# 🧑‍💻 Userbase

Um sistema de **registro e gerenciamento de usuários** implementado em Java com Spring Boot, utilizando H2 como banco de dados em memória. O projeto oferece endpoints REST para **criar, ler, atualizar (PUT/PATCH) e deletar usuários**, com validação e tratamento de exceções.

Projeto desenvolvido como exercício prático para praticar Spring Boot, JPA, validação e testes unitários.

---

## 🎯 Por que esse projeto?

- Praticar desenvolvimento de APIs REST com Spring Boot
- Aprender a trabalhar com **CRUD completo** (Create, Read, Update, Delete)
- Implementar **PATCH** para atualizações parciais
- Trabalhar com **validação de dados** e **tratamento global de exceções**
- Criar testes unitários e de integração para garantir a qualidade do código

## 📂 Organização do Projeto

```text
userbase/
├── src/main/java/com/pitergomes/userbase/
│   ├── business/
│   │   └── UserService.java              # Lógica de negócio e manipulação de usuários
│   ├── controller/
│   │   └── UserController.java           # Endpoints REST
│   ├── exception/
│   │   └── GlobalExceptionHandler.java   # Tratamento global de erros
│   ├── infrastructure/
│   │   ├── entities/
│   │   │   └── User.java                 # Entidade User mapeada para JPA
│   │   └── repository/
│   │       └── UserRepository.java      # Interface de persistência com JPA
│   └── UserbaseApplication.java         # Classe principal Spring Boot
├── src/test/java/com/pitergomes/userbase/
│   ├── business/
│   │   └── UserServiceTest.java          # Testes unitários do serviço
│   └── controller/
│       └── UserControllerTest.java       # Testes de integração do controller
├── src/main/resources/
│   └── application.properties            # Configurações do Spring Boot e H2
├── pom.xml                                # Dependências e build Maven
├── .gitignore                             # Arquivos ignorados pelo Git
└── README.md                              # Este arquivo
```

## 🛠️ Ferramentas e Tecnologias

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Lombok** (redução de boilerplate)
- **Validation API** (jakarta.validation)
- **JUnit 5** + **Mockito** (testes unitários e de integração)
- **Maven** (gerenciamento de dependências e build)

## 🚀 Como rodar o projeto

### 1. Clonar o repositório:
```bash

git clone https://github.com/pitercoding/userbase.git
cd userbase
```

### 2. Buildar e rodar com Maven:
```bash

mvn clean install
mvn spring-boot:run
```

### 3. Acessar a API:

Você pode testar a API usando **Postman, Insomnia ou curl**.

- **POST /user** → criar usuário

```bash

curl -X POST http://localhost:8080/user \
-H "Content-Type: application/json" \
-d '{
  "fullName": "John Doe",
  "email": "john@example.com",
  "birthDate": "1990-01-01",
  "phoneNumber": "+5511999999999",
  "password": "123456"
}'
```

- **GET /user?email={email}** → buscar usuário por email

```bash

curl http://localhost:8080/user?email=john@example.com
```

- **PUT /user?id={id}** → atualizar usuário completo

```bash

curl -X PUT http://localhost:8080/user?id=1 \
-H "Content-Type: application/json" \
-d '{
  "fullName": "John Doe Updated",
  "email": "john@example.com",
  "birthDate": "1990-01-01",
  "phoneNumber": "+5511999999999",
  "password": "654321"
}'
```

- **PATCH /user/{id}** → atualizar campos específicos do usuário

```bash

curl -X PATCH http://localhost:8080/user/1 \
-H "Content-Type: application/json" \
-d '{
  "phoneNumber": "+5511988888888"
}'
```

- **DELETE /user?email={email}** → remover usuário

```bash

curl -X DELETE http://localhost:8080/user?email=john@example.com
```

### 4. Acessar console H2 (opcional):
```yaml

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:user
User: sa
Password: (em branco)
```

## 🎨 Funcionalidades do projeto

- CRUD completo de usuários
- Validação de campos: nome, email, senha, telefone
- Atualização parcial com PATCH usando reflexão
- Tratamento global de exceções: 400 (validação), 404 (não encontrado)
- Testes unitários e de integração garantem a confiabilidade

## 🧪 Testes

O projeto contém testes unitários (`UserServiceTest`) e testes de integração (`UserControllerTest`) utilizando **JUnit 5** e **Mockito**.

Para rodar os testes:

```bash

mvn test
```

## 📜 Licença
Este projeto está licenciado sob a **licença MIT**.

## 🤝 Contribuições
Contribuições são bem-vindas!
Sinta-se à vontade para abrir **issues** ou **pull requests** para melhorias, correções ou novos recursos.