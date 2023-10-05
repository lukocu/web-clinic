package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.repository.jpa.PatientCardJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientCardEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class PatientCardRepository {

    private PatientCardJpaRepository patientCardJpaRepository;
    private PatientCardEntityMapper patientCardEntityMapper;


    public PatientCard save(PatientCard existingPatientCard) {
     return patientCardEntityMapper.mapFromEntityWithFields(
             patientCardJpaRepository.save(
                     patientCardEntityMapper.mapToEntityWithFields(existingPatientCard)));
    }

    public List<PatientCard> findPatientCardByPatientId(Integer patientId) {
        return patientCardJpaRepository.findByPatientIdWithDetails(patientId).stream()
                .map(entity->patientCardEntityMapper.mapFromEntityWithFields(entity))
                .toList();
    }

    public void deleteById(Integer patientCardId) {
        patientCardJpaRepository.deleteById(patientCardId);
    }
}
