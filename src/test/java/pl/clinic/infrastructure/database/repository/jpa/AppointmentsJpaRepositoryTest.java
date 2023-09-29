package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.business.PatientsService;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;
import pl.clinic.util.EntityFixtures;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentsJpaRepositoryTest {

    private AppointmentsJpaRepository appointmentsJpaRepository;

    @Test
    @DisplayName("Only appointments that have a status of Scheduled should be returned")
    void testFindByPatientIdWithScheduled() {
        // given
        Integer patientId = 2; // default 1 appointment scheduled
        Integer nonExistentPatientId = 999;

        List<AppointmentsEntity> appointments = appointmentsJpaRepository.
                findByPatientIdWithScheduled(patientId);

        List<AppointmentsEntity> noAppointments = appointmentsJpaRepository.
                findByPatientIdWithScheduled(nonExistentPatientId);

        // when, then
        Assertions.assertEquals(1,appointments.size());
        Assertions.assertTrue(noAppointments.isEmpty());

    }

    @Test
    void testFindByProbableStartTimeWithOffice() {
        //given
        Integer officeId = 2;
        Integer nonExistentOfficeId = 999;

        OffsetDateTime startTime =
                OffsetDateTime.of(2023, 8, 22, 15, 0, 0, 0,
                        ZoneOffset.UTC);

        OffsetDateTime nonExistentStartTime =
                OffsetDateTime.of(2023, 12, 1, 14, 30, 0, 0,
                        ZoneOffset.UTC);


        Optional<AppointmentsEntity> appointment =
                appointmentsJpaRepository.findByProbableStartTimeWithOffice(startTime, officeId);

        Optional<AppointmentsEntity> nonExistentAppointment =
                appointmentsJpaRepository.findByProbableStartTimeWithOffice(startTime, nonExistentOfficeId);

        Optional<AppointmentsEntity> nonExistentStartTimeAppointment =
                appointmentsJpaRepository.findByProbableStartTimeWithOffice(nonExistentStartTime, officeId);

        // when, then
        Assertions.assertTrue(nonExistentAppointment.isEmpty());
        Assertions.assertTrue(appointment.isPresent());
        Assertions.assertTrue(nonExistentStartTimeAppointment.isEmpty());
    }
    @Test
    void testFindCompletedAndCanceledByPatientId() {

        Integer patientId = 2;

        PatientsEntity patients= EntityFixtures.patientWithoutVisits();

        Integer patientWithoutAppointmentsId = patients.getPatientId();


        Integer patientWithCompletedAppointmentsId = 4;

        Integer patientWithCanceledAppointmentsId = 3;

        List<AppointmentsEntity> completedAndCanceledAppointments =
                appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientId);

        List<AppointmentsEntity> noCompletedAndCanceledAppointments =
                appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientWithoutAppointmentsId);


        List<AppointmentsEntity> completedAppointmentsOnly =
                appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientWithCompletedAppointmentsId);


        List<AppointmentsEntity> canceledAppointmentsOnly =
                appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientWithCanceledAppointmentsId);

        Assertions.assertFalse(completedAndCanceledAppointments.isEmpty());
        Assertions.assertTrue(noCompletedAndCanceledAppointments.isEmpty());
        Assertions.assertFalse(completedAppointmentsOnly.isEmpty());
        Assertions.assertEquals(2,completedAppointmentsOnly.size());
        Assertions.assertEquals(2,canceledAppointmentsOnly.size());
    }
}
