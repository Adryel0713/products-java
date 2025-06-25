
# 🛍️ Products API - Java + Spring Boot

API REST segura desenvolvida em **Java 17 com Spring Boot**, que oferece autenticação JWT, controle de acesso por roles (ADMIN / USER), e um CRUD completo de produtos.

## 🔐 Funcionalidades

- Registro e login de usuários com criptografia de senha (BCrypt)
- Geração e validação de tokens JWT
- Controle de acesso baseado em roles: `ROLE_USER` e `ROLE_ADMIN`
- CRUD de produtos: criar, buscar, atualizar e deletar
- Promoção de usuários para administradores
- Tratamento centralizado de exceções (ex: recursos não encontrados, erros de autenticação)

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- PostgreSQL

---

## ▶️ Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/Adryel0713/products-java.git
cd products-java
```

### 2. Abrir no IntelliJ, Eclipse ou VS Code

Certifique-se de ter:
- Java 17 instalado
- Maven configurado

### 3. Configurar banco de dados PostgreSQL

No arquivo `src/main/resources/application.properties`, atualize suas credenciais de conexão com o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 4. Rodar a aplicação

Execute a classe principal:

```bash
com.adryel0713.backend.BackEndApplication
```

---

## 📬 Endpoints principais

| Método | Endpoint                  | Autorização  | Descrição                            |
|--------|---------------------------|--------------|----------------------------------------|
| POST   | `/api/auth/register`      | ADMIN        | Cadastra novo usuário                 |
| POST   | `/api/auth/login`         | Livre        | Retorna o token JWT                   |
| PUT    | `/api/auth/promover`      | ADMIN        | Promove usuário para ADMIN            |
| GET    | `/produtos`               | USER/ADMIN   | Lista todos os produtos               |
| GET    | `/produtos/{id}`          | USER/ADMIN   | Busca produto por ID                  |
| POST   | `/produtos`               | USER/ADMIN   | Cria novo produto                     |
| PUT    | `/produtos/{id}`          | USER/ADMIN   | Atualiza um produto                   |
| DELETE | `/produtos/{id}`          | USER/ADMIN   | Deleta um produto                     |

> ⚠️ Obs: é necessário enviar o token JWT no header das requisições protegidas:  
> `Authorization: Bearer <seu_token>`

---

## 🧪 Testes e Demonstração

Você pode testar todos os endpoints via:
- [Postman](https://www.postman.com/)
- [Insomnia](https://insomnia.rest/)

---

## 📁 Estrutura do Projeto

```bash
src/main/java
├── controllers
│   └── AuthController, ProdutoController
├── exceptions
│   └── GlobalHandleException, NaoEncontradoException
├── model
│   └── Membro, Produto, Role
├── repositories
│   └── MembroRepository, ProdutoRepository, RoleRepository
├── security
│   └── JwtAuthFilter, JwtUtil, SecurityConfig
├── services
│   └── MembroService, ProdutoService, MembroDetailsService
```

---

## 📄 Licença

Este projeto está sob a licença MIT.  
Sinta-se à vontade para usar como base de estudo!
