package br.com.fiap.med_api.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.med_api.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //component

public class AppointmentController {

   private List<Appointment> repository = new ArrayList<>();

    //Listar todos os agendamentos
    //GET :8080/categories -> 200 ok -> JSON
    @GetMapping("/appointments")
    public List<Appointment> index() {
       return repository;
    }

    //Cadastrar um agendamento
    @PostMapping("/appointments")
    //@ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
        System.out.println("Cadastrando..." + appointment.getName());
        repository.add(appointment);
        return ResponseEntity.status(201).body(appointment);
    }

    //Detalhes do agendamento
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Long id){
        System.out.println("Buscando agendamento " + id);
        var appointment = repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (appointment.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(appointment.get());
    }

    //Apagar uma categoria
    
}
