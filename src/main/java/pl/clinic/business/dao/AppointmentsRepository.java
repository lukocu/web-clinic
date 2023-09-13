package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
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

    public Optional<Appointments> findByProbableStartTime(OffsetDateTime offsetDateTime) {
        return appointmentsJpaRepository.findByProbableStartTime(offsetDateTime)
                .map(appointmentsEntityMapper::mapFromEntity);
    }
}
