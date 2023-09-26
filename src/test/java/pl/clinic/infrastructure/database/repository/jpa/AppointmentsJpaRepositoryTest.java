package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;
import pl.clinic.util.EntityFixtures;

import javax.swing.text.html.parser.Entity;
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
    void testFindByPatientIdWithAllFields() {
        // Arrange
        Integer patientId = 1;
        AppointmentsEntity appointment1 = EntityFixtures.appointment1();
        AppointmentsEntity appointment2 = EntityFixtures.appointment2();
        appointment1.setPatient(EntityFixtures.patient1());
        appointment1.setPatient(EntityFixtures.patient1());
        appointmentsJpaRepository.save(appointment1);
        appointmentsJpaRepository.save(appointment2);
        // Act
        Set<AppointmentsEntity> appointments = appointmentsJpaRepository.findByPatientIdWithAllFields(patientId);

        // Assert
        Assertions.assertEquals(2,appointments.size());
        // Add your assertions here
    }

  /*  @Test
    void testFindByProbableStartTimeWithOffice() {
        // Arrange
        Integer officeId = 1;
        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        // Act
        Optional<AppointmentsEntity> appointment = appointmentsJpaRepository.findByProbableStartTimeWithOffice(offsetDateTime, officeId);

        // Assert
        assertThat(appointment).isPresent();
        // Add your assertions here
    }

    @Test
    void testFindCompletedAndCanceledByPatientId() {
        // Arrange
        Integer patientId = 1;

        // Act
        List<AppointmentsEntity> appointments = appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientId);

        // Assert
        assertThat(appointments).isNotEmpty();
        // Add your assertions here
    }*/
}
