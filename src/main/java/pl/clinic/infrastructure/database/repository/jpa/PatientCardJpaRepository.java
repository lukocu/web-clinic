package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientCardJpaRepository extends JpaRepository<PatientCardEntity,Integer> {
    List<PatientCardEntity> findByPatient(PatientsEntity patient);
    Optional<PatientCardEntity> findByPatientAndDoctorAndDiagnosisDate(PatientsEntity patient, DoctorsEntity doctor, OffsetDateTime diagnosisDate);
    Optional<PatientCardEntity> findByPatientAndDiagnosisDate(PatientsEntity patient, OffsetDateTime diagnosisDate);
}
