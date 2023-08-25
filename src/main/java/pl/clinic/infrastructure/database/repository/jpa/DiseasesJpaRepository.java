package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;

import java.util.Optional;

@Repository
public interface DiseasesJpaRepository extends JpaRepository<DiseasesEntity, Integer> {
    Optional<DiseasesEntity> findByDiseaseName(String diseaseName);
}
