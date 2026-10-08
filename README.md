# Teste Técnico — Desenvolvedor Full Stack Java Sênior

Tempo: aproximadamente 30 minutos.

## Contexto
Uma plataforma de crédito e benefícios recebe solicitações de crédito. O serviço inicial recebe a solicitação via REST e publica uma mensagem em uma fila RabbitMQ.

## Tarefa
Complete a implementação existente criando o processamento assíncrono:

1. Crie um consumer RabbitMQ usando Spring AMQP.
2. Consuma a fila `credit.requests`.
3. Ao receber uma mensagem, altere o status para `PROCESSED`.
4. Registre no log `requestId`, `employeeId` e `amount`.
5. Não processe novamente uma solicitação que já esteja `PROCESSED`.

Não é necessário criar banco, autenticação ou interface gráfica.

## Execução
Pré-requisitos: Java 17+, Maven e Docker.

Suba o RabbitMQ:
```bash
docker compose up -d
```

Execute:
```bash
cd benefit-service
mvn spring-boot:run
```

Envie:
```bash
curl -X POST http://localhost:8080/credit-requests   -H "Content-Type: application/json"   -d '{"employeeId":123,"amount":1500.00}'
```

A implementação inicial cria a solicitação com status `PENDING` e publica na fila. Complete o consumer para que ela seja processada e fique `PROCESSED`.

Priorize uma solução simples, organizada e funcional.
