package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.domain.Status;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentStatusJpaRepositoryTest {

    private AppointmentStatusJpaRepository appointmentStatusJpaRepository;

    @Test
    void testFindByStatus() {

        Optional<AppointmentStatusEntity> foundStatusScheduledEntity = appointmentStatusJpaRepository.findByStatus(Status.Scheduled);
        Optional<AppointmentStatusEntity> foundStatusCanceledEntity = appointmentStatusJpaRepository.findByStatus(Status.Canceled);
        Optional<AppointmentStatusEntity> foundStatusCompletedEntity = appointmentStatusJpaRepository.findByStatus(Status.Completed);
        Optional<AppointmentStatusEntity> statusNotFound = appointmentStatusJpaRepository.findByStatus(null);


        assertEquals(Status.Scheduled, foundStatusScheduledEntity.get().getStatus());
        assertEquals(Status.Canceled, foundStatusCanceledEntity.get().getStatus());
        assertEquals(Status.Completed, foundStatusCompletedEntity.get().getStatus());
        assertEquals(Optional.empty(),statusNotFound);
    }

}
