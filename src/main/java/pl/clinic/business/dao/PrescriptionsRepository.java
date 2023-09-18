package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.repository.jpa.PrescriptionsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PrescriptionsEntityMapper;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PrescriptionsRepository {
    PrescriptionsEntityMapper prescriptionsEntityMapper;
    PrescriptionsJpaRepository prescriptionsJpaRepository;
    public void save(Prescriptions prescription) {
        prescriptionsJpaRepository.save(prescriptionsEntityMapper.mapToEntity(prescription));
    }

    public Optional<Prescriptions> findByDate(OffsetDateTime prescriptionDate) {
        return prescriptionsJpaRepository.findByPrescriptionDate(prescriptionDate)
                .map(prescriptionsEntity -> prescriptionsEntityMapper.mapFromEntity(prescriptionsEntity));
    }
}
