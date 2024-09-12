package com.example.demo;

import java.time.LocalDate;

public record Product(String name, int quantity, ProductionLabel productionLabel) {
    public record ProductionLabel(String producer, LocalDate productionDate) {}
}
