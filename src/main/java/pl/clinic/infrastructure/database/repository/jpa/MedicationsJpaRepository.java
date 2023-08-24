package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

import java.util.Optional;

@Repository
public interface MedicationsJpaRepository extends JpaRepository<MedicationsEntity,Integer> {
    Optional<MedicationsEntity> findByMedicationName(String medicationName);
}
