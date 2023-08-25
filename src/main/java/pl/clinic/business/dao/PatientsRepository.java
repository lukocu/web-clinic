package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.repository.jpa.PatientsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientsEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PatientsRepository {

    private PatientsEntityMapper patientsEntityMapper;
    private PatientsJpaRepository patientsJpaRepository;

    public Optional<Patients> findPatientByPesel(String pesel) {
    return  patientsJpaRepository.findByPesel(pesel)
            .map(entity->patientsEntityMapper.mapFromEntity(entity));

    }

}
