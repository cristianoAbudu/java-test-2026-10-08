package com.example.benefit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditRequestConsumer {
    private final CreditRequestService service;

    public CreditRequestConsumer(CreditRequestService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consume(CreditRequest request) {
        // TODO: IMPLEMENTAR PELO CANDIDATO
        // 1. Ignorar se já estiver PROCESSED.
        if(!service.get(request.requestId()).equals("PROCESSED")){
            // 2. Registrar requestId, employeeId e amount no log.
            System.out.println("requestId "+request.requestId());
            System.out.println("employeeId "+request.employeeId());
            System.out.println("amount "+request.amount());

            // 3. Marcar a solicitação como PROCESSED.
            service.markProcessed(request.requestId());
        }


    }
}