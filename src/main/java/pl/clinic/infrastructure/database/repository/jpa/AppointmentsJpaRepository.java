package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentsJpaRepository extends JpaRepository<AppointmentsEntity, Integer> {

    @Query("""
        SELECT a FROM AppointmentsEntity a 
        WHERE a.patient.patientId = :patientId
        """)
    Set<AppointmentsEntity> findByPatientId(Integer patientId);

    Optional<AppointmentsEntity> findByProbableStartTime(OffsetDateTime offsetDateTime);
}
