package pl.clinic.business.dao;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;
import pl.clinic.infrastructure.database.repository.mapper.OfficeEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AppointmentsRepository {
    private final EntityManager entityManager;

    private final AppointmentsJpaRepository appointmentsJpaRepository;
    private final AppointmentsEntityMapper appointmentsEntityMapper;

    public void save(Appointments appointment) {
        AppointmentsEntity appointments = appointmentsEntityMapper.mapToEntity(appointment);
        OfficeEntity mergedOffice = entityManager.merge(appointments.getOffice());
        appointments.setOffice(mergedOffice);

        appointmentsJpaRepository.save(appointments);
    }


    public List<Appointments> findAppointmentsByPatientIdWithAllFields(Integer patientId) {
      return   appointmentsJpaRepository.findByPatientIdWithScheduled(patientId).stream()
              .map(appointmentsEntityMapper::mapFromEntity)
              .toList();
    }



    public Optional<Appointments>  findByProbableStartTimeWithOffice(OffsetDateTime offsetDateTime, Office office) {
        return appointmentsJpaRepository.findByProbableStartTimeWithOffice(offsetDateTime,office.getOfficeId())
                .map(appointmentsEntityMapper::mapFromEntity);
    }

    public List<Appointments> findCompletedAndCanceledByPatient(Integer patientId) {
        return appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientId).stream()
                .map(appointmentsEntityMapper::mapFromEntity)
                .toList();
    }

    public Optional<Appointments> findById(Integer appointmentId) {
        return appointmentsJpaRepository.findById(appointmentId)
                .map(appointmentsEntityMapper::mapFromEntity);
    }
}
