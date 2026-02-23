# 📋 Kanban Board

## O que é o projeto 📖

O **Kanban Board** é um sistema de gerenciamento de tarefas em estilo Kanban, desenvolvido em Java. Ele permite criar, visualizar, atualizar e excluir cards (cartões) que passam por diferentes estágios: To Do, Processing, Done e Approved.

O aplicativo roda no console e utiliza banco de dados MySQL para persistência dos dados.

---

## Como foi feito 🛠️

O projeto segue uma arquitetura em camadas:

- **db** — Camada de acesso ao banco (conexão e tratamento de erros)
- **model** — Entidades e lógica de negócio (DAO)
- **Application** — Camada de interface com o usuário (console)

### Tecnologias utilizadas ⚙️

- **Java** (linguagem principal)
- **JDBC** — Acesso ao banco de dados
- **MySQL** — Banco de dados relacional
- **Padrão DAO** (Data Access Object) — Separação entre modelo e persistência

### Estrutura do projeto 📁

```
kanban_board/
├── db.properties              # Configuração de conexão com o banco
├── src/
│   ├── db/                    # Classes de banco de dados
│   │   ├── DB.java
│   │   ├── DbException.java
│   │   └── DbIntegrityException.java
│   ├── model/                 # Camada de modelo
│   │   ├── entities/          # Entidades do domínio
│   │   │   ├── Card.java
│   │   │   └── enums/
│   │   │       └── CardStatus.java
│   │   └── dao/               # Acesso a dados
│   │       ├── CardDao.java
│   │       ├── DaoFactory.java
│   │       └── impl/
│   │           └── CardDaoJDBC.java
│   └── Application/
│       └── Program.java       # Ponto de entrada da aplicação
```

---

## Como usar 🚀

### 1. Pré-requisitos 📦

- Java JDK 8 ou superior
- MySQL Server
- Driver JDBC do MySQL (ex.: `mysql-connector-java`)

### 2. Configurar o banco de dados 🗄️

Crie o banco e a tabela no MySQL:

```sql
CREATE DATABASE kanbanboard;

USE kanbanboard;

CREATE TABLE card (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255),
    Topic VARCHAR(255),
    CreatedDate DATE,
    CardStatus VARCHAR(50)
);
```

### 3. Configurar conexão 🔌

Edite o arquivo `db.properties` na raiz do projeto:

```properties
user=seu_usuario
password=sua_senha
dburl=jdbc:mysql://localhost:3306/kanbanboard
useSSL=false
```

### 4. Executar o projeto ▶️

Abra o terminal na pasta do projeto e execute:

```bash
java -cp .;mysql-connector-java-X.X.XX.jar Application.Program
```

No Eclipse/IDE: execute a classe `Program` em `src/Application/Program.java`.

### 5. Menu de opções 📌

| Opção | Descrição |
|-------|-----------|
| 1 | 📄 Mostrar todos os cards do board |
| 2 | 🔍 Filtrar card por ID |
| 3 | ➕ Criar novo card |
| 4 | ✏️ Atualizar título e tópico do card |
| 5 | 🔄 Atualizar status do card |
| 6 | 🗑️ Remover card |
| 7 | 👋 Sair |

---

## Documentação das classes 📚

### Classes do pacote `db` 🗃️

#### `DB.java` 🔗

Gerencia a conexão com o banco de dados.

| Método | Descrição |
|--------|-----------|
| `getConnection()` | Retorna uma conexão com o banco. Cria a conexão na primeira chamada (singleton). |
| `closeConnection()` | Fecha a conexão com o banco. |
| `loadProperties()` | Carrega o arquivo `db.properties` e retorna as propriedades de conexão. |
| `closeStatement(Statement st)` | Fecha o `Statement` de forma segura. |
| `closeResultSet(ResultSet rs)` | Fecha o `ResultSet` de forma segura. |

Uso de recursos via `try-with-resources` ou chamadas manuais de `close` evita vazamento de conexões e statements.

---

#### `DbException.java` ⚠️

Exceção de runtime para erros gerais de banco de dados.

- **Tipo:** `RuntimeException`
- **Uso:** Erros de SQL, falhas de conexão, falhas ao carregar propriedades, etc.
- Permite que o fluxo de erros seja propagado sem necessidade de `try-catch` em todas as camadas.

---

#### `DbIntegrityException.java` 🛡️

Exceção específica para violações de integridade.

- **Tipo:** `RuntimeException`
- **Uso:** Erros como violação de chave estrangeira, constraints, etc.
- Permite tratamento diferenciado em cenários de integridade do banco.

---

### Classes do pacote `model` 📦

#### `Card.java` (entidade) 🃏

Representa um card no Kanban.

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `id` | `Integer` | Identificador único (gerado pelo banco) |
| `title` | `String` | Título do card |
| `topic` | `String` | Tópico/descrição |
| `createdDate` | `Date` | Data de criação |
| `cardStatus` | `CardStatus` | Status atual (enum) |

Implementa `Serializable` para possível serialização. Possui `hashCode` e `equals` baseados em `id` e `toString` para debug.

---

#### `CardStatus.java` (enum) 🏷️

Define os estados possíveis de um card.

| Valor | Descrição |
|-------|-----------|
| `TO_DO` | A fazer |
| `PROCESSING` | Em andamento |
| `DONE` | Concluído |
| `APPROVED` | Aprovado |

---

#### `CardDao.java` (interface DAO) 📜

Contrato das operações de persistência de `Card`.

| Método | Descrição |
|--------|-----------|
| `insert(Card card)` | Insere um novo card. |
| `updateTitleTopic(Card card)` | Atualiza título e tópico. |
| `updateStatus(Card card)` | Atualiza apenas o status. |
| `delete(Integer id)` | Remove o card pelo ID. |
| `findById(Integer id)` | Busca card por ID. |
| `findAll()` | Retorna todos os cards. |

---

#### `DaoFactory.java` 🏭

Responsável por criar instâncias de DAOs.

- **Método:** `createdCardDao()` — Retorna uma implementação de `CardDao` usando JDBC.
- Permite trocar a implementação (ex.: JDBC, JPA) sem alterar o restante do código.

---

#### `CardDaoJDBC.java` (implementação DAO) 💾

Implementação de `CardDao` usando JDBC.

| Método | Descrição |
|--------|-----------|
| `insert()` | Executa `INSERT` com `Statement.RETURN_GENERATED_KEYS` e define o `id` no objeto. |
| `updateTitleTopic()` | Executa `UPDATE` em `Title` e `Topic` pelo `Id`. |
| `updateStatus()` | Executa `UPDATE` em `CardStatus` pelo `Id`. |
| `delete()` | Executa `DELETE` pelo `Id`. |
| `findById()` | Executa `SELECT *` filtrado por `Id` e retorna `null` se não houver resultado. |
| `findAll()` | Executa `SELECT *` e retorna lista de todos os cards. |
| `instantiateCard()` | Converte um `ResultSet` em um objeto `Card`. |

Utiliza `PreparedStatement` para evitar SQL injection e fecha recursos com métodos auxiliares da classe `DB`.
