package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorsJpaRepository extends JpaRepository<DoctorsEntity,Integer> {
    DoctorsEntity findByNameAndSurname(String name, String surname);
    List<DoctorsEntity> findBySpecializations(Set<SpecializationEntity> specializations);
    DoctorsEntity findByDoctorId(Integer id);
    DoctorsEntity findByPhone(String phone);
}
