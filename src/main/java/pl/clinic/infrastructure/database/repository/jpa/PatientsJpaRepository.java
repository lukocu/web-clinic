package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

import java.util.Optional;

@Repository
public interface PatientsJpaRepository extends JpaRepository<PatientsEntity, Integer> {


    Optional<PatientsEntity> findByPesel(String pesel);
}
