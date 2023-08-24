package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.infrastructure.database.entity.PrescriptionsEntity;

import java.util.List;

@Repository
public interface PrescriptionsJpaRepository extends JpaRepository<PrescriptionsEntity,Integer> {

}
