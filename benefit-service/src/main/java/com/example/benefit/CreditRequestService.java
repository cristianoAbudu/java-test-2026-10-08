package com.example.benefit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CreditRequestService {

    private final RabbitTemplate rabbitTemplate;

    private final Map<String, CreditRequest> requests =
            new ConcurrentHashMap<>();

    public CreditRequestService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public CreditRequest create(CreditRequestInput input) {

        String id = UUID.randomUUID().toString();

        CreditRequest request = new CreditRequest(
                id,
                input.employeeId(),
                input.amount(),
                "PENDING"
        );

        requests.put(id, request);

        rabbitTemplate.convertAndSend(
                RabbitConfig.QUEUE,
                request
        );

        return request;
    }

    public CreditRequest get(String id) {
        return requests.get(id);
    }

    public void markProcessed(String id) {

        requests.computeIfPresent(
                id,
                (key, value) -> new CreditRequest(
                        value.requestId(),
                        value.employeeId(),
                        value.amount(),
                        "PROCESSED"
                )
        );
    }
}