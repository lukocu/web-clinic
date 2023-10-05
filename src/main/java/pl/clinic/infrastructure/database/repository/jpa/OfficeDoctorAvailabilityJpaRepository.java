package pl.clinic.infrastructure.database.repository.jpa;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeDoctorAvailabilityJpaRepository extends JpaRepository<OfficeDoctorAvailabilityEntity, Integer> {

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

    @Query("SELECT NEW pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity(ofa.officeAvailabilityId, ofa.date, ofa.startTime, ofa.endTime, ofa.availabilityStatus, ofa.office) " +
            "FROM OfficeDoctorAvailabilityEntity ofa " +
            "INNER JOIN ofa.office AS offi " +
            "INNER JOIN offi.doctor AS doc " +
            "WHERE doc.doctorId = :doctorId AND ofa.availabilityStatus = false")
    List<OfficeDoctorAvailabilityEntity> findUnavailableStatusForDoctor(@Param("doctorId") Integer doctorId);


    @Modifying
    @Query("delete from OfficeDoctorAvailabilityEntity oda where oda.officeAvailabilityId = :officeAvailabilityId")
    void deleteByIdCustom(@Param("officeAvailabilityId") Integer officeAvailabilityId);

    @Query("SELECT oda FROM OfficeDoctorAvailabilityEntity oda " +
            "WHERE oda.date = :date " +
            "AND oda.office.officeId = :officeId " +
            "AND ((oda.startTime >= :startTime AND oda.startTime < :endTime) OR " +
            "(oda.endTime > :startTime AND oda.endTime <= :endTime))")
    List<OfficeDoctorAvailabilityEntity> findByDateAndTimeRange(@Param("date") LocalDate date,
                                                                @Param("startTime") LocalTime startTime,
                                                                @Param("endTime") LocalTime endTime,
                                                                @Param("officeId") Integer officeId);

    @Query("SELECT oda FROM OfficeDoctorAvailabilityEntity oda " +
            "WHERE oda.date = :date " +
            "AND oda.office.officeId = :officeId " +
            "AND ((oda.startTime >= :startTime AND oda.startTime < :endTime) OR " +
            "(oda.endTime > :startTime AND oda.endTime <= :endTime)) " +
            "AND oda.availabilityStatus = true")
    List<OfficeDoctorAvailabilityEntity> findConflictingAppointments(@Param("date") LocalDate date,
                                                                     @Param("startTime") LocalTime startTime,
                                                                     @Param("endTime") LocalTime endTime,
                                                                     @Param("officeId") Integer officeId);

    @Query("""
            SELECT oda
            FROM OfficeDoctorAvailabilityEntity oda
            WHERE oda.date = :date
              AND oda.startTime = :startTime
              AND oda.office.officeId = :officeId
            """)
    Optional<OfficeDoctorAvailabilityEntity> findByDateAndTime(@Param("date") LocalDate date,
                                                               @Param("startTime") LocalTime startTime,
                                                               @Param("officeId") Integer officeId);

    @Query("""
            select oda from OfficeDoctorAvailabilityEntity oda
            inner join oda.office o
            inner join o.doctor doc
            where oda.officeAvailabilityId = :officeAvailabilityId
            """)
    Optional<OfficeDoctorAvailabilityEntity> findByIdAndOffice(Integer officeAvailabilityId);
}
