package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeDoctorAvailabilityJpaRepository extends JpaRepository<OfficeDoctorAvailabilityEntity, Integer> {

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

    @Query("SELECT oda FROM OfficeDoctorAvailabilityEntity oda WHERE oda.office.officeId = :officeId AND oda.availabilityStatus = true")
    List<OfficeDoctorAvailabilityEntity> findByOfficeIdAndAvailabilityStatusIsTrue(Integer officeId);
    @Query("SELECT oda FROM OfficeDoctorAvailabilityEntity oda WHERE oda.office.officeId = :officeId AND oda.availabilityStatus = false")
    List<OfficeDoctorAvailabilityEntity> findByOfficeIdAndAvailabilityStatusIsFalse(Integer officeId);

    List<OfficeDoctorAvailabilityEntity> findByAvailabilityStatusIsFalse();
    @Query("SELECT NEW pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity(ofa.officeAvailabilityId, ofa.date, ofa.startTime, ofa.endTime, ofa.availabilityStatus, ofa.office) " +
            "FROM OfficeDoctorAvailabilityEntity ofa " +
            "INNER JOIN ofa.office AS offi " +
            "INNER JOIN offi.doctor AS doc " +
            "WHERE doc.doctorId = :doctorId AND ofa.availabilityStatus = false")
    List<OfficeDoctorAvailabilityEntity> findUnavailableStatusForDoctor(@Param("doctorId") Integer doctorId);


    @Modifying
    @Query("delete from OfficeDoctorAvailabilityEntity oda where oda.officeAvailabilityId = :officeAvailabilityId")
    void deleteByIdCustom(@Param("officeAvailabilityId") Integer officeAvailabilityId);
}
