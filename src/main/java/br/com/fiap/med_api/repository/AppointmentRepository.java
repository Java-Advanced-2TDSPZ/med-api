package br.com.fiap.med_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.med_api.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
