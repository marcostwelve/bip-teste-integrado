# 🏆 Desafio Fullstack Integrado - BIP

Este projeto é uma solução fullstack desenvolvida para o desafio técnico da BIP. Ele consiste em um sistema de gerenciamento de benefícios, permitindo o cadastro, listagem e transferência segura de saldos entre benefícios.

## 🏗️ Arquitetura do Projeto

O projeto foi dividido em camadas para garantir a separação de responsabilidades:
* **Banco de Dados (H2):** Banco em memória, inicializado automaticamente com os scripts de schema e seed fornecidos.
* **EJB Module (`ejb-module`):** Contém a regra de negócio central. **(Bug Corrigido)**
* **Backend (`backend-module`):** API RESTful construída com Spring Boot 3 e Spring Data JPA.
* **Frontend (`frontend`):** Aplicação SPA desenvolvida em Angular 16+.

## 🚀 Funcionalidades Implementadas e Requisitos Atendidos

* [x] **Execução de Scripts SQL:** O banco H2 sobe automaticamente carregando a estrutura e os dados iniciais (`schema.sql` e `seed.sql`).
* [x] **Correção de Bug EJB:** O método de transferência no `BeneficioEjbService` foi refatorado para incluir validação de saldo insuficiente e controle de concorrência usando **Pessimistic Lock** (`LockModeType.PESSIMISTIC_WRITE`).
* [x] **Backend CRUD:** Endpoints RESTful implementados para listagem, criação e execução de transferências.
* [x] **Frontend Angular:** Telas de listagem, formulário de cadastro e formulário de transferência integrados ao backend (com tratamento de CORS).
* [x] **Documentação da API:** Swagger UI integrado e configurado.

---

## ⚙️ Como executar o projeto localmente

### Pré-requisitos
* Java 17+
* Node.js (v18+) e npm
* Maven (v3.8+)

### 1. Compilando o EJB e subindo o Backend
Como o Backend depende do EJB Module, é necessário compilar o projeto raiz primeiro para instalar a dependência localmente.

No terminal, na raiz do projeto (`bip-teste-integrado`), execute:
```bash
mvn clean install
```
Em seguida, inicie o servidor Spring Boot:
```bash
cd backend-module
mvn spring-boot:run -pl backend-module
```
O servidor iniciará na porta 8080

```bash
cd frontend
npm install
npm start
```

Acesse a aplicação no navegador via: http://localhost:4200

## 📖 Documentação da API (Swagger)

Com o backend rodando, a documentação completa dos endpoints pode ser acessada e testada diretamente pelo Swagger UI:
👉 http://localhost:8080/swagger-ui/index.html

