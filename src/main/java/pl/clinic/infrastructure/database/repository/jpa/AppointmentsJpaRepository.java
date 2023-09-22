package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentsJpaRepository extends JpaRepository<AppointmentsEntity, Integer> {

    @Query("""
    select app from AppointmentsEntity app
    inner join app.office offi
    inner join offi.doctor doc
    where app.patient.patientId = :patientId
    and app.appointmentStatus.status = 'Scheduled'
    """)
    Set<AppointmentsEntity> findByPatientIdWithAllFields(@Param("patientId") Integer patientId);


    Optional<AppointmentsEntity> findByProbableStartTime(OffsetDateTime offsetDateTime);

    @Query("""
            select a from AppointmentsEntity a
            where a.office.officeId = :officeId
            and a.probableStartTime = :offsetDateTime
            """)
    Optional<AppointmentsEntity> findByProbableStartTimeWithOffice(
            @Param("offsetDateTime") OffsetDateTime offsetDateTime, @Param("officeId") Integer officeId);

    @Query("""
            SELECT app FROM AppointmentsEntity app
            INNER JOIN app.appointmentStatus appstat
            WHERE (appstat.status = 'Completed' OR appstat.status = 'Canceled')
           AND app.patient.patientId = :patientId
""")
    List<AppointmentsEntity> findCompletedAndCanceledByPatientId(@Param("patientId")Integer patientId);
}
