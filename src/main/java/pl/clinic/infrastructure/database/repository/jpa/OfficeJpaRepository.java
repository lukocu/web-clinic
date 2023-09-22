package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.util.List;

@Repository
public interface OfficeJpaRepository extends JpaRepository<OfficeEntity, Integer> {
    List<OfficeEntity> findAllByDoctorDoctorId(Integer doctorId);
}
