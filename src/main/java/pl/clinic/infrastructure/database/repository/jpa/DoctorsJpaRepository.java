package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorsJpaRepository extends JpaRepository<DoctorsEntity, Integer> {
    @Query("SELECT DISTINCT d FROM DoctorsEntity d " +
            "LEFT JOIN FETCH d.specializations s " +
            "LEFT JOIN FETCH d.offices o " +
            "ORDER BY d.doctorId")
    List<DoctorsEntity> findDoctorsAndOffice();
    @Query("SELECT d FROM DoctorsEntity d WHERE d.user.userId= :userId")
    Optional<DoctorsEntity> findByUserId(Integer userId);
}
