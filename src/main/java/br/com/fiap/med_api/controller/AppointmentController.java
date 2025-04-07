package br.com.fiap.med_api.controller;
import java.util.List;
import br.com.fiap.med_api.model.Appointment;
import br.com.fiap.med_api.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController //component
@RequestMapping("appointments")

public class AppointmentController {

   private Logger log = LoggerFactory.getLogger(getClass());

   @Autowired //injetar dependência
   private AppointmentRepository repository;

    //Listar todos os agendamentos
    @GetMapping
    public List<Appointment> index() {
       return repository.findAll();
    }

    //Cadastrar um agendamento
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
        log.info("Cadastrando..." + appointment.getName());
        repository.save(appointment);
        return ResponseEntity.status(201).body(appointment);
    }

    //Detalhes do agendamento
    @GetMapping("{id}")
    public Appointment get(@PathVariable Long id){
        log.info("Buscando agendamento " + id);
        return getAppointment(id);
    }

    //Apagar um agendamento
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando agendamento " + id);
        repository.delete(getAppointment(id));

    }

    //Editar um agendamento
    @PutMapping("{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment appointment){
        log.info("Atualizando agendamento " + id + " " + appointment);  
        getAppointment(id);
        appointment.setId(id);
        repository.save(appointment);
        return appointment;
    }

    private Appointment getAppointment(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }
    
}
