package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientCardJpaRepository extends JpaRepository<PatientCardEntity, Integer> {

    Optional<PatientCardEntity> findByPatientPesel(String patientPesel);
    @Query("""

            SELECT pc,doc,dis,pres,medi FROM PatientCardEntity pc
              inner JOIN  pc.patient pat
              inner JOIN  pc.doctor doc
              inner JOIN  pc.diseases dis
              inner JOIN  pc.prescription pres
              inner JOIN  pres.medications medi
              WHERE pat.patientId = :patientId
            """)
    List<PatientCardEntity> findByPatientIdWithDetails(@Param("patientId") Integer patientId);
}
// dodałem tutaj cos nowego w select ale ogolnie to tak usun appointments z bazy danych zdubuguj kod no i zobaczymy co teraz wyciagniemy z tego
