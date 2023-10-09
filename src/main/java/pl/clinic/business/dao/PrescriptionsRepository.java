package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.repository.jpa.PrescriptionsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PrescriptionsEntityMapper;

@Repository
@AllArgsConstructor
public class PrescriptionsRepository {
    PrescriptionsEntityMapper prescriptionsEntityMapper;
    PrescriptionsJpaRepository prescriptionsJpaRepository;
    public void save(Prescriptions prescription) {
        prescriptionsJpaRepository.save(prescriptionsEntityMapper.mapToEntity(prescription));
    }

    public void delete(Prescriptions prescription) {
        prescriptionsJpaRepository.delete(prescriptionsEntityMapper.mapToEntity(prescription));
    }
}
