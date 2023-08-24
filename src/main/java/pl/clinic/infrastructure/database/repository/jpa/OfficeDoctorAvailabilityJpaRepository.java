package pl.clinic.infrastructure.database.repository.jpa;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface OfficeDoctorAvailabilityJpaRepository extends JpaRepository<OfficeDoctorAvailabilityEntity,Integer> {

    List<OfficeDoctorAvailabilityEntity> findByOfficeAndDateAndAvailabilityStatus(OfficeEntity office, LocalDate date, Boolean availabilityStatus);
    List<OfficeDoctorAvailabilityEntity> findAllByAvailabilityStatusAndDate(Boolean availabilityStatus, LocalDate date);
    List<OfficeDoctorAvailabilityEntity> findAllByDate(LocalDate date);

    @Query("SELECT oda " +
            "FROM DoctorsEntity doctors " +
            "JOIN doctors.offices office " +
            "JOIN office.officeDoctorAvailabilities oda " +
            "WHERE oda.availabilityStatus = true " +
            "AND doctors.name = :name " +
            "AND doctors.surname = :surname")
    List<OfficeDoctorAvailabilityEntity> findAvailableHoursForDoctor(@Param("name") String name, @Param("surname") String surname);
}
