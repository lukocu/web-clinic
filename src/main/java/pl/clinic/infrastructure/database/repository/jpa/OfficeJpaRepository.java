package pl.clinic.infrastructure.database.repository.jpa;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfficeJpaRepository extends JpaRepository<OfficeEntity, Integer> {

    List<OfficeEntity> findByDoctor(DoctorsEntity doctor);

    List<OfficeEntity> findByFirstConsultationFeeLessThan(BigDecimal maxFee);

    List<OfficeEntity> findByFollowupConsultationFeeBetween(BigDecimal minFee, BigDecimal maxFee);

    List<OfficeEntity> findAllByDoctorDoctorId(Integer doctorId);
}
