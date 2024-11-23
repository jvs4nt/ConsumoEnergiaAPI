# ConsumoEnergiaAPI

Este projeto é uma API REST desenvolvida em Java utilizando o framework Spring Boot, com HATEOAS implementado para fornecer links dinâmicos nos recursos e documentada com Swagger para facilitar a visualização e teste dos endpoints.

---

## **Índice**

- [Requisitos](#requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endereços e Rotas da API](#endereços-e-rotas-da-api)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)\

---

## Links:
- Link do DEPLOY em nuvem: https://consumoenergiaapi.onrender.com (Render)
- Link do repositório: https://github.com/jvs4nt/ConsumoEnergiaAPI
- Link do vídeo pitch: https://youtu.be/akG1joR57Ew
- Link do vídeo de apresentação do projeto: https://youtu.be/hgWoSCiyKqM

---

## **Requisitos**

Antes de iniciar, certifique-se de que você possui os seguintes requisitos instalados em sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- Banco de dados PostgreSQL (local ou Supabase)

---

## **Instalação e Configuração**

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/consumo-energia-api.git
cd consumo-energia-api
```

### 2. Configure o Banco de Dados
- No arquivo application.yml, insira as configurações do nosso banco de dados para testes PostgreSQL. Seguem os dados:

```bash
spring:
  datasource:
    url: jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?sslmode=require
    username: postgres.ykrfwvpurcxiuoreavyd
    password: EU6Rsy9pMgcpXk85
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect

```

### 3. Compile o projeto
- No diretório raiz do projeto, execute:
```bash
mvn clean install
```

---

## Como rodar o projeto
### 1. Execute a aplicação
- No terminal, dentro do diretório do projeto, rode o seguinte comando:
```bash
mvn spring-boot:run
```
- A aplicação estará disponível em: http://localhost:8080

### 2. Acesse a Interface do Swagger
- Acesse o Swagger para testar os endpoints diretamente na interface:
```bash
http://localhost:8080/swagger-ui.html
```

---

## **Endereços e Rotas da API**

### **Consumos**
- **POST** `/api/consumos`: Cria um novo consumo.
- **GET** `/api/consumos`: Lista todos os consumos.
- **GET** `/api/consumos/{id}`: Retorna os detalhes de um consumo pelo ID.
- **PUT** `/api/consumos/{id}`: Atualiza um consumo existente pelo ID.
- **DELETE** `/api/consumos/{id}`: Exclui um consumo existente pelo ID.

### **Metas**
- **POST** `/api/metas`: Cria uma nova meta.
- **GET** `/api/metas`: Lista todas as metas.
- **GET** `/api/metas/{id}`: Retorna os detalhes de uma meta pelo ID.
- **GET** `/api/metas/email/{email}`: Retorna os detalhes de uma meta pelo Email.
- **PUT** `/api/metas/{id}`: Atualiza uma meta existente pelo ID.
- **DELETE** `/api/metas/{id}`: Exclui uma meta existente pelo ID.

### **Dispositivos**
- **POST** `/api/dispositivos`: Cria um novo dispositivo.
- **GET** `/api/dispositivos`: Lista todos os dispositivos.
- **GET** `/api/dispositivos/{id}`: Retorna os detalhes de um dispositivo pelo ID.
- **PUT** `/api/dispositivos/{id}`: Atualiza um dispositivo existente pelo ID.
- **DELETE** `/api/dispositivos/{id}`: Exclui um dispositivo existente pelo ID.

### **Usuários**
- **POST** `/api/usuarios`: Cria um novo usuário.
- **GET** `/api/usuarios`: Lista todos os usuários.
- **GET** `/api/usuarios/{id}`: Retorna os detalhes de um usuário pelo ID.
- **PUT** `/api/usuarios/{id}`: Atualiza um usuário existente pelo ID.
- **DELETE** `/api/usuarios/{id}`: Exclui um usuário existente pelo ID.

---

## **Funcionalidades Implementadas**

### **CRUD Completo para as Entidades**
- `Consumo`, `Meta`, `Dispositivo` e `Usuario`.

### **HATEOAS**
- Links dinâmicos adicionados em todas as respostas da API.

### **Documentação com Swagger**
- Todos os endpoints são documentados no Swagger UI para fácil visualização e teste.

### **Banco de Dados PostgreSQL**
- Integração com PostgreSQL para persistência dos dados.

---

## **Tecnologias Utilizadas**

- **Java 17**: Linguagem principal.
- **Spring Boot**: Framework principal para desenvolvimento da API.
- **Spring Data JPA**: Para manipulação do banco de dados.
- **PostgreSQL**: Banco de dados relacional.
- **HATEOAS**: Para implementar o nível 3 de maturidade REST.
- **Swagger**: Para documentação e teste dos endpoints.

---

## **Como Testar**

### Exemplos de JSON para Requisições

#### **Criar um Consumo**
**POST** `/api/consumos`
```json
{
  "dataUso": "2024-08-01",
  "tempoUso": 10,
  "totalConsumo": 100.0,
  "usuarioId": 16,
  "dispositivoId": 12
}
```

#### **Criar uma Meta**
**POST** `/api/metas`
```json
{
  "valorMeta": 4000.0,
  "dataCadastro": "2024-11-22",
  "usuarioEmail": "joao342@gmail.com"
}
```

Demais padrões de consulta na documentação Swagger...

## Obrigado pela atenção e sinta-se livre para testar a API

### 2024, EcoLight - All Rights Reserved.



