package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.repository.jpa.MedicationsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.MedicationsEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MedicationsRepository {
    private MedicationsJpaRepository medicationsJpaRepository;
    private MedicationsEntityMapper medicationsEntityMapper;

    public void save(Medications medication) {
         medicationsJpaRepository.save(medicationsEntityMapper.mapToEntity(medication));
    }



    public Optional<Medications> findByName(String medicationName) {
        return    medicationsJpaRepository.findByMedicationName(medicationName)
                .map(medicationsEntity -> medicationsEntityMapper.mapFromEntity(medicationsEntity));
    }
}
