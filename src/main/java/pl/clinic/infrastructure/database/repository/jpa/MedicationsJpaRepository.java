package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

import java.util.Optional;

@Repository
public interface MedicationsJpaRepository extends JpaRepository<MedicationsEntity, Integer> {


    @Query("SELECT m FROM MedicationsEntity m WHERE m.medicationName = :medicationName")
    Optional<MedicationsEntity> findByMedicationName(String medicationName);
}
