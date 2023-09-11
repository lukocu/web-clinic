package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentStatusJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentStatusEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AppointmentStatusRepository {

    private AppointmentStatusJpaRepository appointmentStatusJpaRepository;
    private AppointmentStatusEntityMapper appointmentStatusEntityMapper;

    public void save(AppointmentStatus newStatus) {
        appointmentStatusJpaRepository.save(appointmentStatusEntityMapper.mapToEntity(newStatus));
    }

    public Optional<AppointmentStatus> findByStatus(Status status) {
      return  appointmentStatusJpaRepository.findByStatus(status)
                .map(entity -> appointmentStatusEntityMapper.mapFromEntity(entity));
    }
}
