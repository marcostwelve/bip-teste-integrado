🏗️ Desafio Fullstack Integrado
🚨 Instrução Importante (LEIA ANTES DE COMEÇAR) ❌ NÃO faça fork deste repositório.

Este repositório é fornecido como modelo/base. Para realizar o desafio, você deve: ✅ Opção correta (obrigatória) Clique em “Use this template” (se este repositório estiver marcado como Template) OU Clone este repositório e crie um NOVO repositório público em sua conta GitHub. 📌 O resultado deve ser um repositório próprio, independente deste.

🎯 Objetivo
Criar solução completa em camadas (DB, EJB, Backend, Frontend), corrigindo bug em EJB e entregando aplicação funcional.

📦 Estrutura
db/: scripts schema e seed
ejb-module/: serviço EJB com bug a ser corrigido
backend-module/: backend Spring Boot
frontend/: app Angular
docs/: instruções e critérios
.github/workflows/: CI
✅ Tarefas do candidato
Executar db/schema.sql e db/seed.sql
Corrigir bug no BeneficioEjbService
Implementar backend CRUD + integração com EJB
Desenvolver frontend Angular consumindo backend
Implementar testes
Documentar (Swagger, README)
Submeter via fork + PR
🐞 Bug no EJB
Transferência não verifica saldo, não usa locking, pode gerar inconsistência
Espera-se correção com validações, rollback, locking/optimistic locking
📊 Critérios de avaliação
Arquitetura em camadas (20%)
Correção EJB (20%)
CRUD + Transferência (15%)
Qualidade de código (10%)
Testes (15%)
Documentação (10%)
Frontend (10%)