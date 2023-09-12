package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;

import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AppointmentsRepository {

    private final AppointmentsJpaRepository appointmentsJpaRepository;
    private final AppointmentsEntityMapper appointmentsEntityMapper;

    public void save(Appointments appointment) {
        appointmentsJpaRepository.save(appointmentsEntityMapper.mapToEntity(appointment));
    }


    public List<Appointments> findAppointmentsByPatientId(Integer patientId) {
      return   appointmentsJpaRepository.findByPatientId(patientId).stream()
              .map(appointmentsEntityMapper::mapFromEntity)
              .toList();
    }
}
