package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;

import java.util.Optional;

@Repository
public interface DoctorsJpaRepository extends JpaRepository<DoctorsEntity, Integer> {
    Optional<DoctorsEntity> findByNameAndSurname(String name, String surname);

    Optional<DoctorsEntity> findByPesel(String pesel);
}
