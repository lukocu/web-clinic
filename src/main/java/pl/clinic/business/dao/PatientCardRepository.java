package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.repository.jpa.PatientCardJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientCardEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PatientCardRepository {

    private PatientCardJpaRepository patientCardJpaRepository;
    private PatientCardEntityMapper patientCardEntityMapper;


    public Optional<PatientCard> findPatientCardByPesel(String pesel) {
        return patientCardJpaRepository.findByPatientPesel(pesel)
                .map(entity -> patientCardEntityMapper.mapFromEntity(entity));
    }


    public void save(PatientCard existingPatientCard) {
        patientCardJpaRepository.save(patientCardEntityMapper.mapToEntityWithFields(existingPatientCard));
    }
}
