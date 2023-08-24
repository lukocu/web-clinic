package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.infrastructure.database.repository.jpa.PatientCardJpaRepository;
import pl.clinic.infrastructure.database.repository.jpa.PatientsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientCardEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class PatientCardRepository  {

    private PatientCardJpaRepository patientCardJpaRepository;
    private PatientCardEntityMapper patientCardEntityMapper;

    public List<PatientCard> findByPatient(Patients patient) {
        return patientCardJpaRepository.findByPatient(patient).stream()
                .map(entity -> patientCardEntityMapper.mapFromEntity(entity))
                .toList();
    }
}
