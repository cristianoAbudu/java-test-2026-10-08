package com.example.benefit;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreditRequestInput(@NotNull Long employeeId, @NotNull @DecimalMin("0.01") BigDecimal amount) {
}