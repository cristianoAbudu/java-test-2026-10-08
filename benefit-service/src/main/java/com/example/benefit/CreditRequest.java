package com.example.benefit;

import java.math.BigDecimal;

public record CreditRequest(String requestId, Long employeeId, BigDecimal amount, String status) {
}