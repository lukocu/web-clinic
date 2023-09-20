package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AppointmentsRepository {

    private final AppointmentsJpaRepository appointmentsJpaRepository;
    private final AppointmentsEntityMapper appointmentsEntityMapper;

    public void save(Appointments appointment) {
        appointmentsJpaRepository.save(appointmentsEntityMapper.mapToEntity(appointment));
    }


    public List<Appointments> findAppointmentsByPatientIdWithAllFields(Integer patientId) {
      return   appointmentsJpaRepository.findByPatientIdWithAllFields(patientId).stream()
              .map(appointmentsEntityMapper::mapFromEntity)
              .toList();
    }

    public Optional<Appointments> findByProbableStartTime(OffsetDateTime offsetDateTime) {
        return appointmentsJpaRepository.findByProbableStartTime(offsetDateTime)
                .map(appointmentsEntityMapper::mapFromEntity);
    }

    public Optional<Appointments>  findByProbableStartTimeWithOffice(OffsetDateTime offsetDateTime, Office office) {
        return appointmentsJpaRepository.findByProbableStartTimeWithOffice(offsetDateTime,office)
                .map(appointmentsEntityMapper::mapFromEntity);
    }
}
