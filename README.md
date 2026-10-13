# Baozi Store — API REST

API REST desenvolvida em Java com Spring Boot para gerenciamento de clientes, produtos e pedidos de uma loja fictícia especializada na venda de baozi (pão chinês).

Projeto acadêmico desenvolvido para a disciplina de Desenvolvimento Web Back-End, do curso de Análise e Desenvolvimento de Sistemas.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman para testes da API

## Funcionalidades

A aplicação permite realizar operações CRUD (Create, Read, Update e Delete) nas seguintes entidades:

**Cliente:** cadastro, listagem, consulta por ID, atualização e exclusão de clientes.

**Produto:** cadastro, listagem, consulta por ID, atualização e exclusão de produtos.

**Pedido:** cadastro, listagem, consulta por ID, atualização e exclusão de pedidos.

Ao cadastrar ou atualizar um pedido, a API verifica se os identificadores de cliente e produto informados correspondem a registros existentes.

## Endpoints da API

### Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/clientes` | Cadastrar cliente |
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Consultar cliente por ID |
| PUT | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Excluir cliente |

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastrar produto |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Consultar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |

### Pedidos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/pedidos` | Cadastrar pedido |
| GET | `/pedidos` | Listar pedidos |
| GET | `/pedidos/{id}` | Consultar pedido por ID |
| PUT | `/pedidos/{id}` | Atualizar pedido |
| DELETE | `/pedidos/{id}` | Excluir pedido |

## Configuração do banco de dados

A aplicação utiliza MySQL para persistência dos dados.

**Por segurança, a senha do banco de dados não está armazenada diretamente no código-fonte.** Ela deve ser fornecida por meio de uma variável de ambiente.

O arquivo `application.properties` utiliza a seguinte configuração:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/baozi_store}
spring.datasource.username=${DB_USER:springuser}
spring.datasource.password=${DB_PASSWORD}
```

Antes de executar o projeto:

1. Instale e configure o MySQL.
2. Crie um banco de dados chamado `baozi_store`.
3. Configure um usuário MySQL com permissão para acessar esse banco.
4. Defina as variáveis de ambiente necessárias.

As variáveis são:

| Variável | Descrição |
|---|---|
| `DB_URL` | URL de conexão com o MySQL. Opcional quando utilizado o endereço padrão. |
| `DB_USER` | Usuário do MySQL. O valor padrão é `springuser`. |
| `DB_PASSWORD` | Senha do usuário MySQL. Obrigatória. |

No Windows, utilizando o PowerShell, as variáveis podem ser configuradas para a sessão atual:

```powershell
$env:DB_USER="springuser"
$env:DB_PASSWORD="SUA_SENHA_LOCAL"
```

Substitua `SUA_SENHA_LOCAL` pela senha configurada no seu MySQL.

Caso utilize outro usuário, altere também `DB_USER`.

**Importante:** não publique senhas reais no GitHub nem as adicione ao arquivo `application.properties`.

## Como executar

Com o Java 21, o MySQL e as variáveis de ambiente configurados, abra um terminal na pasta do projeto e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em:

`http://localhost:8080`

As requisições podem ser realizadas pelo Postman ou por outro cliente HTTP.

## Exemplo de requisição

Cadastro de um pedido:

**POST** `/pedidos`

```json
{
  "clienteId": 3,
  "produtoId": 3,
  "quantidade": 1
}
```

Para atualizar a quantidade de um pedido existente:

**PUT** `/pedidos/3`

```json
{
  "clienteId": 3,
  "produtoId": 3,
  "quantidade": 3
}
```

Os identificadores utilizados são ilustrativos e dependem dos registros existentes no banco de dados.

## Testes

As operações da API foram verificadas por meio do Postman, incluindo cadastro, consulta, listagem, atualização, exclusão e respostas HTTP para registros inexistentes.

A persistência dos dados também foi conferida no MySQL Workbench.

## Autor

**Leonardo Oliveira da Silva**

Curso: Análise e Desenvolvimento de Sistemas

GitHub: https://github.com/leonardoOsilva
