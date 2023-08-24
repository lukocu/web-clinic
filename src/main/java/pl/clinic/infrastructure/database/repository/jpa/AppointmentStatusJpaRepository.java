package pl.clinic.infrastructure.database.repository.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.infrastructure.database.entity.Status;

import java.util.Optional;

@Repository
public interface AppointmentStatusJpaRepository extends JpaRepository<AppointmentStatusEntity,Integer> {
    Optional<AppointmentStatusEntity> findByStatusName(String statusName);
}
