package br.com.fiap.med_api.model;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Appointment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 3, max = 255, message = "tamanho deve pelo mennos 3 caracteres")
    private String name;

    @NotBlank
    private LocalDate date;


}
