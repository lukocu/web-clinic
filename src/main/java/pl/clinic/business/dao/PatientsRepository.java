package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.infrastructure.database.repository.jpa.PatientsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientsEntityMapper;

import java.util.List;
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
    public Patients save(Patients patient) {
        PatientsEntity savedPatient =
                patientsJpaRepository.save(patientsEntityMapper.mapToEntityWithUser(patient));

        return patientsEntityMapper.mapFromEntityWithUser(savedPatient);
    }

    public Optional<Patients> findById(Integer patientId) {
        return patientsJpaRepository.findById(patientId)
                .map(entity->patientsEntityMapper.mapFromEntityWithUser(entity));
    }


    public List<Patients> findAll() {
        return patientsJpaRepository.findAll().stream()
                .map(entity->patientsEntityMapper.mapFromEntity(entity))
                .toList();
    }

    public void delete(Integer patientId) {
        patientsJpaRepository.deleteById(patientId);
    }
}
