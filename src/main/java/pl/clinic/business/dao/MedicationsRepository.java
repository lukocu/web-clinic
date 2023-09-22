package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.api.dto.mapper.MedicationsMapper;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.repository.jpa.MedicationsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.MedicationsEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MedicationsRepository {
    private MedicationsJpaRepository medicationsJpaRepository;
    private MedicationsEntityMapper medicationsEntityMapper;
    private MedicationsMapper medicationsMapper;
    public void save(Medications medication) {
         medicationsJpaRepository.save(medicationsEntityMapper.mapToEntity(medication));
    }

    public Optional<Medications> findByName(String medicationName) {
     return    medicationsJpaRepository.findByMedicationName(medicationName)
                .map(medication-> medicationsEntityMapper.mapFromEntity(medication));
    }

    public Medications findByNameNoOptional(String medicationName) {
        return    medicationsEntityMapper
                .mapFromEntity(medicationsJpaRepository.findByMedicationNameNoOptional(medicationName));
    }
}
