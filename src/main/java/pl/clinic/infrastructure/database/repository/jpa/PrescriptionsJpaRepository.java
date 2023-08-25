package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.PrescriptionsEntity;

@Repository
public interface PrescriptionsJpaRepository extends JpaRepository<PrescriptionsEntity, Integer> {

}
