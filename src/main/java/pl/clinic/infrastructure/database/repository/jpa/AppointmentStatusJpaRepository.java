package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;

import java.util.Optional;

@Repository
public interface AppointmentStatusJpaRepository extends JpaRepository<AppointmentStatusEntity, Integer> {

    Optional<AppointmentStatusEntity> findByStatus(Status status);
}
