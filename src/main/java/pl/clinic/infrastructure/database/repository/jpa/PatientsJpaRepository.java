package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

import java.util.Optional;

@Repository
public interface PatientsJpaRepository extends JpaRepository<PatientsEntity,Integer> {

    @SuppressWarnings("NullableProblems")
    Optional<PatientsEntity> findById(Integer id);
    Optional<PatientsEntity> findByNameAndSurname(String name,String surname);
    Optional<PatientsEntity> findByUserId(Integer userId);
}
