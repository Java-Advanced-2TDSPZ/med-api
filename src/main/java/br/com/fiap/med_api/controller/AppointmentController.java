package br.com.fiap.med_api.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.med_api.model.Appointment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController //component

public class AppointmentController {

   private Logger log = LoggerFactory.getLogger(getClass());

   private List<Appointment> repository = new ArrayList<>();

    //Listar todos os agendamentos
    @GetMapping("/appointments")
    public List<Appointment> index() {
       return repository;
    }

    //Cadastrar um agendamento
    @PostMapping("/appointments")
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
        log.info("Cadastrando..." + appointment.getName());
        repository.add(appointment);
        return ResponseEntity.status(201).body(appointment);
    }

    //Detalhes do agendamento
    @GetMapping("/appointments/{id}")
    public Appointment get(@PathVariable Long id){
        log.info("Buscando agendamento " + id);
        return getAppointment(id);
    }

    //Apagar um agendamento
    @DeleteMapping("/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando agendamento " + id);
        repository.remove(getAppointment(id));

    }

    //Editar um agendamento
    @PutMapping("/appointments/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment appointment){
        log.info("Atualizando agendamento " + id + " " + appointment);  
        repository.remove(getAppointment(id));
        appointment.setId(id);
        repository.add(appointment);
        return appointment;
    }

    private Appointment getAppointment(Long id) {
        return repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }
    
}
