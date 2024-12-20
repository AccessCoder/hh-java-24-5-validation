package org.example.hhjava245validation.model;

import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Employees")
public record Employee(String id,
                       @NotBlank @Size(min = 2, max = 40, message = "Name must contain between 2 and 40 letters") String name,
                       @NotBlank @Email String email,
                       @NotBlank @Pattern(regexp = "^\\d{10}$", message = "Telefonnummer muss aus 10 Ziffern bestehen") String phone) {
}

/**
 * @NotNull = Wert darf nicht null sein! | ""= Valider Wert
 *
 * @NotEmpty = Wert darf nicht null sein UND er darf nicht leer sein | " " = Valide
 *
 * @NotBlank = Wert darf nicht null sein UND er darf nicht leer sein UND darf nicht nur als Leerzeichen bestehen
 */