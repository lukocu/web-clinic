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
            "LEFT JOIN FETCH d.specializations s " + // Pobierz specjalizacje doktora
            "LEFT JOIN FETCH d.offices o " + // Pobierz biura doktora
            "ORDER BY d.doctorId") // Opcjonalnie można dodać sortowanie
    List<DoctorsEntity> findDoctorsAndOffice();

    Optional<DoctorsEntity> findByNameAndSurname(String name, String surname);

    Optional<DoctorsEntity> findByPesel(String pesel);

    @Query("SELECT d FROM DoctorsEntity d WHERE d.user.userId= :userId")
    Optional<DoctorsEntity> findByUserId(Integer userId);
}
