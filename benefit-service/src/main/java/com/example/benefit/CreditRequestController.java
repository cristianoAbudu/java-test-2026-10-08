package com.example.benefit;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-requests")
public class CreditRequestController {
    private final CreditRequestService service;

    public CreditRequestController(CreditRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreditRequest> create(@Valid @RequestBody CreditRequestInput input) {
        return ResponseEntity.accepted().body(service.create(input));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditRequest> get(@PathVariable String id) {
        CreditRequest r = service.get(id);
        return r == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }
}